package com.srikar.service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srikar.entity.Alert;
import com.srikar.entity.Readings;
import com.srikar.entity.Vehicle;
import com.srikar.exception.VehicleBadRequestException;
import com.srikar.exception.VehicleNotFoundException;
import com.srikar.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleRepository repository;
	
	@Autowired
	private JavaMailSender mailSender;
		
	@Override
	@Transactional
	public List<Vehicle> findAll() {
		return repository.findAll();
	}


	@Override
	@Transactional
	public Vehicle update(String vin, Vehicle vehicle) {
		
		String str = vehicle.getLastServiceDate();
		ZonedDateTime lastServiceDate = ZonedDateTime.parse(str);
		ZonedDateTime present = ZonedDateTime.now(Clock.systemUTC());
		
		if(vehicle.getYear()<1800 || vehicle.getYear() > 2017){
			throw new VehicleBadRequestException("Vehicle Info : Year out of range.");
		}
		if(vehicle.getRedlineRpm() < 0){
			throw new VehicleBadRequestException("Vehicle Info : Red Line RPM less than 0.");
		}
		if(vehicle.getMaxFuelVolume() < 0){
			throw new VehicleBadRequestException("Vehicle Info : Max Fuel Volume less than 0.");
		}
		if(lastServiceDate.isAfter(present)){
			throw new VehicleBadRequestException("Vehicle Info : Service time in the future.");
		}
		
		
		
		Vehicle existing = repository.findOne(vin);
		if(existing == null){
			repository.create(vehicle);
		}else{
			repository.update(vehicle);
		}
		
		return vehicle;
	}


	@Override
	@Transactional
	public Readings addReadings(Readings readings) {	
		try {
			alerts(readings);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return repository.addReadings(readings);
	}


	@Override
	@Transactional
	public void alerts(Readings readings) throws MessagingException {
		String vinNumb = readings.getVin();
		Vehicle vehicleDetails = repository.findOne(vinNumb);
		if(vehicleDetails == null){
			throw new VehicleNotFoundException("Vehicle with VIN Number : "+ vinNumb +" Not Found");
		}
		if(readings.getFuelVolume() > vehicleDetails.getMaxFuelVolume()){
			throw new VehicleBadRequestException("Readings : Fuel volume is greater than Maximum fuel volume.");
		}
		if(readings.getSpeed()<0){
			throw new VehicleBadRequestException("Readings : Vehicle speed less than 0");
		}
		if(readings.getEngineRpm() < 0){
			throw new VehicleBadRequestException("Readings : Vehicle RPM less than 0");
		}
		if(readings.getEngineHp() < 0){
			throw new VehicleBadRequestException("Readings : Vehicle HP less than 0");
		}
		if(readings.getTires().getFrontLeft() < 0 || readings.getTires().getFrontRight() < 0
				|| readings.getTires().getRearLeft() < 0 || readings.getTires().getRearRight() < 0){
			throw new VehicleBadRequestException("Readings : One of the vehicle tire pressure is less than 0");
		}
		
		String str = readings.getTimestamp();
		ZonedDateTime readingTimeStamp = ZonedDateTime.parse(str);
		ZonedDateTime present = ZonedDateTime.now(Clock.systemUTC());
		
		if(readingTimeStamp.isAfter(present)){
			throw new VehicleBadRequestException("Readings : Vehicle Time Stamp is in the future.");
		}
		
		
	
		//engineRpm > readlineRpm p:HIGH
		if(readings.getEngineRpm() > vehicleDetails.getRedlineRpm()){
			
			Alert alert = new Alert();
			alert.setAlertVin(readings.getVin());
			alert.setAlertTimeStamp(readings.getTimestamp());
			alert.setAlertMessage("Check RPM : EngineRpm = "+ readings.getEngineRpm() + " > RedLineRpm = "+ vehicleDetails.getRedlineRpm());
			alert.setPriority("HIGH");
			repository.addAlert(alert);
			
			System.out.println("Check RPM, Alert=HIGH : EngineRpm = "+ readings.getEngineRpm() + " > RedLineRpm = "+ vehicleDetails.getRedlineRpm() + "VIN : "+ readings.getVin());
			
			
			//Sending Email Alert
			
			MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
            helper.setFrom("from mail");
            helper.setTo("to mail");
            helper.setSubject("Vehicle Alert : "+alert.getPriority());
            String text = alert.getAlertMessage() + ". Vin Number : " + vehicleDetails.getVin() +" Vehicle Details : " + vehicleDetails.getMake()+" "+ vehicleDetails.getModel();
            
            helper.setText(text, true);
            mailSender.send(message);
	
			
		}
		
		//fuelVolume < 10% of maxFuelVolume p:MEDIUM
		if(readings.getFuelVolume() < (vehicleDetails.getMaxFuelVolume()/10)){
			
			Alert alert = new Alert();
			alert.setAlertVin(readings.getVin());
			alert.setAlertTimeStamp(readings.getTimestamp());
			alert.setAlertMessage("Check Fuel: Vehicle Fuel Volume = "+ readings.getFuelVolume() + " less than 10% of Max Fuel volume");
			alert.setPriority("MEDIUM");
			repository.addAlert(alert);
			
			System.out.println("Check Fuel, Alert=MEDIUM : VehicleFuelVolume = "+ readings.getFuelVolume() + " < MaxFuelVolume "+ (vehicleDetails.getMaxFuelVolume()/10)+ "VIN : "+ readings.getVin() );
		}
		
		//tire pressure of any tire < 32psi || > 36psi p:LOW
		if(readings.getTires().getFrontLeft() < 32 || readings.getTires().getFrontLeft() >36
			|| readings.getTires().getFrontRight() <32 || readings.getTires().getFrontRight() > 36 
			|| readings.getTires().getRearLeft() < 32 || readings.getTires().getRearLeft() >36
			|| readings.getTires().getRearRight() <32 || readings.getTires().getRearRight() >36){
			
			Alert alert = new Alert();
			alert.setAlertVin(readings.getVin());
			alert.setAlertTimeStamp(readings.getTimestamp());
			alert.setAlertMessage("Check Air Pressure : Air < 32 || Air >36 ");
			alert.setPriority("LOW");
			repository.addAlert(alert);
			
			System.out.println("Check Air Pressure, Alert = Low : Air < 32 || Air >36 " + readings.getTires().toString() + "VIN : "+ readings.getVin());
			
		}
		
		//engineCoolantLow = true || checkEngineLightOn = true p:Low
		if(readings.isEngineCoolantLow() == true || readings.isCheckEngineLightOn() == true){
			
			Alert alert = new Alert();
			alert.setAlertVin(readings.getVin());
			alert.setAlertTimeStamp(readings.getTimestamp());
			alert.setAlertMessage("Check Coolent or Light : EngineCoolentLow = "+readings.isEngineCoolantLow()+", CheckEngineLight = "+ readings.isCheckEngineLightOn());
			alert.setPriority("LOW");
			repository.addAlert(alert);
			
			System.out.println("Check Coolent or Light, Alert = Low : EngineCoolentLow = "+readings.isEngineCoolantLow()+", CheckEngineLight = "+ readings.isCheckEngineLightOn() + "VIN : "+ readings.getVin() );
		}
	}
	
}

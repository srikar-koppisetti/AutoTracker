package com.srikar.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srikar.entity.Alert;
import com.srikar.entity.Readings;
import com.srikar.entity.Vehicle;
import com.srikar.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleRepository repository;
	
	@Override
	@Transactional
	public List<Vehicle> findAll() {
		return repository.findAll();
	}


	@Override
	@Transactional
	public Vehicle update(String vin, Vehicle vehicle) {
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
		alerts(readings);
		return repository.addReadings(readings);
	}


	@Override
	@Transactional
	public void alerts(Readings readings) {
		String vinNumb = readings.getVin();
		Vehicle vehicleDetails = repository.findOne(vinNumb);
		
		
		
		//engineRpm > readlineRpm p:HIGH
		if(readings.getEngineRpm() > vehicleDetails.getRedlineRpm()){
			
			Alert alert = new Alert();
			alert.setAlertVin(readings.getVin());
			alert.setAlertTimeStamp(readings.getTimestamp());
			alert.setAlertMessage("Check RPM : EngineRpm = "+ readings.getEngineRpm() + " > RedLineRpm = "+ vehicleDetails.getRedlineRpm());
			alert.setPriority("HIGH");
			repository.addAlert(alert);
			
			System.out.println("Check RPM, Alert=HIGH : EngineRpm = "+ readings.getEngineRpm() + " > RedLineRpm = "+ vehicleDetails.getRedlineRpm() + "VIN : "+ readings.getVin());
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

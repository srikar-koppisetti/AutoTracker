package com.srikar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srikar.entity.Alert;
import com.srikar.entity.Readings;
import com.srikar.entity.Vehicle;
import com.srikar.service.VehicleService;

@RestController
public class VehicleController {
	
	@Autowired
	private VehicleService service;
	
	//Get all Vehicle details
	@RequestMapping(method = RequestMethod.GET, value="vehicles",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Vehicle> findAll(){
		return service.findAll();
	}
	
	
	//Updates existing vehicles if already available else creates new ones    
	@RequestMapping(method = RequestMethod.PUT, value="vehicles",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Vehicle> update(@RequestBody List<Vehicle> vehicle){
		for(int i=0; i<vehicle.size();i++){
			Vehicle newVehicle = vehicle.get(i);
			String vin = newVehicle.getVin();
		try{
			service.update(vin,newVehicle);
		}catch(Exception e){
			System.out.println(e);
			//service.vehicleQuarantine((VehicleQuarantine) newVehicle);
		}
		}
		return vehicle;
	}
	
	
	//Adds new readings 
	@RequestMapping(method = RequestMethod.POST, value="readings",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Readings addReadings(@RequestBody Readings readings){
		try{
		service.addReadings(readings);
		}
		catch (Exception e){
			System.out.println(e);
		}
		return readings;
	}
	
	//get alerts of each vin
	@RequestMapping(method = RequestMethod.GET, value = "alerts/{vin}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Alert> findAlerts(@PathVariable("vin") String carVin){
		
		return service.findAlerts(carVin);	
	}
	
	//high alerts in last two hours
	@RequestMapping(method = RequestMethod.GET, value="highAlerts",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Alert> highAlerts(){
		return service.highAlerts();
	}
	
	//readings of vehicle with vin and time
	@RequestMapping(method = RequestMethod.GET, value="readings/{vin}/time/{min}",
				produces  = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Readings> getReadings(@PathVariable("vin")  String carVin, @PathVariable("min") int minutes){
		return service.getReadings(carVin,minutes);
	}
	
	
	
}

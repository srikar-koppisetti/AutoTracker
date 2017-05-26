package com.srikar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srikar.entity.Readings;
import com.srikar.entity.Vehicle;
import com.srikar.service.VehicleService;

@RestController
public class VehicleController {
	
	@Autowired
	private VehicleService service;
	
	
	@RequestMapping(method = RequestMethod.GET, value="vehicles",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Vehicle> findAll(){
		return service.findAll();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value="vehicles",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Vehicle> update(@RequestBody List<Vehicle> vehicle){
		for(int i=0; i<vehicle.size();i++){
			Vehicle newVehicle = vehicle.get(i);
			String vin = newVehicle.getVin();
			service.update(vin,newVehicle);
		}
		return vehicle;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="readings",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Readings addReadings(@RequestBody Readings readings){
		service.addReadings(readings);
		return readings;
	}
	
	
	
}

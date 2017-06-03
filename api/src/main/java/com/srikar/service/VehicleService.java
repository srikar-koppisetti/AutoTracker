package com.srikar.service;


import java.util.List;

import javax.mail.MessagingException;

import com.srikar.entity.Alert;
import com.srikar.entity.Readings;
import com.srikar.entity.Vehicle;

public interface VehicleService {
	
	List<Vehicle> findAll();
	
	Readings addReadings(Readings readings);
	
	Vehicle update(String vin, Vehicle vehicle);
	
	void alerts(Readings readings) throws MessagingException;

	List<Alert> findAlerts(String vin);

	//VehicleQuarantine vehicleQuarantine(VehicleQuarantine vehicleQuarantine);

}

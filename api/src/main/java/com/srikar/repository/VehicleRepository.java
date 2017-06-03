package com.srikar.repository;

import java.util.List;

import com.srikar.entity.Alert;
import com.srikar.entity.Readings;
import com.srikar.entity.Vehicle;

public interface VehicleRepository {
	
	List<Vehicle> findAll();
	
	Vehicle create(Vehicle vehicle);
	
	Vehicle update(Vehicle vehicle);
	
	Vehicle findOne(String vin);

	Readings addReadings(Readings readings);

	Alert addAlert(Alert alert);

	List<Alert> findAlerts(String vin);

	List<Alert> highAlerts();

	List<Readings> getReadings(String vin, int minutes);

	//VehicleQuarantine addVehicleQuarantine(VehicleQuarantine vehicleQuarantine);
	
	
}

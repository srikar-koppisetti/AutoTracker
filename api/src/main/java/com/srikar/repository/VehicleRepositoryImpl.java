package com.srikar.repository;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.srikar.entity.Alert;
import com.srikar.entity.Readings;
import com.srikar.entity.Vehicle;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

	@PersistenceContext
    private EntityManager em;
	
	//Finds all vehicles
	@Override
	public List<Vehicle> findAll() {
		TypedQuery<Vehicle> query = em.createNamedQuery("Vehicle.findAll", Vehicle.class);
        return query.getResultList();
	}

	//Create vehicle  
	@Override
	public Vehicle create(Vehicle vehicle) {
		em.persist(vehicle);
		return vehicle;
	}

	//Update existing 
	@Override
	public Vehicle update(Vehicle vehicle) {
		return em.merge(vehicle);
	}

	//Find vehicle with particular ID
	@Override
	public Vehicle findOne(String vin) {
		return em.find(Vehicle.class, vin);
	}

	//Create reading
	@Override
	public Readings addReadings(Readings readings) {
		//em.persist(readings.getTires());
		em.persist(readings);
		return readings;
	}

	//Create alert
	@Override
	public Alert addAlert(Alert alert) {
		em.persist(alert);
		return alert;
	}

	
	//get alerts of each vin
	@Override
	public List<Alert> findAlerts(String vin) {
		
		TypedQuery<Alert> query = em.createNamedQuery("Alert.findAlerts", Alert.class);
		query.setParameter("pVin", vin);
		
        return query.getResultList();
	}

	//high alerts in last two hours
	@Override
	public List<Alert> highAlerts() {
		TypedQuery<Alert> query = em.createNamedQuery("Alert.highAlerts",Alert.class);
		ZonedDateTime last = ZonedDateTime.now(Clock.systemUTC()).minus(1, ChronoUnit.HOURS);
		query.setParameter("pTime", last );
		query.setParameter("pPriority", "HIGH");
		return query.getResultList();
	}

	//readings of vehicle with vin and time
	@Override
	public List<Readings> getReadings(String vin, int minutes) {
		TypedQuery<Readings> query = em.createNamedQuery("Readings.getReadings", Readings.class);
		ZonedDateTime last = ZonedDateTime.now(Clock.systemUTC()).minus(minutes, ChronoUnit.MINUTES);
		query.setParameter("pTime", last);
		query.setParameter("pVin", vin);
		return query.getResultList();
	}

	/*@Override
	public VehicleQuarantine addVehicleQuarantine(VehicleQuarantine vehicleQuarantine) {
		em.persist(vehicleQuarantine);		
		return vehicleQuarantine;
	}*/
	
	
	
}

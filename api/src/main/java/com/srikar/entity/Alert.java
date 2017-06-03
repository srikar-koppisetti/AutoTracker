package com.srikar.entity;



import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Alert.findAlerts",
                query = "SELECT alert FROM Alert alert WHERE alert.alertVin = :pVin")
    
})
public class Alert {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String alertVin;
	private ZonedDateTime alertTimeStamp;
	private String alertMessage;
	private String priority;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlertVin() {
		return alertVin;
	}
	public void setAlertVin(String alertVin) {
		this.alertVin = alertVin;
	}
	
	public ZonedDateTime getAlertTimeStamp() {
		return alertTimeStamp;
	}
	public void setAlertTimeStamp(ZonedDateTime alertTimeStamp) {
		this.alertTimeStamp = alertTimeStamp;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}

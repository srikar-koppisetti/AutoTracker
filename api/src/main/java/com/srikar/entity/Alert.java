package com.srikar.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alert {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String alertVin;
	private String alertTimeStamp;
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
	
	public String getAlertTimeStamp() {
		return alertTimeStamp;
	}
	public void setAlertTimeStamp(String alertTimeStamp) {
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

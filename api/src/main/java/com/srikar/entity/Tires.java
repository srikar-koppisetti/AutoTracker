package com.srikar.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tires {
	
	@Id
	@GeneratedValue
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int frontLeft;
	private int frontRight;
	private int rearLeft;
	private int rearRight;
	
	
	public int getFrontLeft() {
		return frontLeft;
	}
	public void setFrontLeft(int frontLeft) {
		this.frontLeft = frontLeft;
	}
	public int getFrontRight() {
		return frontRight;
	}
	public void setFrontRight(int frontRight) {
		this.frontRight = frontRight;
	}
	public int getRearLeft() {
		return rearLeft;
	}
	public void setRearLeft(int rearLeft) {
		this.rearLeft = rearLeft;
	}
	public int getRearRight() {
		return rearRight;
	}
	public void setRearRight(int rearRight) {
		this.rearRight = rearRight;
	}
	
	@Override
	public String toString() {
		return "Tires [id=" + id + ", frontLeft=" + frontLeft + ", frontRight=" + frontRight + ", rearLeft=" + rearLeft
				+ ", rearRight=" + rearRight + "]";
	}
	
	
	
	
	
}

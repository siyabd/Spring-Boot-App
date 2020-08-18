package com.digital.academy.cloudDime.entity;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "AttendanceRegister")
public class AttendanceRegister {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attendanceRegisterId")
	private int attendanceRegisterId;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="userId")
	private User user;
	
	
	private int eventId ;
	
	private double receivedPoints;
	private Timestamp outTime;
	
	
	private int timeSpent;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="createDate")
	private Timestamp createDate;
	
	public AttendanceRegister() {
		
	}

	public AttendanceRegister(User user,int event, double receivedPoints, Timestamp createDate) {
		super();
		this.user = user;
		this.eventId = event;
		this.receivedPoints = receivedPoints;
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getEvent() {
		return eventId;
	}

	public void setEvent(int event) {
		this.eventId = event;
	}

	public double getReceivedPoints() {
		return receivedPoints;
	}

	public void setReceivedPoints(double receivedPoints) {
		this.receivedPoints = receivedPoints;
	}

	public Timestamp getOutTime() {
		return outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	public int getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(int timeSpent) {
		this.timeSpent = timeSpent;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
	
}

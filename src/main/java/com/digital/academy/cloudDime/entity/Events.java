package com.digital.academy.cloudDime.entity;


import java.sql.Date;
import java.sql.Time;
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
@Table(name = "Events")
public class Events {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "eventID")
	private int eventID;
	
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	
	@Column(name = "location")
	private String location;
	
	@Column(name ="date")
	private Date date;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "startTime")
	Time startTime;
	@Column(name = "endTime")
	Time endTime;
	
	
	@Column(name = "duration")
	private double duration;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="createDate")
	private Timestamp createDate;
	
	@Column(name = "points")
	private double points;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="userID")
	private User user;
	
	public Events() {
	}
	
	public Events(String name, String location,String description, Date date, Time startTime, Time endTime,
			Timestamp createDate, double points, User user,boolean status) {
		super();
		this.name = name;
		this.location = location;
		this.description = description;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createDate = createDate;
		this.points = points;
		this.user = user;
		this.status = status;
	}

	public Events (String name, String location, Date date,double points, Timestamp createDate,
			User user) {
	
		this.name = name;
		this.location = location;
		this.date = date;
		this.createDate = createDate;
		this.user = user;
	}
	public Events (String name, String location,Timestamp createDate2, double points,
			User user) {
	
		this.name = name;
		this.location = location;
		this.user = user;
	}
	
	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	
	
	
}


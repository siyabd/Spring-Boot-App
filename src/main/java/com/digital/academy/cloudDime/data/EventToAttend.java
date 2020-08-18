package com.digital.academy.cloudDime.data;

import java.sql.Date;
import java.sql.Time;

public class EventToAttend {
	
	private String name;
	private String description;
	private String location;
	private Date date;
	private Time startTime;
	private Time endTime;
	private double points;
	private int eventId;
	private String creatorName;
	
	
	public EventToAttend(String name,String description, String location, Date date, Time startTime, Time endTime, double points,int eventId,String creatorName) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.points = points;
		this.eventId = eventId;
		this.creatorName = creatorName;
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
	public double getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	
	
	
	

}

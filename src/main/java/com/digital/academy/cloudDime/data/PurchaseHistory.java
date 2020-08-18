package com.digital.academy.cloudDime.data;

import java.sql.Date;

public class PurchaseHistory {
	
	private String itemName;
	private double points;
	private Date date;
	public PurchaseHistory(String itemName, double points, Date date) {
		super();
		this.itemName = itemName;
		this.points = points;
		this.date = date;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}

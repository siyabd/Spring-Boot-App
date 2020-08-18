package com.digital.academy.cloudDime.data;

public class UserInfo {
	
	private int userId;
	private String name;
	private String surname;
	private double pointsBalance;
	private double pointsSpent;
	private int userTypeId;
	private int status;
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//
	
	public UserInfo(String name, String surname, double pointsBalance, double pointsSpent,int userId,int userTypeId,int status) {
		super();
		this.name = name;
		this.surname = surname;
		this.pointsBalance = pointsBalance;
		this.pointsSpent = pointsSpent;
		this.userId = userId;
		this.userTypeId = userTypeId;
		this.status = status;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public double getPointsBalance() {
		return pointsBalance;
	}
	public void setPointsBalance(double pointsBalance) {
		this.pointsBalance = pointsBalance;
	}
	public double getPointsSpent() {
		return pointsSpent;
	}
	public void setPointsSpent(double pointsSpent) {
		this.pointsSpent = pointsSpent;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getUserTypeId() {
		return userTypeId;
	}


	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", name=" + name + ", surname=" + surname + ", pointsBalance="
				+ pointsBalance + ", pointsSpent=" + pointsSpent + ", userTypeId=" + userTypeId + ", status=" + status
				+ "]";
	}


	
	
	
	
	

}

package com.digital.academy.cloudDime.data;

public class InterestedUser {
	
	private String name;
	private String surname;
	private int userId;
	public InterestedUser(String name, String surname,int userId) {
		super();
		this.name = name;
		this.surname = surname;
		this.userId = userId;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	

}

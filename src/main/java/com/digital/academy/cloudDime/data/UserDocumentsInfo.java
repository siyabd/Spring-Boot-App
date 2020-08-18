package com.digital.academy.cloudDime.data;

import java.sql.Timestamp;



public class UserDocumentsInfo {
	
	private String description;
	private String url;
	private Timestamp createDate;
	private int userId;
	
	
	public UserDocumentsInfo(String description, String url, Timestamp createDate,int userId) {
		super();
		this.description = description;
		this.url = url;
		this.createDate = createDate;
		this.userId = userId;

	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
	

}

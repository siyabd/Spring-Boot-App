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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "UserType")
public class UserType {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userTypeId")
	private int userTypeId;
	@Column(name = "createDate")
	private Timestamp createDate;
	@Column(name = "description")
	private String description;

	public UserType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserType(Timestamp createDate, String description) {
		super();
		this.createDate = createDate;
		this.description = description;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	
	

}

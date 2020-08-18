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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "BusinessProfile")

public class BusinessProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BusinessprofileId")
	private int id;
	@Column(name = "businessType")
	private String businessType;
	@Column(name = "businessName")
	private String businessName;
	@Column(name = "numberOfEmployees")
	private String numberOfEmployees;
	@Column(name = "annualTurnOver")
	private String annualTurnOver;
	@Column(name = "profit")
	private String profit;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "createDate")
	private Timestamp createDate;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	//businessType,businessName,numberOfEmployees,profit,telephone
	public BusinessProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public BusinessProfile(String businessType, String businessName, String numberOfEmployees, String annualTurnOver,
			String profit, String telephone) {
		super();
		this.businessType = businessType;
		this.businessName = businessName;
		this.numberOfEmployees = numberOfEmployees;
		this.annualTurnOver = annualTurnOver;
		this.profit = profit;
		this.telephone = telephone;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBusinessType() {
		return businessType;
	}


	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}


	public String getBusinessName() {
		return businessName;
	}


	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


	public String getNumberOfEmployees() {
		return numberOfEmployees;
	}


	public void setNumberOfEmployees(String numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}


	public String getAnnualTurnOver() {
		return annualTurnOver;
	}


	public void setAnnualTurnOver(String annualTurnOver) {
		this.annualTurnOver = annualTurnOver;
	}


	public String getProfit() {
		return profit;
	}


	public void setProfit(String profit) {
		this.profit = profit;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	
	
	
	
	
	
	
}

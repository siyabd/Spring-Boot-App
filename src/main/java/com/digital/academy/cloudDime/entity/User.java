package com.digital.academy.cloudDime.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private int userId;
	@Column(name = "email",unique = true)
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "approvalDate")
	private Date approvalDate;
	@Column(name = "active")
	private boolean active;
	@Column(name = "status")
	private int status;
	@Column(name = "createDate")
	private Timestamp createDate;
	@Column(name = "notificationToken")
	private String notificationToken;
	
	//creating relationship between the entities
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="userTypeId")
	private UserType userType;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String email, String password,Timestamp createDate) {
		super();
		this.email = email;
		this.password = password;
		this.createDate =createDate;
	}


	public User(String email, String password, Date approvalDate, boolean active,int status,
			Timestamp createDate, String notificationToken) {
		super();
		this.email = email;
		this.password = password;
		this.approvalDate = approvalDate;
		this.active = active;
		this.status = status;
		this.createDate = createDate;
		this.notificationToken = notificationToken;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Date getApprovalDate() {
		return approvalDate;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}



	public String getNotificationToken() {
		return notificationToken;
	}



	public void setNotificationToken(String notificationToken) {
		this.notificationToken = notificationToken;
	}



	public UserType getUserType() {
		return userType;
	}



	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
	

}

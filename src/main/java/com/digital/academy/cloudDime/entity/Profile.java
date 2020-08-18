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
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "Profile")
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="profileId")
	private int profileId;
	//relationships
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	//other variables
	
	@Column(name ="name")
	private String name;
	@Column(name ="surname")
	private String surname;
	@Column(name ="cellphone")
	private String cellphone;

	@Column(name ="createDate")
	private Timestamp createDate;
	
	@Column(name ="dateOfBirth")
	private Date dateOfBirth;
	
	
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Profile(User user, String name, String surname,String cellphone) {
		super();
		this.user = user;
		this.cellphone = cellphone;
		this.name = name;
		this.surname = surname;
		
	}


	public int getProfileId() {
		return profileId;
	}


	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Timestamp createDate) {
		
		
		this.createDate = createDate;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getCellphone() {
		return cellphone;
	}


	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

}

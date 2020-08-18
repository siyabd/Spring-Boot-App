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
@Table(name = "Connections")
public class Connections {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "connectionId")
	private int id;
	@Column(name = "")
	private String specialization;
	@Column(name = "name")
	private String name;
	@Column(name = "createDate")
	private Timestamp createDate;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	public Connections() {
		super();
		
	}
	
	
	public Connections(String specialization, String name) {
		super();
		this.specialization = specialization;
		this.name = name;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

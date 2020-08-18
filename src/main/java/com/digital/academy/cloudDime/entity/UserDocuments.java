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
@Table(name = "UserDocuments")
public class UserDocuments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userDocumentsId")
	private int id;
	@Column(name = "url")
	private String url;
	@Column(name = "fileDescription")
	private String fileDecription;
	@Column(name = "createDate")
	private Timestamp createDate;
	@Column(name = "active")
	private boolean active;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	public UserDocuments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public UserDocuments(String url,String fileDescription, Timestamp createDate,User user) {
		super();
		this.fileDecription = fileDescription;
		this.url = url;
		this.createDate = createDate;
		this.user = user;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public String getFileDecription() {
		return fileDecription;
	}



	public void setFileDecription(String fileDecription) {
		this.fileDecription = fileDecription;
	}
	
	
	
}

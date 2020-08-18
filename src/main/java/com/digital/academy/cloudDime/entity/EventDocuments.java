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
@Table(name = "EventDocuments")
public class EventDocuments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "eventDocumentsId")
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
	@JoinColumn(name = "eventId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Events event;

	public EventDocuments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public EventDocuments(String url,String fileDescription, Timestamp createDate,Events event) {
		super();
		this.fileDecription = fileDescription;
		this.url = url;
		this.createDate = createDate;
		this.event = event;
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
	public Events getEvent() {
		return event;
	}



	public void setEvent(Events event) {
		this.event = event;
	}

	public String getFileDecription() {
		return fileDecription;
	}



	public void setFileDecription(String fileDecription) {
		this.fileDecription = fileDecription;
	}

}

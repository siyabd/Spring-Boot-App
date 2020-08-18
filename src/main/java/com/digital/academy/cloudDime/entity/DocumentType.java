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

@Entity
@Table(name = "DocumentType")
public class DocumentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "documentTypeId")
	private int id;
	@Column(name = "description")
	private String description;
	@Column(name = "createDate")
	private Timestamp createDate;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "userDocumentsId")
	private UserDocuments userDocuments;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "eventDocumentsId")
	private EventDocuments eventDocuments;
	public DocumentType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DocumentType(String description, Timestamp createDate, UserDocuments userDocuments) {
		super();
		this.description = description;
		this.createDate = createDate;
		this.userDocuments = userDocuments;
	}
	
	public DocumentType(String description, Timestamp createDate, EventDocuments eventDocuments) {
		super();
		this.description = description;
		this.createDate = createDate;
		this.eventDocuments = eventDocuments;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	

}

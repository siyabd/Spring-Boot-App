package com.digital.academy.cloudDime.entity;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TransactionalHistory")
public class TransactionalHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transactionalHistory_Id")
	private int transactionalHistoryID;
	
	@Column(name ="spentPoints")
	double spentPoints;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="createDate")
	private Timestamp createDate;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="userID")
	private User user;
	
	//@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL ,targetEntity = Items.class,mappedBy="transactionalHistory")
    //public List<Items> items;
	//private Items items;
	
	
	@ManyToOne
	@JoinColumn(name = "itemId")
	private Items items;

	public TransactionalHistory() {
		
	}

	public TransactionalHistory(double spentPoints, Timestamp createDate, User user,Items items) {
		this.spentPoints = spentPoints;
		this.createDate = createDate;
		this.user = user;
		this.items = items;
	}

	public int getTransactionalHistoryID() {
		return transactionalHistoryID;
	}

	public double getSpentPoints() {
		return spentPoints;
	}

	public void setSpentPoints(double spentPoints) {
		this.spentPoints = spentPoints;
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

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public void setTransactionalHistoryID(int transactionalHistoryID) {
		this.transactionalHistoryID = transactionalHistoryID;
	}

	
	

}

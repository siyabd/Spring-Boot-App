package com.digital.academy.cloudDime.entity;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Items")
@Table(name = "Items")
public class Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "itemId")
	private int id;
	@Column(name = "itemName")
	private String itemName;
	@Column(name = "points")
	private double points;
	@Column(name = "numberOfPurchase")
	private int numberOfPurchase;
	
	/*@ManyToOne
	@JoinColumn(name = "transactionalHistoryId")
	private TransactionalHistory transactionalHistory;*/
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL ,targetEntity = TransactionalHistory.class,mappedBy="items")
     private List<TransactionalHistory> transactionHistory;
	
	
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Items(String itemName, double points, int numberOfPurchase) {
		super();
		this.itemName = itemName;
		this.points = points;
		this.numberOfPurchase = numberOfPurchase;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public int getNumberOfPurchase() {
		return numberOfPurchase;
	}

	public void setNumberOfPurchase(int numberOfPurchase) {
		this.numberOfPurchase = numberOfPurchase;
	}

	
	
	
	
	

}

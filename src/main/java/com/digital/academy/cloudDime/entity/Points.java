package com.digital.academy.cloudDime.entity;


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
@Table(name = "Points")

public class Points {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pointsId")
	private int pointsId;
	@Column(name = "balance")
	double balance;
	@Column(name = "pointsSpent")
	double pointsSpent;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	
	public Points() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Points(double balance, double pointsSpent, User user) {
		super();
		this.balance = balance;
		this.pointsSpent = pointsSpent;
		this.user = user;
	}

	public int getPointsId() {
		return pointsId;
	}
	public void setPointsId(int pointsId) {
		this.pointsId = pointsId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getPointsSpent() {
		return pointsSpent;
	}
	public void setPointsSpent(double pointsSpent) {
		this.pointsSpent = pointsSpent;
	}
	
	
	
	

}

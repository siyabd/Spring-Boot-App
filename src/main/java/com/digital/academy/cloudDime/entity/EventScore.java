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
import javax.persistence.OneToOne;
import javax.persistence.Table;


//gets the score of a user based on events;
@Entity
@Table(name = "EventScore")
public class EventScore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eventScoreID;
	
	@Column(name ="score")
	private double score;
	
	@Column(name ="createDate")
	private Timestamp createDate;
	
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="userID")
	private User user;

	public EventScore() {
		
	}

	public EventScore(double score, Timestamp createDate, User user) {
		this.score = score;
		this.createDate = createDate;
		this.user = user;
	}

	public int getEventScoreID() {
		return eventScoreID;
	}

	public void setEventScoreID(int eventScoreID) {
		this.eventScoreID = eventScoreID;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
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

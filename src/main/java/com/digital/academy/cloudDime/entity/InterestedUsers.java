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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "InterestedUsers")

public class InterestedUsers{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "interestedUsersId")
	private int interestedUsersId;
	@Column(name = "createDate")
	private Timestamp createDate;
	@Column(name = "isGoing")
	private boolean isGoing;//default is true,will be false when you change your mind about going to the event
	@Column(name = "hasAttended")
	private boolean hasAttended;//default is false,will be true if the user attends the event
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name ="userId")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name ="eventId")
	private Events event;
	
	

	public InterestedUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InterestedUsers(Timestamp createDate, User user, Events event) {
		super();
		this.createDate = createDate;
		this.user = user;
		this.event = event;
	}

	public int getInterestedUsersId() {
		return interestedUsersId;
	}

	public void setInterestedUsersId(int interestedUsersId) {
		this.interestedUsersId = interestedUsersId;
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

	public Events getEvent() {
		return event;
	}

	public void setEvent(Events event) {
		this.event = event;
	}

	public boolean isGoing() {
		return isGoing;
	}

	public void setGoing(boolean isGoing) {
		this.isGoing = isGoing;
	}

	public boolean isHasAttended() {
		return hasAttended;
	}

	public void setHasAttended(boolean hasAttended) {
		this.hasAttended = hasAttended;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
}
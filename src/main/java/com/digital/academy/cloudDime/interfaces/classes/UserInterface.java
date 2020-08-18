package com.digital.academy.cloudDime.interfaces.classes;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;

import com.digital.academy.cloudDime.data.EventToAttend;
import com.digital.academy.cloudDime.data.UserDocumentsInfo;
import com.digital.academy.cloudDime.data.UserInfo;
import com.digital.academy.cloudDime.entity.Achievements;
import com.digital.academy.cloudDime.entity.AttendanceRegister;
import com.digital.academy.cloudDime.entity.BusinessProfile;
import com.digital.academy.cloudDime.entity.Connections;
import com.digital.academy.cloudDime.entity.Points;
import com.digital.academy.cloudDime.entity.Profile;
import com.digital.academy.cloudDime.entity.User;
import com.digital.academy.cloudDime.entity.UserDocuments;

public interface UserInterface {
	
	public UserInfo registerUser(Profile profile);
	public UserInfo login(User user);
	public UserDocuments uploadFile(String url,String fileName,int userId);
	public ArrayList<UserDocumentsInfo> getUserDocuments(int userId);
	public ArrayList<Achievements> getUserAchivements(int userId);
	public Profile getEventOrganiser(int userId);
	public ArrayList<Connections> getConnections(int userId);
	public String resetPassword(String email,String password);
	public ArrayList<AttendanceRegister> eventsAttended(int userId);
	public ArrayList<EventToAttend> getEventsToAttend(int userId);//interested events
	public boolean isGoingToEvent(int userId,int eventId);
	public String getOTP(String email,String otp);
	
	public String removeInteretesdEvent(int userId,int eventId);
	
	//public ArrayList<User> getRegisteredUsers();
	//public ArrayList<Profile> getProfiles();
	
	public String addBusinessProfile(BusinessProfile businessProfile,int userId);
	public String addConnection(Connections connection,int userId);
	public Points spendPoint(int points,int userId);
	public String addAchievements(Achievements achievements,int userid);
	public ArrayList<BusinessProfile> getUserBusinesses(int userId);
	


}

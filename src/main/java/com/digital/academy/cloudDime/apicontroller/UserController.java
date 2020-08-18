package com.digital.academy.cloudDime.apicontroller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.academy.cloudDime.data.EventToAttend;
import com.digital.academy.cloudDime.data.UserDocumentsInfo;
import com.digital.academy.cloudDime.data.UserInfo;
import com.digital.academy.cloudDime.entity.Achievements;
import com.digital.academy.cloudDime.entity.AttendanceRegister;
import com.digital.academy.cloudDime.entity.BusinessProfile;
import com.digital.academy.cloudDime.entity.Connections;
import com.digital.academy.cloudDime.entity.Profile;
import com.digital.academy.cloudDime.entity.TransactionalHistory;
import com.digital.academy.cloudDime.entity.User;
import com.digital.academy.cloudDime.implementation.EmailServices;
import com.digital.academy.cloudDime.implementation.UserService;

@RestController
@RequestMapping("cloudDime/User")

public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private EmailServices emailServices;
	
	@PostMapping("register")
	public UserInfo registerUser(@RequestBody Profile profile) {
		//accepts the profile of user as an object,if user is succesfully registered
		//relevant user information is returned
		return userService.registerUser(profile);
	}
	@PostMapping("login")
	public UserInfo login(@RequestBody User user) {
		
		//accepts the user login details  as an object,if user is succesfully logged in
		//relevant user information is returned.
		return userService.login(user);
	}
	
	@PostMapping("resetpassword")
	public String resetPassword(@RequestParam String email,@RequestParam String password) {
		return userService.resetPassword(email,password);
		
	}
	
	@GetMapping("uploadedDocuments")
	public ArrayList<UserDocumentsInfo> uploadedDocuments(@RequestParam int userId) {
		return userService.getUserDocuments(userId);
	}
	@GetMapping("getEventOrganiser")
	public Profile getEventOrganiser(@RequestParam int userId) {
		
		return userService.getEventOrganiser(userId);
		
	}
	
	@GetMapping("geteventstoattend")
	public ArrayList<EventToAttend> getEvetsToAttend(int userId){
		return userService.getEventsToAttend(userId);
	}

	@GetMapping("getConnections")
	public ArrayList<Connections> getConnection(@RequestParam int userId){
		
		return userService.getConnections(userId);
	}
	
	@PostMapping("addBusiness")
	public String addBusiness(@RequestParam String businessType,@RequestParam String businessName,@RequestParam String numberOfEmployees
		,@RequestParam String annualTurnOver,@RequestParam String profit,@RequestParam String telephone,@RequestParam int userId) {
		
		BusinessProfile businessProfile = new BusinessProfile(businessType,businessName,numberOfEmployees,annualTurnOver,profit,telephone);
		
		return userService.addBusinessProfile(businessProfile,userId);
	}
	
	@PostMapping("getUsers")
	public String getBusinesses(@RequestParam int userId) {
		return null;
	}
	
	@PostMapping("addConnect")
	public String addConnection(@RequestParam String specialization,@RequestParam String name,@RequestParam int userId) {
		Connections connect = new Connections(specialization,name);
		
		userService.addConnection(connect,userId);
		return "Connection succesfully added.";
		
	}
	
	@PostMapping("addAchievements")
	public String addAchievements(@RequestParam String description,@RequestParam String achievementType,@RequestParam String date,@RequestParam int userId) throws ParseException {
		 
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date actualDate = format.parse(date);
		Achievements achievements = new Achievements(actualDate,description,achievementType);
		
		userService.addAchievements(achievements, userId);
		return "Achievements succesfully added.";
	}
	
	@GetMapping("getBusinesses")
	public ArrayList<BusinessProfile> getBusinessProfile(@RequestParam int userId) {
		return userService.getUserBusinesses(userId);
	}
	
	@GetMapping("getUserAchievements")
	public ArrayList<Achievements> getAchievements(int userId){
		
		return userService.getUserAchivements(userId);
	}
	
	//user spending points
	@PostMapping("deductpoints")
	public String deductPoints(@RequestParam int userID,@RequestParam int itemId){
		int id = userID;
		return userService.purchaseItem(id,itemId);
	}
	
	//getting the transactional history of a user
	@GetMapping("gethistory")
	public ArrayList<TransactionalHistory> getHistory(@RequestParam int userID){
		User user = userService.getUser(userID);
		return userService.getHistory(user);
	}
	
	@GetMapping("attendanceRegister")
	public ArrayList<AttendanceRegister> getEventsAttended(@RequestParam int userId){
		return userService.eventsAttended(userId);
	}
	
	@GetMapping("getotp")
	public String sendOTP(@RequestParam String email,@RequestParam String otp) {
		//String emai,String subject,String mailBody
		
		String message = ""+otp;
		emailServices.sendEmail(email, "Password Reset : OTP", "toger");
		
		return null;
	}
	
	//String removeInteretesdEvent(int userId, int eventId)
	
	@PostMapping("removeInteretesdEvent")
	public String removeInteretesdEvent(@RequestParam int userId,@RequestParam int eventId) {
		return userService.removeInteretesdEvent(userId, eventId);
	}
	
	
}

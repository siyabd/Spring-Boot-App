package com.digital.academy.cloudDime.implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.academy.cloudDime.data.CreateDate;
import com.digital.academy.cloudDime.data.UserDocumentsInfo;
import com.digital.academy.cloudDime.entity.Events;
import com.digital.academy.cloudDime.entity.Profile;
import com.digital.academy.cloudDime.entity.User;
import com.digital.academy.cloudDime.entity.UserDocuments;
import com.digital.academy.cloudDime.repository.DocumentTypeRepository;
import com.digital.academy.cloudDime.repository.UserDocumentsRepository;
import com.digital.academy.cloudDime.repository.EventsRepository;
import com.digital.academy.cloudDime.repository.ProfileRepository;
import com.digital.academy.cloudDime.repository.UserRepository;
import com.digital.academy.cloudDime.interfaces.classes.AdminInterface;

@Component
public class AdminService implements AdminInterface{

	@Autowired
	private UserRepository userRepo;
	@Autowired
	EventService eventService;
	@Autowired
	EventsRepository eventsRepo;
	@Autowired
	private UserDocumentsRepository userDocumentsRepository;
	@Autowired
	private EmailServices emailServices;
	@Autowired
	private ProfileRepository prepository;
	
	
	public String approveUsers(String email) {
	
		String message = "";
		
		User user = userRepo.findByEmail(email);
		
		if(user!=null) {
			user.setStatus(1);
			user.setApprovalDate(CreateDate.createDate());
			userRepo.save(user);
			
			Profile profile = prepository.findByUser(user);
			//Profile findByUser(User user);
			String emailMessage = "Hi "+profile.getName()+", Congradulations "+profile.getName()+" your account has been confirmed,now you can you use the app \n\nRegards Clouddime team";
			emailServices.sendEmail(user.getEmail(),"CloudDime event confirmation", emailMessage);
			
			
			message="User has been approved";		
		}else {
			message = "User does not exist";
		}
		return message;
	}
	
	public ArrayList<User> getUsersNotApproved(){
		
		ArrayList<User> notApprovedUsers=userRepo.findByStatus(0);
		return notApprovedUsers;
	}
	public ArrayList<User> getApproved(){
		ArrayList<User> approvedUsers=userRepo.findByStatus(1);		
		return approvedUsers;
	}
	//Calculating the total number of users that are not approved 
	public Long totalNotApprovedUsers() {		
		return userRepo.countByApprovalDateNull();
	}
	
	//Calculating the total number of users that approved 
	public Long totalApprovedUsers() {		
		return userRepo.countByApprovalDateNotNull();
	}
	@Override
	public ArrayList<UserDocuments> retrieveAllDocuments() {
		// TODO Auto-generated method stub
		ArrayList<UserDocuments> allDocumnets = (ArrayList<UserDocuments>)userDocumentsRepository.findAll();
		
		return allDocumnets;
	}
	@Override
	public ArrayList<UserDocumentsInfo> retrieveUserDocuments(String email) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByEmail(email);
		ArrayList<UserDocuments> allDocuments = (ArrayList<UserDocuments>)userDocumentsRepository.findAll();
		ArrayList<UserDocumentsInfo> documentsList = new ArrayList<>();
		
		for(UserDocuments document : allDocuments) {
			if(document.getUser().getEmail().equals(email)) {
				
				UserDocumentsInfo userDocument = new UserDocumentsInfo(document.getFileDecription(),document.getUrl(),document.getCreateDate(),
						document.getUser().getUserId());
				
				documentsList.add(userDocument);
			}
		}
		
		return documentsList;
	}
	@Override
	public String approveEvent(int eventId) {
		// TODO Auto-generated method stub
		String message = "Not approved";
		
		Events event = eventService.getEvent(eventId);
		if(event != null) {
			event.setStatus(true);
			eventsRepo.save(event);
			String creatorEmail = event.getUser().getEmail();
			message = "Approved";
			
			Profile profile = prepository.findByUser(event.getUser());
			//Profile findByUser(User user);
			String emailMessage = "Hi "+profile.getName()+"\n Your request to host an event called "+event.getName()+" has been confirmed \n\nRegards Clouddime Team ";
			emailServices.sendEmail(creatorEmail,"CloudDime event confirmation", emailMessage);
		}
			 
		return message;
	}

	@Override
	public String rejectuser(String userEmail,String reasonToReject) {
		String message = "User not kicked out,user not found";
		
		User user = userRepo.findByEmail(userEmail);
		
		if(user != null) {
			user.setStatus(2);
			userRepo.save(user);
			Profile profile = prepository.findByUser(user);
			//Profile findByUser(User user);
			String emailMessage = "Hi "+profile.getName()+", Your account has been suspended reaon being "+reasonToReject+"\n\n Regards CloudDime Team";
			emailServices.sendEmail(userEmail,"CloudDime event confirmation", emailMessage);
			message = "User kicked out reason being :\n"+reasonToReject;
		}
	
		return message;		
	}
	
	
}

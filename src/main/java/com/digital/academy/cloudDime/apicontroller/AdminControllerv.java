package com.digital.academy.cloudDime.apicontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.digital.academy.cloudDime.data.UserDocumentsInfo;
import com.digital.academy.cloudDime.entity.User;
import com.digital.academy.cloudDime.entity.UserDocuments;
import com.digital.academy.cloudDime.implementation.AdminService;


@RestController
@RequestMapping("cloudDime/Admin")
public class AdminControllerv {
	@Autowired
	AdminService adminService;
	
	
	@PostMapping("approveuser")
	public String approveUser(@RequestParam  String email) {
		return adminService.approveUsers(email);
	}
	
	@PostMapping("approveevnet")
	public String approveEvnt(int eventId) {
		return adminService.approveEvent(eventId);
	}
	
	@GetMapping("notapproved")
	public ArrayList<User> getNotApprovedUsers() {
		return adminService.getUsersNotApproved();
	}
	@GetMapping("approved")
	public ArrayList<User> getApprovedUsers() {
		return adminService.getApproved();
	}
	@GetMapping("notapprovedtotal")
	public long totalNotApproved() {
		
		return adminService.totalNotApprovedUsers();
		
	}
	
	@GetMapping("approvedtotal")
	public Long totalApproved() {
		return adminService.totalApprovedUsers();
	}

	@GetMapping("allusersdocuments")
	public ArrayList<UserDocuments> getAllDocuments(){
		return adminService.retrieveAllDocuments();
	}
	
	@GetMapping("getusersdocuments")
	public ArrayList<UserDocumentsInfo> getUserDocuments(@RequestParam String email){
		return adminService.retrieveUserDocuments(email);
	}
	
	@GetMapping("rejectuser")
	public String rejectUser(@RequestParam String email,@RequestParam String reasonToReject) {
		return adminService.rejectuser(email,reasonToReject);
	}

}

package com.digital.academy.cloudDime.interfaces.classes;

import java.util.ArrayList;

import com.digital.academy.cloudDime.data.UserDocumentsInfo;
import com.digital.academy.cloudDime.entity.UserDocuments;

public interface AdminInterface {
	
	public ArrayList<UserDocuments> retrieveAllDocuments();
	public ArrayList<UserDocumentsInfo> retrieveUserDocuments(String email);
	public String approveEvent(int eventId);
	public String rejectuser(String email,String reasonToReject);

}

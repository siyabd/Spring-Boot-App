package com.digital.academy.cloudDime.interfaces.classes;

import java.util.ArrayList;
import com.digital.academy.cloudDime.data.InterestedUser;
import com.digital.academy.cloudDime.entity.EventDocuments;
import com.digital.academy.cloudDime.entity.Events;
import com.digital.academy.cloudDime.entity.User;

public interface EventsInterface {
	public String createEvent(Events event);
	public ArrayList<Events> getAvailableEvents();
	public double eventScore(int userId);
	public ArrayList<Events> eventFilter(String branch);
	public String createEventScore(User user);
	public String addInterestedUser(int eventId,int userId);
	public ArrayList<InterestedUser> getAllInterestedUsers(int eventId);
	public ArrayList<Events> getEventsToBeHosted();
	public ArrayList<Events> eventsCreatedByUser (int userId);
	
	public EventDocuments uploadFileForEvent(String url,String documentDescription,int eventId);
	
}

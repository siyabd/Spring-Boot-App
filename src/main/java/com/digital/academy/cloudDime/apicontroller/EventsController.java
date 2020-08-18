package com.digital.academy.cloudDime.apicontroller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.digital.academy.cloudDime.data.CreateDate;
import com.digital.academy.cloudDime.data.EventToAttend;
import com.digital.academy.cloudDime.data.InterestedUser;
import com.digital.academy.cloudDime.entity.Events;
import com.digital.academy.cloudDime.entity.User;
import com.digital.academy.cloudDime.implementation.EventService;
import com.digital.academy.cloudDime.implementation.UserService;

/*
 *API for users to create events,update events score,and retrieve events
 *User id is used as a key to query,insert,and update events
 */


@RestController
@RequestMapping("cloudDime/User")
public class EventsController {
	@Autowired
	EventService eventService;
	@Autowired
	private UserService userService;
	
	/*
	 * This methods create a new events and invokes a method that persist the added events into the
	 * database,it receives all the data needed to create an event from android through Volley request
	 * 
	 */
	@PostMapping("registerevents")
	public String add(@RequestParam String name,@RequestParam String location,@RequestParam String description,@RequestParam Date date,@RequestParam Time startTime,@RequestParam Time endTime
			,@RequestParam double points,@RequestParam int userID) {
		
		//finding the user
		User user = userService.getUser(userID);
		/*
		 * Object used to store events details in a table,constructor also accepts a boolean value which represents the status
		 * of the event.By default the status of the event is false which means the admin has not approved the event yet,value will
		 * be changed to true once the administrator approves the event
		 */
		Events event =new Events (name, location,description,date,startTime,endTime,CreateDate.createDate(),points ,user,false) ;
		return eventService.createEvent(event);
	}
	
	//Retrieves all the upcoming events stored in the database
	@GetMapping("getevents")
	public ArrayList<Events> getEvents(@RequestParam int userId){
		
		return eventService.getEvents(userId);
		
	}
	
	@GetMapping("geteventattendees")
	public ArrayList<InterestedUser> getEventAttendees(int eventId){
		return eventService.getAllInterestedUsers(eventId);
	}
	
	@PostMapping("addinteresteduser")
	public String addInterestedUser(int eventId,int userId) {
		return eventService.addInterestedUser(eventId, userId);
	}
	
	@PostMapping("addscore")
	public String add(@RequestParam int userID) {
		User user = userService.getUser(userID);
		return eventService.createEventScore(user);
	}
	
	/*
	 * Ratings from attendees,for thumbs the score for the event creator increments and thumbs down
	 * the score decrease.thumbs method is for user satisfaction and thumbs down is for user unsatisfactory
	 */
	@PostMapping("thumbsup")
	public String thumbsUp(@RequestParam int userId) {
		
		return eventService.thumbsUpScore(userId);
	}
	@PostMapping("thumbsdown")
	public String thumbsDown(@RequestParam int userId) {
		
		return eventService.thumbsDownScore(userId);
	}
	
	/*
	 * Allocates points for attendees,userId is used to ensure that the points are allocated
	 * to the rightful person and event id identifies the event which the user has attended.
	 * Methods calculates the time between the user got to the event and the time which the user left
	 * to make ensure the user attended full event
	 */
	@PostMapping("attendin")
	public String takeInAttendance(@RequestParam int userId,@RequestParam int eventId) {
		User user = userService.getUser(userId);
		Events event = eventService.getEvent(eventId);
		return eventService.attendIn(user, event);
	}
	@PostMapping("attendout")
	public String takeOutAttendance(@RequestParam int userId,@RequestParam int eventId) {
		User user = userService.getUser(userId);
		Events event = eventService.getEvent(eventId);
		return eventService.EventOut(user, event);
	}

	@GetMapping("geteventscore")
	public double getEventScore (@RequestParam int userId) {
		return eventService.eventScore(userId);
		
	}
	
	@GetMapping("getorganizer")
	public String organizerName(int userId) {
		return eventService.getName(userId);
		
	}
	
	@GetMapping("isAttendingEvent")
	public boolean isGoingToEvent(@RequestParam int userId,@RequestParam int eventId) {
		return userService.isGoingToEvent(userId, eventId);
	}
	
	@GetMapping("notGoing")
	public String removeInteretesdEvent(@RequestParam int userId,@RequestParam int eventId) {
		return null;
	}
	
	
	
	@GetMapping("geteventsbydate")
    public ArrayList<Events> getEventsByDate(@RequestParam Date start,@RequestParam Date end){
        
        return eventService.dateBasedEvents(start, end);
    }
	
	@GetMapping("getEventsToBeHosted")
	public ArrayList<Events> getEventsToBeHosted() {
		return eventService.getEventsToBeHosted();
		
	}

	//return list of events the user is interested in attending
	@GetMapping("interestingEvents")
	public ArrayList<EventToAttend> getEventsUserInterestedIn(int userId){
		return userService.getEventsToAttend(userId);
	}
	
	@GetMapping("eventsByUser")
    public ArrayList<Events> eventsByUser(@RequestParam int userId) {
        
     return  eventService.eventsCreatedByUser(userId);
        
    }
}
	
	


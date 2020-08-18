package com.digital.academy.cloudDime.implementation;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.academy.cloudDime.data.CreateDate;
import com.digital.academy.cloudDime.data.InterestedUser;
import com.digital.academy.cloudDime.entity.AttendanceRegister;
import com.digital.academy.cloudDime.entity.DocumentType;
import com.digital.academy.cloudDime.entity.EventDocuments;
import com.digital.academy.cloudDime.entity.EventScore;
import com.digital.academy.cloudDime.entity.Events;
import com.digital.academy.cloudDime.entity.InterestedUsers;
import com.digital.academy.cloudDime.entity.Profile;
import com.digital.academy.cloudDime.entity.User;
import com.digital.academy.cloudDime.interfaces.classes.EventsInterface;
import com.digital.academy.cloudDime.repository.AttendanceRepository;
import com.digital.academy.cloudDime.repository.DocumentTypeRepository;
import com.digital.academy.cloudDime.repository.EventDocumentsRepository;
import com.digital.academy.cloudDime.repository.UserDocumentsRepository;
import com.digital.academy.cloudDime.repository.EventScoreRepository;
import com.digital.academy.cloudDime.repository.EventsRepository;
import com.digital.academy.cloudDime.repository.InterestedUsersRepository;
import com.digital.academy.cloudDime.repository.ProfileRepository;
import com.digital.academy.cloudDime.repository.UserRepository;

@Component
public class EventService implements EventsInterface {
	
	@Autowired
	EventsRepository eventsRepo;
	@Autowired
	EventScoreRepository eventScoreRepo;
	@Autowired
	AttendanceRepository attendanceRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProfileRepository prepository;
	@Autowired
	InterestedUsersRepository interestedUserRepository;
	@Autowired
	private UserDocumentsRepository userDocumentsRepository;
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	@Autowired
	private EventDocumentsRepository eventDocumentsRepository;
	@Autowired
	private EmailServices emailServices;
	
	
	
	/*
	 * This methods create a new events and invokes a method that persist the added events into the
	 * database,it receives all the data needed to create an event from android through Volley request
	 */
		public String createEvent(Events event) {
			
			event.setStatus(false);
			eventsRepo.save(event);
			
			
			Profile profile = prepository.findByUser(event.getUser());
			//Profile findByUser(User user);
			String emailMessage = "Hi "+profile.getName()+ " we have received your request to host an event "+event.getName()+" at "+event.getLocation()+",please for the"
					+ " admin to approve .\n\nRegards Clouddime Team";
			emailServices.sendEmail(event.getUser().getEmail(),"CloudDime event confirmation", emailMessage);
			
			
			return "Event has been succesfully created";
		}
		
		//Retrieves all the upcoming events stored in the database
		public ArrayList<Events> getAvailableEvents(){
			return eventsRepo.findAll();	
		}  
		
		
		public ArrayList<Events> getEvents(int userId){
			
			User user = userRepo.findById(userId).get();
			ArrayList<AttendanceRegister> attendanceRegister =attendanceRepo.findByUser(user);
			ArrayList<Events> allEvents =  eventsRepo.findAll();

				for(AttendanceRegister attendeEvent:attendanceRegister) {
				
					allEvents.remove(eventsRepo.findById(attendeEvent.getEvent()).get());
				
				}
	
			return allEvents;	
			
		}
		//Querying events and using branch name as a condition
		@Override
		public ArrayList<Events>eventFilter(String branch) {	
			return eventsRepo.findByLocation(branch);	
		}
		//Gives a new user default event score of zero
		@Override
		public String createEventScore(User user) {
			
			EventScore score = new EventScore(0,CreateDate.createDate(),user) ;	
			eventScoreRepo.save(score);
			return "Score has been succesfully created";
		}
		
		//Getting the event score of a use
		@Override
		public double eventScore(int userId) {
			User user = userRepo.findById(userId).get();
			EventScore score =eventScoreRepo.findByUser(user);
			return score.getScore();	
		}
		
		/*
		 * Ratings from attendees,for thumbs the score for the event creator increments and thumbs down
		 * the score decrease.thumbs method is for user satisfaction and thumbs down is for user unsatisfactory.
		 */
		public String thumbsUpScore(int userId) {
			User user = userRepo.findById(userId).get();
		
			EventScore score =eventScoreRepo.findByUser(user);
			double newEventScore =score.getScore()+10.00; 
			score.setScore(newEventScore);
			eventScoreRepo.save(score);
			return "Added";
		}
		public String thumbsDownScore(int userId) {
			
			User user = userRepo.findById(userId).get();
			EventScore score =eventScoreRepo.findByUser(user);
			score.setScore(score.getScore()-10.00);
			eventScoreRepo.save(score);
			return "Added";
		}
		//To be added ===========================================================
		public Events getEvent(int eventId) {
			return eventsRepo.findById(eventId).get();
		}
		/*
		 * Method is invoked when a user scans a QR code of the event
		 * Allocates points for attendees,userId is used to ensure that the points are allocated
		 * to the rightful person and event id identifies the event which the user has attended.
		 * Methods calculates the time between the user got to the event and the time which the user left
		 * to make ensure the user attended full event
		 */
		public String attendIn(User user,Events event) {

			ArrayList<AttendanceRegister> attendanceRegister =attendanceRepo.findByUser(user);
			
			for(AttendanceRegister userEvents :attendanceRegister ) {
					if(userEvents.getEvent()==event.getEventID()) {
						return " ";
					}
			}
			
			//Adding a new event attendance register
			
			AttendanceRegister newRegister = new AttendanceRegister(user,event.getEventID(),event.getPoints(),CreateDate.createDate());
			attendanceRepo.save(newRegister);
			
			return "Recorded";
		}
		public String EventOut(User user,Events event) {
			
			String message = "";
			
			
			//getting the history of events that were registered under the user
			ArrayList<AttendanceRegister> attendanceRegister =attendanceRepo.findByUser(user);
				
			//checks whether the event you are attending is not already registered 
			for(AttendanceRegister userEvents :attendanceRegister ) {
				
					AttendanceRegister register = userEvents;
					
					if(register.getEvent()==event.getEventID()) {
						Timestamp outTimeb = register.getOutTime();
						if(outTimeb==null){
						
							register.setOutTime(CreateDate.createDate());
							long diff = register.getOutTime().getTime()-register.getCreateDate().getTime();
							int seconds = (int) diff / 1000;
							 // calculate hours minutes and seconds
							 int minutes = (seconds % 3600) / 60; 
							register.setTimeSpent(minutes);
							
						
							attendanceRepo.save(register);
							message = "Recorded";
						
						}
					}
			}
			
			return message;
		}
		
		//method accepts user id and returns user's name,by using
		public String getName(int userId) {
			
			User user = userRepo.findById(userId).get();

			Profile profile = prepository.findByUser(user);
			String name = profile.getName();
			
			return name;
			
		}

		@Override
		public String addInterestedUser(int eventId, int userId) {
			// TODO Auto-generated method stub
			Events eventToAttend = null;
			User user = userRepo.findById(userId).get();
						
			ArrayList<Events> allEvents = eventsRepo.findAll();
			
			for(Events event : allEvents) {
				if(event.getEventID() == eventId) {
					eventToAttend = event;
				}
			}
			InterestedUsers interestedUser = new InterestedUsers(CreateDate.createDate(),user,eventToAttend);
			ArrayList<InterestedUsers> interestedUsersList = (ArrayList<InterestedUsers>)interestedUserRepository.findAll();
			
			
			boolean addUser =  interestedUsersList.add(interestedUser);
			
			if(addUser) {
				interestedUserRepository.saveAll(interestedUsersList);
				return "Success";
			}
			
			return "Unsuccesful";
		}

		/*
		 * Methods rectrieves all the users interested in going to a specific event,which is identified by the
		 * event id
		 * (non-Javadoc)
		 * @see com.digital.academy.cloudDime.interfaces.classes.EventsInterface#getAllInterestedUsers(int)
		 */
		@Override
		public ArrayList<InterestedUser> getAllInterestedUsers(int eventId) {
			// TODO Auto-generated method stub
			ArrayList<InterestedUsers> interestedUsersList = (ArrayList<InterestedUsers>)interestedUserRepository.findAll();
			
			ArrayList<InterestedUser> eventAttendess = new ArrayList<>();
			
			for(InterestedUsers interestedUser : interestedUsersList) {
				Profile profile= prepository.findByUser(interestedUser.getUser());
				
				if(interestedUser.getEvent().getEventID() == eventId) {
					eventAttendess.add(new InterestedUser(profile.getName(),profile.getSurname(),profile.getUser().getUserId()));
				}
				
			}
			
			return eventAttendess;
		}

		@Override
		public ArrayList<Events> getEventsToBeHosted() {
			// TODO Auto-generated method stub
			ArrayList<Events> allEventsList = eventsRepo.findAll();
			ArrayList<Events> eventsToBeHosted = new ArrayList<>();
			
			Date today = Date.valueOf(LocalDate.now());
			
			for(Events event : allEventsList) {
				
				if(!(event.getDate().before(today))) {
				
					eventsToBeHosted.add(event);
				}	
					
			}
		
			return eventsToBeHosted;
		}
		
		public ArrayList<Events> dateBasedEvents(Date start,Date end){
            
            return eventsRepo.findByDateBetween(start, end);
        }

		@Override
		public EventDocuments uploadFileForEvent(String url,String documentDescription,int eventId) {
			// TODO Auto-generated method stub
			
			Events event = eventsRepo.findById(eventId).get();
			EventDocuments eventDocuments = new EventDocuments(url,documentDescription,CreateDate.createDate(),event);
			EventDocuments uploadedDocument = eventDocumentsRepository.save(eventDocuments);
		    DocumentType documentType = new DocumentType(documentDescription,CreateDate.createDate(),uploadedDocument);
			documentTypeRepository.save(documentType);
			return uploadedDocument;
			
		}

		@Override
		public ArrayList<Events> eventsCreatedByUser(int userId) {
			// TODO Auto-generated method stub
			User user = userRepo.findById(userId).get();
            return eventsRepo.findByUser(user);
			
		}
		

}

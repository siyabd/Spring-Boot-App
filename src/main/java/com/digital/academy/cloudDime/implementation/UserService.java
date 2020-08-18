package com.digital.academy.cloudDime.implementation;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.digital.academy.cloudDime.data.CreateDate;
import com.digital.academy.cloudDime.data.EventToAttend;
import com.digital.academy.cloudDime.data.PurchaseHistory;
import com.digital.academy.cloudDime.data.UserDocumentsInfo;
import com.digital.academy.cloudDime.data.UserInfo;
import com.digital.academy.cloudDime.entity.Achievements;
import com.digital.academy.cloudDime.entity.AttendanceRegister;
import com.digital.academy.cloudDime.entity.BusinessProfile;
import com.digital.academy.cloudDime.entity.Connections;
import com.digital.academy.cloudDime.entity.DocumentType;
import com.digital.academy.cloudDime.entity.EventScore;
import com.digital.academy.cloudDime.entity.Events;
import com.digital.academy.cloudDime.entity.InterestedUsers;
import com.digital.academy.cloudDime.entity.Items;
import com.digital.academy.cloudDime.entity.Points;
import com.digital.academy.cloudDime.entity.Profile;
import com.digital.academy.cloudDime.entity.TransactionalHistory;
import com.digital.academy.cloudDime.entity.User;
import com.digital.academy.cloudDime.entity.UserDocuments;
import com.digital.academy.cloudDime.entity.UserType;
import com.digital.academy.cloudDime.interfaces.classes.UserInterface;
import com.digital.academy.cloudDime.repository.AchievementsRepository;
import com.digital.academy.cloudDime.repository.AttendanceRepository;
import com.digital.academy.cloudDime.repository.BusinessProfileRepository;
import com.digital.academy.cloudDime.repository.ConnectionRepository;
import com.digital.academy.cloudDime.repository.DocumentTypeRepository;
import com.digital.academy.cloudDime.repository.UserDocumentsRepository;
import com.digital.academy.cloudDime.repository.EventScoreRepository;
import com.digital.academy.cloudDime.repository.EventsRepository;
import com.digital.academy.cloudDime.repository.HistoryRepository;
import com.digital.academy.cloudDime.repository.InterestedUsersRepository;
import com.digital.academy.cloudDime.repository.ItemsRepository;
import com.digital.academy.cloudDime.repository.PointsRepository;
import com.digital.academy.cloudDime.repository.ProfileRepository;
import com.digital.academy.cloudDime.repository.UserRepository;
import com.digital.academy.cloudDime.repository.UserTypeRepository;

@Component
public class UserService implements UserInterface {
	@Autowired
	private ProfileRepository prepository;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private HistoryRepository historyRepo;
	@Autowired
	private PointsRepository pointsRepo;
	@Autowired
	private BusinessProfileRepository businessRepo;
	@Autowired
	private AchievementsRepository achievementsRepo;
	@Autowired
	private ConnectionRepository connectionRepo;
	@Autowired
	private PointsRepository pointsRepository;
	@Autowired
	private UserDocumentsRepository userDocumentsRepository;
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	@Autowired
	EventScoreRepository eventScoreRepo;
	@Autowired
	private AttendanceRepository attendanceRepo;
	@Autowired
	private InterestedUsersRepository interestedUserRepository;
	@Autowired
	private EventsRepository eventsRepo;
	@Autowired
	private EmailServices emailServices;
	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	private ItemsRepository itemsRepository;
	
	/*
	 *Method has parameter that accept object of profile which consists of all details
	 *needed for registration,it also encrypt the provided password.Again methods allocates 
	 *default points,event score,and status for the user after successful registration.  
	 */

	public UserInfo registerUser(Profile profile) {
		
		User user = userRepo.findByEmail(profile.getUser().getEmail());

		if (user != null) {
			System.out.println("User exist");
			return null;

		}

		//stores date of the registration sets status to 0 which ,which means that the user is not yet approved by the administrator
		profile.setCreateDate(CreateDate.createDate());
		profile.getUser().setCreateDate(CreateDate.createDate());
		profile.getUser().setActive(true);
		profile.getUser().setStatus(0);
		
		UserType userType = userTypeRepository.findById(1).get();
		
		
		profile.getUser().setUserType(userType);

		// encrypting the password,so that its not readable in the database records
		String rawPassword = profile.getUser().getPassword();
		String encryptedPassword = new BCryptPasswordEncoder().encode(rawPassword);
		profile.getUser().setPassword(encryptedPassword);

		//allocating points for registering
		Points points = new Points(50, 0.0, profile.getUser());
		
		//Setting default overall score of the user's events,score will increment after being rated
		EventScore score = new EventScore(0,CreateDate.createDate(),profile.getUser()) ;	
		eventScoreRepo.save(score);

		pointsRepository.save(points);
		prepository.save(profile);

		UserInfo userInformation = new UserInfo(profile.getName(), profile.getSurname(), points.getBalance(),
				points.getPointsSpent(), profile.getUser().getUserId(),profile.getUser().getUserType().getUserTypeId(),profile.getUser().getStatus());
		
		String emailMessage = "Hi "+profile.getName()+"\n Thank you for joining cloudDime please wait for account confirmation from the Admin\n\nRegards CloudDime";
		
		emailServices.sendEmail(profile.getUser().getEmail(),"CloudDime Registration", emailMessage);

		return userInformation;
	}

	@Override
	
	/*
	 * Method logs the user in,compares email and password provided with the ones
	 * already stored in the database,if its a wrong match method returns null
	 * and the user will not be able to gain access to the application.	
	 */
	public UserInfo login(User user) {
		
		ArrayList<User> registeredUsersList = (ArrayList<User>) userRepo.findAll();
		ArrayList<Profile> profilesList = (ArrayList<Profile>) prepository.findAll();
		
		Profile loggedProfile = null;
		UserInfo userInformation = null;

		User validUser = userRepo.findByEmail(user.getEmail());
		
		String email = validUser.getEmail();
		System.out.println(email);
	
		if(validUser != null) {
			
			for (User userObj : registeredUsersList) {
				
				boolean isValidPassword = new BCryptPasswordEncoder().matches(user.getPassword(), userObj.getPassword());
				System.out.println(isValidPassword);
				if (isValidPassword) {

					for (Profile profileObj : profilesList) {
						if (profileObj.getUser().getUserId() == validUser.getUserId()) {
							loggedProfile = profileObj;
							Points onlineUserPoints = null;
							ArrayList<Points> pointsList = (ArrayList<Points>) pointsRepository.findAll();

							for (Points pointsObj : pointsList) {
								if (pointsObj.getUser().getUserId() == loggedProfile.getUser().getUserId()) {
									onlineUserPoints = pointsObj;
								}
							}

							userInformation = new UserInfo(loggedProfile.getName(), loggedProfile.getSurname(),
									onlineUserPoints.getBalance(), onlineUserPoints.getPointsSpent(),
									onlineUserPoints.getUser().getUserId(),loggedProfile.getUser().getUserType().getUserTypeId(),loggedProfile.getUser().getStatus());
							break;
						}
					}
					
				}
			}
			
		}


		return userInformation;
	}

	@Override
	public String addBusinessProfile(BusinessProfile businessProfile, int userId) {

		// adding a user business profile to the database,creates a relationship between
		// the user and the business
		// business assigned to the right user by making a correct user id
		businessProfile.setCreateDate(CreateDate.createDate());
		Optional<User> user = userRepo.findById(userId);
		businessProfile.setUser(user.get());
		BusinessProfile businessProfileObj = businessRepo.save(businessProfile);
		if (businessProfileObj != null) {
			return "Succesful";
		} else {
			return "Unsuccesful,please try again";
		}

	}

	@Override
	public String addConnection(Connections connection, int userId) {

		connection.setCreateDate(CreateDate.createDate());
		Optional<User> user = userRepo.findById(userId);
		connection.setUser(user.get());
		Connections connectionObj = connectionRepo.save(connection);

		if (connectionObj != null) {
			return "Succesful";
		} else {
			return "Unsuccesful,please try again";
		}
	}

	
	/*
	 * Deducts points in the database each an every time the user makes
	 * a purchase,retrieves the current balance and subtract it with the
	 * points spent
	 */
	@Override
	public Points spendPoint(int pointsToDeduct, int userId) {
		
		Optional<Points> userPoints = pointsRepository.findById(userId);
		double newBalance = userPoints.get().getBalance() - pointsToDeduct;
		double pointsSpent = userPoints.get().getPointsSpent() + pointsToDeduct;
		userPoints.get().setBalance(newBalance);
		userPoints.get().setPointsSpent(pointsSpent);
		pointsRepository.save(userPoints.get());

		return pointsRepository.save(userPoints.get());
	}

	@Override
	public String addAchievements(Achievements achievements,int userid) {

		achievements.setCreateDate(CreateDate.createDate());
		Optional<User> user = userRepo.findById(userid);
		achievements.setUser(user.get());
		Achievements achievement = achievementsRepo.save(achievements);
		

		if (achievement != null) {
			return "Succesful";
		} else {
			return "Unsuccesful,please try again";
		}
	}

	@Override
	public ArrayList<BusinessProfile> getUserBusinesses(int userId) {

		ArrayList<BusinessProfile> businessesList = new ArrayList<>();
		ArrayList<BusinessProfile> getBusinesses = (ArrayList<BusinessProfile>) businessRepo.findAll();
		// finding business id using userId

		for (BusinessProfile businessObj : getBusinesses) {
			if (businessObj.getUser().getUserId() == userId) {
				businessesList.add(businessObj);
			}
		}
		return businessesList;
	}

	// user spending points
	public String purchaseItem(int userID, int itemId) {

		User user = userRepo.findById(userID).get();
		Points points = pointsRepo.findByUser(user);
		
		// deducting from the current user balance
		Items item = itemsRepository.findById(itemId).get();
		double itemCost = item.getPoints();
		double balance = points.getBalance() - itemCost;
		double spent = points.getPointsSpent()+itemCost;
		points.setPointsSpent(spent);
		points.setBalance(balance);
		
		pointsRepo.save(points);

		TransactionalHistory history = new TransactionalHistory(spent, CreateDate.createDate(), user,item);
		historyRepo.save(history);

		return "Item purchased";
	}

	// getting the transactions history of a user
	public ArrayList<TransactionalHistory> getHistory(User user) {
		
		ArrayList<TransactionalHistory> transactionHistoryList= historyRepo.findByUser(user);
		ArrayList<PurchaseHistory> purchaseHistory = new ArrayList<>();
		
		for(TransactionalHistory transaction : transactionHistoryList) {
			//String itemName, double points, Date date
			purchaseHistory.add(new PurchaseHistory(transaction.getItems().getItemName(),transaction.getItems().getPoints(),2015-12-12));
		}
		
		return historyRepo.findByUser(user);

	}
	
	public User getUser(int userId) {
		User user = userRepo.findById(userId).get();
		return user;
	}

	@Override
	public UserDocuments uploadFile(String url,String documentDescription,int userId) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId).get();
		UserDocuments userDocuments = new UserDocuments(url,documentDescription,CreateDate.createDate(),user);
		UserDocuments uploadedDocument = userDocumentsRepository.save(userDocuments);
	    DocumentType documentType = new DocumentType(documentDescription,CreateDate.createDate(),uploadedDocument);
		documentTypeRepository.save(documentType);
		return uploadedDocument;
	}
	
	@Override
	public ArrayList<UserDocumentsInfo> getUserDocuments(int userId) {
		// TODO Auto-generated method stub
		
		//		ArrayList<UserDocuments> allDocuments = (ArrayList<UserDocuments>)userDocumentsRepository.findAll();
		ArrayList<UserDocuments> listDocuments = (ArrayList<UserDocuments>)userDocumentsRepository.findAll();
		ArrayList<UserDocumentsInfo> userDocumentsList = new ArrayList<>();
		for(UserDocuments userDocument : listDocuments) {
			if(userDocument.getUser().getUserId() == userId) {
				
				
				userDocumentsList.add(new UserDocumentsInfo(userDocument.getFileDecription(),userDocument.getUrl(),
						userDocument.getCreateDate(),userDocument.getUser().getUserId()));
			}
		}
		
		return userDocumentsList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.digital.academy.cloudDime.interfaces.classes.UserInterface#getUserAchivements(int)
	 * Method receives user id and then query's all achievements provided by that specific user
	 */
	@Override
	public ArrayList<Achievements> getUserAchivements(int userId) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId).get();
		ArrayList<Achievements> userAchievementsList = (ArrayList<Achievements>) achievementsRepo.findAll();
		ArrayList<Achievements> userAchivements = new ArrayList<>();
		
		for(Achievements userAchievements : userAchievementsList) {
			if(userAchievements.getUser().getUserId() == user.getUserId()) {
				userAchivements.add(userAchievements);
			}
		}
		
		if(userAchivements.isEmpty()) {
			return null;
		}else {
			return userAchivements;
		}
	
	}

	/*
	 * (non-Javadoc)
	 * @see com.digital.academy.cloudDime.interfaces.classes.UserInterface#getEventOrganiser(int)
	 * Method receives user id and then query's all the events created by that specific user
	 */
	@Override
	public Profile getEventOrganiser(int userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).get();
		return prepository.findByUser(user);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.digital.academy.cloudDime.interfaces.classes.UserInterface#getConnections(int)
	 * methods receives user id and query's conections belonging to a specific user
	 */
	@Override
	public ArrayList<Connections> getConnections(int userId) {
		// TODO Auto-generated method stub
		
		ArrayList<Connections> allConnectionsList = (ArrayList<Connections>)connectionRepo.findAll();
		ArrayList<Connections> userConnections =  new ArrayList<>();
		for(Connections connections : allConnectionsList) {
			if(connections.getUser().getUserId() == userId) {
				userConnections.add(connections); 
			}
		}
		return userConnections;
	}


	/*
	 * Method resets user current password,it receives the user email and new password.
	 * The email is used as a condition to ensure the password being reset is for the
	 * the right user. 
	 */
	@Override
	public String resetPassword(String email, String password) {
		String message = null;
		User user = userRepo.findByEmail(email);
		String old = user.getPassword();
		String encryptedPassword = new BCryptPasswordEncoder().encode(password);
		if(user != null) {
			user.setPassword(encryptedPassword);
			userRepo.save(user);
			System.out.println("changed "+old+"\n new :"+encryptedPassword);
			message = "Your password has been reset";
			
		}
		return message;
	}

	@Override
	public ArrayList<AttendanceRegister> eventsAttended(int userId) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId).get();
		ArrayList<AttendanceRegister> attendanceRegister =attendanceRepo.findByUser(user);
		
		return attendanceRegister;
	}

	@Override
	public ArrayList<EventToAttend> getEventsToAttend(int userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).get();
		ArrayList<InterestedUsers> myEvents = interestedUserRepository.findByUser(user);
		ArrayList<Events> getAllEvents = eventsRepo.findAll();
		
		//myEvents.get(0).isGoing()
		
		Date today = Date.valueOf(LocalDate.now());
		//String name,String description, String location, Date date, Time startTime, Time endTime, int points
		ArrayList<EventToAttend> eventsToAttendList = new ArrayList<>();
		
		
		
		for(InterestedUsers eventOfInterest : myEvents) {
			
			for(Events event : getAllEvents) {
				if(eventOfInterest.getEvent().getEventID() == event.getEventID() && !(event.getDate().before(today)) && eventOfInterest.isGoing()) {
					
					Profile profile = prepository.findByUser(event.getUser());
					eventsToAttendList.add(new EventToAttend(event.getName(),event.getDescription(),event.getLocation(),event.getDate(),
							event.getStartTime(),event.getEndTime(),event.getPoints(),event.getEventID(),profile.getName()));
				}
				
			}
	
		}//here

		return eventsToAttendList;
	}

	@Override
	public boolean isGoingToEvent(int userId, int eventId) {
		// TODO Auto-generated method stub
		ArrayList<EventToAttend> listEventToAttend  = getEventsToAttend(userId);
		boolean isAttending = false;
	
		for(EventToAttend eventToAttend : listEventToAttend) {
			if(eventId == eventToAttend.getEventId()) {
				isAttending = true;
				break;
			}
		}
		
		return isAttending;
	}

	@Override
	public String getOTP(String email,String otp) {
		// TODO Auto-generated method stub
		
		Profile profile = prepository.findByUser(userRepo.findByEmail(email));
		//String emai,String subject,String mailBody
		
		String mailBody = "Hi "+profile.getName()+"\n We have received a request from you to reset your passord,make use of this pin :"+otp+
				"to proceed\n\nRegards Clouddime Team";
		emailServices.sendEmail(email, "Password Reset : OTP",mailBody);
		return null;
	}

	@Override
	public String removeInteretesdEvent(int userId, int eventId) {
		// TODO Auto-generated method stub
		
		String message = "Event could not be removed";
		
		ArrayList<InterestedUsers> myEvents = interestedUserRepository.findByUser(userRepo.findById(userId).get());
		
		for(InterestedUsers interestedUsers : myEvents) {
			if(interestedUsers.getEvent().getEventID() == eventId) {
				interestedUsers.setGoing(false);
				
				interestedUserRepository.save(interestedUsers);
				
				message = "Event removed from interested events";
				break;
			}
		}
		
		return message;
	}

	

}

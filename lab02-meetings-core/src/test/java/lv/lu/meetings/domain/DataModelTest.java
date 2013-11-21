package lv.lu.meetings.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import lv.lu.meetings.base.JpaBaseTest;
import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.domain.jpa.meeting.Attendance;
import lv.lu.meetings.domain.jpa.meeting.Invite;
import lv.lu.meetings.domain.jpa.meeting.Meeting;
import lv.lu.meetings.domain.jpa.venue.Category;
import lv.lu.meetings.domain.jpa.venue.Venue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Creates a full data structure with associations 
 * and then tests that data is correctly saved to and retrieved from database.
 * 
 * Only persistence and O/R mapping is tested there.
 */
public class DataModelTest extends JpaBaseTest{ 
    
	private static List<User> users;
	private static List<Category> categories;
	private static List<Venue> venues;
	private static List<Meeting> meetings;
	private static List<Invite> invites;
	private static List<Attendance> attendances;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		JpaBaseTest.setUpBeforeClass();
		em.getTransaction().begin();
		
		createUsers(3);
		createCategories(2);
		createVenues(5);
		createMeetings();
		createInvites();
		createAttendances();
		
		/* flush changes to data store and clear persistence context */		
		flushAndClear();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		em.getTransaction().rollback();
		JpaBaseTest.tearDownAfterClass();
	}
	
	@Override @Before
	public void setUp() { /* nothing */ }
	
	@Override @After
	public void tearDown() {/* nothing */ }

	/**
	 * Data creation
	 */
	
	private static void createUsers(int count) {
		users = new ArrayList<User>(count);
		for (int i = 0 ; i < count; i++){
		    users.add(createTestUser());	
		}	
		jpaDAO.saveAll(users);
	}
	
    private static void createCategories(int count) {
        categories = new ArrayList<Category>(count);
        for (int i = 0 ; i < count; i++){
            categories.add(createTestCategory());
        }
        jpaDAO.saveAll(categories);
    }
    
    private static void createVenues(int count) {
        venues = new ArrayList<Venue>(count);
        for (int i = 0 ; i < count; i++){
            Category category = categories.get(i % categories.size());
            Venue venue = createTestVenue(category);
            venues.add(venue);
        }
        jpaDAO.saveAll(venues);
    }

    private static void createMeetings() {
    	meetings = new ArrayList<Meeting>(3);    	
    	meetings.add(createTestParty(users.get(0), venues.get(0)));
    	meetings.add(createTestConcert(users.get(0), venues.get(1)));
    	meetings.add(createTestSportEvent(users.get(1), venues.get(2)));
    	jpaDAO.saveAll(meetings);
	}

	private static void createInvites() {
		invites = new ArrayList<Invite>(5);
		
		for (int i = 1; i <= 2; i++){
			invites.add(createTestInvite(
					meetings.get(0), 
					users.get(0), 
					users.get(i)));
		}		
		for (int i = 0; i <= 1; i++){
			invites.add(createTestInvite(
					meetings.get(1), 
					users.get(2), 
					users.get(i)));
		}
		invites.add(createTestInvite(
				meetings.get(2), 
				users.get(1), 
				users.get(2)));
		
		jpaDAO.saveAll(invites);
	}
	
	private static void createAttendances() {
		attendances = new ArrayList<Attendance>(3);
		attendances.add(createTestAttendance(meetings.get(0), users.get(1)));
		attendances.add(createTestAttendance(meetings.get(1), users.get(0)));
		attendances.add(createTestAttendance(meetings.get(2), users.get(2)));
		jpaDAO.saveAll(attendances);
	}
    
    /**
     * Data loading and verification
     */

	@Test
	public void testUsers(){			
		List<User> usersFromDB = jpaDAO.findAll(User.class);
		assertEquals("Wrong number of users", users.size(), usersFromDB.size());

		for (int i = 0; i < users.size(); i++){
		    assertUsersEquals(users.get(i), usersFromDB.get(i));
		}
	}
	
	@Test
	public void testCategories(){            
	    List<Category> categoriesFromDB = jpaDAO.findAll(Category.class);
	    assertEquals("Wrong number of categories", categories.size(), categoriesFromDB.size());
	    
	    for (int i = 0; i < categoriesFromDB.size(); i++){
	        assertEquals(categories.get(i).getName(), categoriesFromDB.get(i).getName());  
	    }
	}
	
	@Test
	public void testVenues(){            
	    List<Venue> venuesFromDB = jpaDAO.findAll(Venue.class);
	    assertEquals("Wrong number of venues", venues.size(), venuesFromDB.size());
	    
	    for (int i = 0; i < venuesFromDB.size(); i++){
	        assertVenuesEquals(venues.get(i), venuesFromDB.get(i));  
	    }
	}
	
	@Test
	public void testMeetings(){            
	    List<Meeting> meetingsFromDB = jpaDAO.findAll(Meeting.class);
	    assertEquals("Wrong number of meetings", meetings.size(), meetingsFromDB.size());
	    
	    for (int i = 0; i < meetingsFromDB.size(); i++){
	        assertMeetingsEquals(meetings.get(i), meetingsFromDB.get(i));  
	    }
	}
	
	@Test
	public void testInvites(){            
	    List<Invite> invitesFromDB = jpaDAO.findAll(Invite.class);
	    assertEquals("Wrong number of invites", invites.size(), invitesFromDB.size());
	    
	    for (int i = 0; i < invitesFromDB.size(); i++){
	        assertInvitesEquals(invites.get(i), invitesFromDB.get(i));  
	    }
	}
	
	@Test
	public void testAttendances(){            
	    List<Attendance> attendanceFromDB = jpaDAO.findAll(Attendance.class);
	    assertEquals("Wrong number of attendances", attendances.size(), attendanceFromDB.size());
	    
	    for (int i = 0; i < attendanceFromDB.size(); i++){
	        assertAttendancesEquals(attendances.get(i), attendanceFromDB.get(i));  
	    }
	}
}

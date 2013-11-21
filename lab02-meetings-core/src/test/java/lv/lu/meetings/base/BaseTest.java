package lv.lu.meetings.base;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Random;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.domain.jpa.meeting.Attendance;
import lv.lu.meetings.domain.jpa.meeting.Concert;
import lv.lu.meetings.domain.jpa.meeting.Invite;
import lv.lu.meetings.domain.jpa.meeting.InviteStatus;
import lv.lu.meetings.domain.jpa.meeting.Meeting;
import lv.lu.meetings.domain.jpa.meeting.Party;
import lv.lu.meetings.domain.jpa.meeting.SportEvent;
import lv.lu.meetings.domain.jpa.venue.Category;
import lv.lu.meetings.domain.jpa.venue.Venue;

/**
 * Base test containing the most common functions.
 */
public abstract class BaseTest {
    
    private static final Random RANDOM = new Random();
    
	protected static User createTestUser(){
		User user = new User();
		user.setUsername(generateRandomString());
		user.setPassword(generateRandomString());
		user.setFirstName(generateRandomString());
		user.setLastName(generateRandomString());
		return user;
	}
	
	protected static Category createTestCategory(){
	    Category category = new Category();
	    category.setName(generateRandomString());
        return category;
    }
	
	protected static Venue createTestVenue(Category category){
		Venue venue = new Venue();
		venue.setName(generateRandomString());
		venue.setLocation(generateRandomString());
		venue.setCategory(category);
		venue.setUrl(generateRandomString());
		venue.setUrl(generateRandomString());
		venue.setFoursquareId(generateRandomString());
		venue.setFoursquareUrl(generateRandomString());
		venue.setApproved(RANDOM.nextBoolean());
	    return venue;
	}
	
	protected static Party createTestParty(User creator, Venue venue){
		Party party = new Party();
		initMeeting(party);
		party.setVenue(venue);
		party.setCreator(creator);
		party.setReason(generateRandomString());
		return party;
	}
	
	protected static Concert createTestConcert(User creator, Venue venue){
		Concert concert = new Concert();
		initMeeting(concert);
		concert.setVenue(venue);
		concert.setCreator(creator);
		concert.setArtist(generateRandomString());
		return concert;
	}
	
	protected static SportEvent createTestSportEvent(User creator, Venue venue){
		SportEvent sportEvent = new SportEvent();
		initMeeting(sportEvent);
		sportEvent.setVenue(venue);
		sportEvent.setCreator(creator);
		sportEvent.setSport(generateRandomString());
		sportEvent.setParticipant1(generateRandomString());
		sportEvent.setParticipant2(generateRandomString());
		return sportEvent;
	}
	
	private static void initMeeting(Meeting meeting){
		meeting.setTitle(generateRandomString());
		meeting.setDetails(generateRandomString());
		meeting.setStartTime(new Date(System.currentTimeMillis() - 1000*60*60));
		meeting.setEndTime(new Date(System.currentTimeMillis() + 1000*60*180));
	}
	
	protected static Invite createTestInvite(Meeting meeting, User inviter, User invitee){
		Invite invite = new Invite();
		invite.setCreated(new Date());
		invite.setInviteStatus(getRandomInviteStatus());
		invite.setMeeting(meeting);
		invite.setInviter(inviter);
		invite.setInvitee(invitee);
		return invite;
	}
	
	private static InviteStatus getRandomInviteStatus(){
		InviteStatus[] values = InviteStatus.values();
		return values[RANDOM.nextInt(values.length)];
	}
	
	protected static Attendance createTestAttendance(Meeting meeting, User attendee){
		Attendance attendance = new Attendance();
		attendance.setCreated(new Date());
		attendance.setMeeting(meeting);
		attendance.setAttendee(attendee);
		return attendance;
	}
		
    private static String generateRandomString(){
        String random = Long.toString(Math.abs(RANDOM.nextLong()), 36);
        return random.substring(0,8);
    }
    
    protected void assertUsersEquals(User expected, User actual){
        assertEquals("User 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("User 'username' is wrong", expected.getUsername(), actual.getUsername());
        assertEquals("User 'password' is wrong", expected.getPassword(), actual.getPassword());
        assertEquals("User 'first name' is wrong", expected.getFirstName(), actual.getFirstName());
        assertEquals("User 'last name' is wrong", expected.getLastName(), actual.getLastName());
    }
    
    protected void assertVenuesEquals(Venue expected, Venue actual){
        assertEquals("Venue 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("Venue 'name' is wrong", expected.getName(), actual.getName());
        assertEquals("Venue 'location' is wrong", expected.getLocation(), actual.getLocation());
        assertEquals("Venue 'url' is wrong", expected.getUrl(), actual.getUrl());
        assertEquals("Venue 'foursquareId' is wrong", expected.getFoursquareId(), actual.getFoursquareId());
        assertEquals("Venue 'foursquareUrl' is wrong", expected.getFoursquareUrl(), actual.getFoursquareUrl());
        assertEquals("Venue 'approved' is wrong", expected.isApproved(), actual.isApproved());
        assertEquals("Venue 'category' is wrong", expected.getCategory().getName(), actual.getCategory().getName());
    }
    
    protected void assertMeetingsEquals(Meeting expected, Meeting actual){
        assertEquals("Meeting 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("Meeting 'title' is wrong", expected.getTitle(), actual.getTitle());
        assertEquals("Meeting 'details' is wrong", expected.getDetails(), actual.getDetails());
        assertEquals("Meeting 'startTime' is wrong", expected.getStartTime(), actual.getStartTime());
        assertEquals("Meeting 'endTime' is wrong", expected.getEndTime(), actual.getEndTime());
        assertVenuesEquals(expected.getVenue(), actual.getVenue());
        assertUsersEquals(expected.getCreator(), actual.getCreator());
        assertEquals("Meeting 'class' is wrong", expected.getClass(), actual.getClass());
    }
    
    protected void assertInvitesEquals(Invite expected, Invite actual){
        assertEquals("Invite 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("Invite 'created' is wrong", expected.getCreated(), actual.getCreated());
        assertEquals("Invite 'inviteStatus' is wrong", expected.getInviteStatus(), actual.getInviteStatus());
        assertMeetingsEquals(expected.getMeeting(), actual.getMeeting());
        assertUsersEquals(expected.getInviter(), actual.getInviter());
        assertUsersEquals(expected.getInvitee(), actual.getInvitee());
    }
    
    protected void assertAttendancesEquals(Attendance expected, Attendance actual){
        assertEquals("Invite 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("Invite 'created' is wrong", expected.getCreated(), actual.getCreated());
        assertMeetingsEquals(expected.getMeeting(), actual.getMeeting());
        assertUsersEquals(expected.getAttendee(), actual.getAttendee());
    }
}

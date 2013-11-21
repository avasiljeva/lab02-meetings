package lv.lu.meetings.domain.jpa.meeting;

import java.util.Date;

import lv.lu.meetings.domain.jpa.PersistentEntity;
import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.domain.jpa.venue.Venue;

/**
 * Base class for meeting entities (business objects stored in relational (e.g. MySQL) database).
 * Meeting are created by portal users and are associated with a specific venue.
 * 
 * @see Party, Concert, SportEvent
 * 
 * TODO [task]: add JPA annotations to make this hierarchy persistent. Decide about inheritance strategy.
 */
public abstract class Meeting implements PersistentEntity  {

	private Long id;
	
	private String title;
	
	/**
	 * Optional details
	 */
	private String details;

	private Date startTime;

	private Date endTime;

	private Venue venue;

	private User creator;

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}

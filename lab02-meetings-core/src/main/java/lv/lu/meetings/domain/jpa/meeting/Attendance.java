package lv.lu.meetings.domain.jpa.meeting;

import java.util.Date;

import lv.lu.meetings.domain.jpa.PersistentEntity;
import lv.lu.meetings.domain.jpa.User;

/**
 * Entity representing a fact that user attends a specific meeting.
 * 
 * TODO [task]: add JPA annotations to make this entity persistent.
 */
public class Attendance implements PersistentEntity {

	private Long id;

	private User attendee;

	private Meeting meeting;

	/**
	 * Creation date of this attendance, when user decided to visit a meeting.
	 */
	private Date created;
	
	public Long getId() {
		return id;
	}

    public User getAttendee() {
        return attendee;
    }

    public void setAttendee(User attendee) {
        this.attendee = attendee;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

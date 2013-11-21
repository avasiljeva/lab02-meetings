package lv.lu.meetings.domain.jpa.meeting;

import java.util.Date;

import lv.lu.meetings.domain.jpa.PersistentEntity;
import lv.lu.meetings.domain.jpa.User;

/**
 * Any attendee of a meeting can send an invite to that meeting to his friends.
 * 
 * TODO [task]: add JPA annotations to make this entity persistent.
 */
public class Invite implements PersistentEntity  {

	private Long id;
	
	private User inviter;
	
	private User invitee;
	
	private Meeting meeting;
	
	/**
	 * Date when this invite has been created
	 */
	private Date created;
	
	private InviteStatus status;
	
	public Long getId() {
		return id;
	}

	public User getInviter() {
		return inviter;
	}

	public void setInviter(User inviter) {
		this.inviter = inviter;
	}

	public User getInvitee() {
		return invitee;
	}

	public void setInvitee(User invitee) {
		this.invitee = invitee;
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

	public InviteStatus getInviteStatus() {
		return status;
	}

	public void setInviteStatus(InviteStatus status) {
		this.status = status;
	}
}

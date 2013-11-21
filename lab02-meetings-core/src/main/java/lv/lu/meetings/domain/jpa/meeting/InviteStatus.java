package lv.lu.meetings.domain.jpa.meeting;

/**
 * Values for tracking invite status
 */
public enum InviteStatus {
    
    // invite is created and invitee have not responded yet
	CREATED,
	
	// invitee accepted this invite
	ACCEPTED,
	
	// invitee declined this invite
	DECLINED
}

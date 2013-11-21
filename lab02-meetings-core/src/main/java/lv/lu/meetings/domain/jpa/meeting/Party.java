package lv.lu.meetings.domain.jpa.meeting;

/**
 * Type of a meeting denoting a party.
 * 
 * TODO [task]: add JPA annotations to make this entity persistent.
 */
public class Party extends Meeting {
	
    /**
     * Brief description of a reason for this party 
     */
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}

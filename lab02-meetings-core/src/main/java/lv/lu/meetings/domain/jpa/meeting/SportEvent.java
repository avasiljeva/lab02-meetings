package lv.lu.meetings.domain.jpa.meeting;

/**
 * Type of a meeting denoting a sport event (e.g. a football match).
 * 
 * TODO [task]: add JPA annotations to make this entity persistent.
 */
public class SportEvent extends Meeting {

    /**
     * Name of a sport, e.g. football, tennis, basketball 
     */
	private String sport;
	
	/**
	 * Name of the first participant (e.g. a football team)
	 */
	private String participant1;

	/**
     * Name of the second participant (e.g. a football team)
     */
	private String participant2;

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getParticipant1() {
		return participant1;
	}

	public void setParticipant1(String participant1) {
		this.participant1 = participant1;
	}

	public String getParticipant2() {
		return participant2;
	}

	public void setParticipant2(String participant2) {
		this.participant2 = participant2;
	}
}

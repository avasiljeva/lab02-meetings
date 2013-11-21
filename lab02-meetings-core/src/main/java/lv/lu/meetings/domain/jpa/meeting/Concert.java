package lv.lu.meetings.domain.jpa.meeting;

/**
 * Type of a meeting denoting a musical concert.
 * 
 * TODO [task]: add JPA annotations to make this entity persistent.
 */
public class Concert extends Meeting {
	
    /**
     * Name of performing artist
     */
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}

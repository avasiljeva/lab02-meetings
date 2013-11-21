package lv.lu.meetings.domain.jpa.venue;

import javax.persistence.Transient;

import lv.lu.meetings.domain.jpa.PersistentEntity;
import lv.lu.meetings.interfaces.VenueParser;

/**
 * Venue entity, is going to be used as a place for a meeting.
 * 
 * TODO [task]: add JPA annotations to make this entity persistent.
 */
public class Venue implements PersistentEntity  {

	private Long id;
	
	private String name;
	
	private String location;

	/**
	 * A category of this venue (e.g. 'food', 'coffee', 'outdoors').
	 * Corresponds to 'section' parameter of Foursquare request 
	 * and 'query' attribute in response JSON. 
	 */
	private Category category;
	
	/**
	 * This field is not persistent (will not be stored into database).
	 * It is intended for filling by {@link VenueParser} during JSON processing.
	 */
	@Transient
	private String categoryName;
	
	/**
	 * URL to venue's web site
	 */
	private String url;

	/**
	 * Identifier on Foursquare
	 */
	private String foursquareId;
	
	/**
     * URL to venue's page on Foursquare
     */
	private String foursquareUrl;
	
	/**
	 * Approval status in Meetings portal.
	 * Initial status should be 'false'.
	 * Venue can be approved by portal administrator.
	 */
	private boolean approved;
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFoursquareId() {
		return foursquareId;
	}

	public void setFoursquareId(String foursquareId) {
		this.foursquareId = foursquareId;
	}

	public String getFoursquareUrl() {
		return foursquareUrl;
	}

	public void setFoursquareUrl(String foursquareUrl) {
		this.foursquareUrl = foursquareUrl;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}

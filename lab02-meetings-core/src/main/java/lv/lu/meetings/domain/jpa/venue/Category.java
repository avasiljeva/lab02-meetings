package lv.lu.meetings.domain.jpa.venue;

import lv.lu.meetings.domain.jpa.PersistentEntity;

/**
 * Venue categories (e.g. 'food', 'disco', 'concert hall', 'footbal arena' etc).
 * TODO [task]: add JPA annotations to make this entity persistent.
 */
public class Category implements PersistentEntity  {

	private Long id;
	
	private String name;
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

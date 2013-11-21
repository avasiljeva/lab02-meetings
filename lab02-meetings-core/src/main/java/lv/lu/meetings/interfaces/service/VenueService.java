package lv.lu.meetings.interfaces.service;

import java.util.List;

import lv.lu.meetings.domain.jpa.venue.Venue;

public interface VenueService {

	public List<Venue> getAllVenues();
}

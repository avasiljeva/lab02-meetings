package lv.lu.meetings.impl.service;

import java.util.List;

import lv.lu.meetings.domain.jpa.venue.Venue;
import lv.lu.meetings.interfaces.service.VenueService;

import org.springframework.stereotype.Component;

/**
 * Service for operations related to venues
 */
@Component
public class VenueServiceImpl implements VenueService {
    
	public List<Venue> getAllVenues() {

		// just for demo, using temporary in-memory storage
		// TODO: replace with database access via JPA
		return InMemoryTmpStorage.getInstance().getVenues();
	}
}

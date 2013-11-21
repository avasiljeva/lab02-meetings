package lv.lu.meetings.impl.venues;

import java.io.InputStream;
import java.util.List;

import lv.lu.meetings.domain.jpa.venue.Venue;
import lv.lu.meetings.interfaces.VenueParser;

import org.springframework.stereotype.Component;

@Component
public class VenueParserImpl implements VenueParser {

	@Override
	public List<Venue> parseVenues(InputStream data) {
	    
		// TODO [task]: implement JSON parsing and data conversion to List<Venue>
	    
		throw new RuntimeException("Implement me");
	}
}

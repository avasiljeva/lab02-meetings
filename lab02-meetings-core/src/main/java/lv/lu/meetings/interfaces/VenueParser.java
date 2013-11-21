package lv.lu.meetings.interfaces;

import java.io.InputStream;
import java.util.List;

import lv.lu.meetings.domain.jpa.venue.Venue;

/**
 * JSON parser that is capable of extracting venue's data and convert it to a list of Venue objects
 */
public interface VenueParser {

    /**
     * Parses JSON and converts data to a list of venues.
     * 
     * Does not fill venue.category field with a proper Category object, 
     * but instead fills transient field venue.categoryName with a String.
     * This value is intended for processing outside of the parser,
     * to assign some existing category or create a new one.
     * 
     * @param json
     * @return list of venues
     */
	List<Venue> parseVenues(InputStream json);
}

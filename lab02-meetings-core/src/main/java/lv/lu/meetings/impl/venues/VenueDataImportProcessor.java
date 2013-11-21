package lv.lu.meetings.impl.venues;

import java.util.List;
import java.util.Map;

import lv.lu.meetings.interfaces.CommonJpaDAO;
import lv.lu.meetings.interfaces.DataImportProcessor;
import lv.lu.meetings.interfaces.HTTPClient;
import lv.lu.meetings.interfaces.VenueParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("venuesProcessor")
public class VenueDataImportProcessor implements DataImportProcessor {

	/* Flag to delete everything related to venues from database before start */
	private boolean cleanDatabase;	
	
	/* Data to be used to compose a request to Foursquare API.
	 * Key: value intended to be passed as 'near' parameter to a request.
	 * Values: values intended to be passed as 'section' parameter 
	 * 		(check if Foursquare API allows to specify several sections in a single request, 
	 * 		if not - send separate requests for each section)
	 */
	private Map<String, List<String>> request;
	
	/*
	 * Value of a 'limit' request parameter for Foursquare request. 
	 */
	private String count;
	
	/**
	 * Application components that will be injected here automatically by Spring
	 */
	
	@Autowired
	private HTTPClient httpClient;
	
	@Autowired
	private VenueParser venueParser;
	
	@Autowired
	private CommonJpaDAO jpaDAO;
	
	@Override
	public void importData() {

		// TODO [task]: your task is to implement venues data loading from Foursquare (https://foursquare.com/).
		
		// You are expected to use implementation of HTTPClient for sending remote request to Foursquare API.
		// Then, you are expected to use implementation of VenueParser to convert received JSON to List<Venue>.
		// Finally, use CommonJpaDAO to store venues into the database.
		// See UsersDataImportProcessor for an example of similar processor.
		
		// Call 'jpaDAO.cleanupVenues()' if 'cleanDatabase' is set to 'true'.
		// Use 'request' map to compose a request to Foursquare API.
		
		// Do not forget to ensure proper assignment of Category object to each Venue.
		// Avoid a behavior of creating duplicated categories in database.
		// Also, do not permit creation of duplicated venues (having the same 'foursquareId').
	    
		System.out.println("*** Your task is to implement VenueDataImportProcessor. See this class for details. ***");
	}	

	public void setCleanDatabase(boolean cleanDatabase) {
		this.cleanDatabase = cleanDatabase;
	}
	
	public void setRequest(Map<String, List<String>> request) {
		this.request = request;
	}

	public void setCount(String count) {
		this.count = count;
	}
}

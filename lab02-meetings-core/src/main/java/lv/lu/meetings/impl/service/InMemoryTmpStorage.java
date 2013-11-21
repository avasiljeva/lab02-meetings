package lv.lu.meetings.impl.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lv.lu.meetings.domain.jpa.venue.Category;
import lv.lu.meetings.domain.jpa.venue.Venue;

/**
 * Temporary in-memory data storage created JUST-FOR-DEMO purpose.
 * 
 * TODO: completely remove this class and store data in database instead!
 */
public class InMemoryTmpStorage {
    
    private static final InMemoryTmpStorage INSTANCE = new InMemoryTmpStorage();
    
    private InMemoryTmpStorage(){}
    
    public static InMemoryTmpStorage getInstance(){
        return INSTANCE;
    }

    private static final List<Venue> venues;
    
    static{
    	venues = new ArrayList<Venue>(5);
    	
    	Category category1 = new Category();
    	category1.setName("food");
    	
    	Category category2 = new Category();
    	category2.setName("arts");
    	
    	Venue venue1 = new Venue();
    	venue1.setName("Neiburgs");
    	venue1.setCategory(category1);
    	venue1.setLocation("Riga, Jauniela 25/27");
    	venue1.setFoursquareUrl("https://foursquare.com/v/neiburgs-r%C4%ABga-latvija/4bbb3b911261d13aa366eb98");
    	venue1.setUrl("http://www.neiburgs.com");
    	venue1.setFoursquareId("4bbb3b911261d13aa366eb98");
    	venues.add(venue1);
    	
    	Venue venue2 = new Venue();
    	venue2.setName("Lido Vērmanītis");
    	venue2.setCategory(category1);
    	venue2.setLocation("Riga, Elizabetes iela 65");
    	venue2.setFoursquareUrl("https://foursquare.com/v/lido-v%C4%93rman%C4%ABtis/4b66c0a8f964a520b0292be3");
    	venue2.setUrl("http://www.lido.lv");
    	venue2.setFoursquareId("4b66c0a8f964a520b0292be3");
    	venues.add(venue2);
    	
    	Venue venue3 = new Venue();
    	venue3.setName("Kino Lora");
    	venue3.setCategory(category2);
    	venue3.setLocation("Riga, Strēlnieku 2");
    	venue3.setFoursquareUrl("https://foursquare.com/v/kino-lora/4ca1fadfe9a7ef3b95c75c16");
    	venue3.setFoursquareId("4ca1fadfe9a7ef3b95c75c16");
    	venues.add(venue3);
    	
    	Venue venue4 = new Venue();
    	venue4.setName("Latvijas Nacionālais teātris");
    	venue4.setCategory(category2);
    	venue4.setLocation("Riga, Kronvalda bulvāris 2");
    	venue4.setFoursquareUrl("https://foursquare.com/v/latvijas-nacion%C4%81lais-te%C4%81tris/4be4687b477d9c742490e62d");
    	venue4.setUrl("http://www.teatris.lv/lv");
    	venue4.setFoursquareId("4be4687b477d9c742490e62d");
    	venues.add(venue4);
    }
    
    public List<Venue> getVenues(){
        return Collections.unmodifiableList(venues);
    }
}

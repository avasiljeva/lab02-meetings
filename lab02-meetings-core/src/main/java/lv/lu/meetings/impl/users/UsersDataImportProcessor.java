package lv.lu.meetings.impl.users;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.persistence.Query;

import lv.lu.meetings.domain.jpa.JPQConst;
import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.interfaces.CommonJpaDAO;
import lv.lu.meetings.interfaces.DataImportProcessor;
import lv.lu.meetings.interfaces.XmlDataProvider;
import lv.lu.meetings.interfaces.XmlParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * User profile importing processor implementation.
 */
@Component("usersProcessor")
public class UsersDataImportProcessor implements DataImportProcessor {

	/* Loads XML file with users data from configured source (e.g. local file system) */
	@Autowired
	@Qualifier("usersDataProvider")
	private XmlDataProvider dataProvider;
	
	/* Parses XML file and converts data to Java objects */
	@Autowired
	private XmlParser xmlParser;
	
	/* Relational database operations */
	@Autowired
	private CommonJpaDAO jpaDAO;
	
	/* Flag to delete everything related to users from database before start */
	private boolean cleanDatabase;
	
	/**
	 * Main method, performs all processing
	 */
	public void importData() {
		log("Started...");
		
		if (cleanDatabase){
			jpaDAO.cleanupUsers();
		}
		
		/**
		 * Load users data from XML and save new records in database
		 */
		InputStream usersXml = dataProvider.loadXml();		// load XML with users data
		if (usersXml != null){

			// parse XML file and extract user objects from it
			List<User> users = xmlParser.parseUsers(usersXml);
			
			// close XML file input stream
			try {
				usersXml.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// process users retrieved from XML file
			if (users != null && !users.isEmpty()){
				for (User user: users){
					
					// check if user with such username already exists in database
					if (!isUserPresentInDatabase(user.getUsername())){
						// user does not exist, create new record
						jpaDAO.save(user);
						log("User [" + user.getUsername() + "] saved to database");
					}
					else{
						// user already exists, use retrieved object
						log("User [" + user.getUsername() + "] already exists in database");
					}
				}	
			}
			else{
				log("No users found");
			}
		}
		else{
			log("Users XML file was not loaded");
		}
		log("Finished!");
	}
	
	/**
	 * Checks if user with provided username already exists in database
	 * @return true is user already exists, false otherwise
	 */
	@SuppressWarnings("unchecked")
    private boolean isUserPresentInDatabase(String username){
		Query query = jpaDAO.getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_ID_BY_USERNAME);
		query.setParameter("username", username);
		List<Long> ids = (List<Long>)query.getResultList();
		return !ids.isEmpty();
	}
		
	private void log(String message){
		System.out.println("[" + this.getClass().getSimpleName() + "] " + message);
	}

	public void setCleanDatabase(boolean cleanDatabase) {
		this.cleanDatabase = cleanDatabase;
	}
}

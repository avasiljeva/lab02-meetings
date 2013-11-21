package lv.lu.meetings;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessResourceFailureException;

import lv.lu.meetings.domain.redis.Friend;
import lv.lu.meetings.interfaces.RedisDAO;

/**
 * Application for checking Redis database content, namely, user friends.
 * Redis database should be up and running.
 */
public class FriendsChecker {

	// user to be checked
    private static final Long USER_ID = 1L;
    
	public static final String SPRING_CONFIG_FILE = "/applicationContext.xml";
	private static final String SPRING_BEAN_ID = "redisDAO";
	
	@SuppressWarnings("resource")
    public static void main(String[] args) 
	{
		log("Check is started");
		
		// initialize Spring application context from configuration file
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
		RedisDAO redisDAO = (RedisDAO)appCtxt.getBean(SPRING_BEAN_ID);
		
		// load feeds from Redis DB
		Set<Friend> friends = null;
        try {
            friends = redisDAO.getFriends(USER_ID);
        } catch (DataAccessResourceFailureException e) {
            System.err.println("Unable to connect to Redis DB");
            e.printStackTrace();
            System.exit(1);
        }
        
        // check retrieved data
		if (friends == null){
			log("There are no friends in Redis DB for user id: " + USER_ID);
		}
		else{		
			log("In total " + friends.size() + " friends are stored in Redis DB for user id: " + USER_ID);
		    for (Friend friend: friends){
		    	log(friend.toString());
		    }
		}
	
		System.out.println("[FriendsChecker] Check is completed");
		System.exit(0);
	}
	
	private static void log(String message){
		System.out.println("[FriendsChecker] " + message);
	}
}

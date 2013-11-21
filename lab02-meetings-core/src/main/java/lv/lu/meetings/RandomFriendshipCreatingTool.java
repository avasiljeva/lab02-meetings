package lv.lu.meetings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.domain.redis.Friend;
import lv.lu.meetings.interfaces.CommonJpaDAO;
import lv.lu.meetings.interfaces.RedisDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Preliminary Redis testing tool.
 * Loads all users from SQL database, selects the random one and adds all remaining users as his friends (and vice versa).
 * Makes nothing if there are less than two users in a database.
 * 
 * Redis database should be up and running.
 */
public class RandomFriendshipCreatingTool {

	public static final String SPRING_CONFIG_FILE = "/applicationContext.xml";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		log("Application started");
		
		// initialize Spring application context from configuration file
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
		
		CommonJpaDAO jpaDAO = (CommonJpaDAO)appCtxt.getBean("jpaDAO");
		RedisDAO redisDAO = (RedisDAO)appCtxt.getBean("redisDAO");
		
		List<User> users = jpaDAO.findAll(User.class);
		log(users.size() + "  users loaded from database");
		
		if (users.size() < 2){
			log("Too few users in database. Cannot establish friendships.");
			System.exit(0);
		}
		
		// select a user for whom friends will be added
		User user = users.remove(new Random().nextInt(users.size()));
		log("Friends will be added to user with id: " + user.getId() + "\n");
		
		// add all remaining users as friends to selected user
		List<Friend> friends = new ArrayList<Friend>(users.size());
		for (User u: users){
		    friends.add(new Friend(u));
		    log("Adding a friend: " + u.getId());
		}
		redisDAO.addFriends(user.getId(), friends);
		log(friends.size() + " friends added for user id: " + user.getId() + "\n");
		
		// opposite direction - add selected user as a friend to all other users
		Friend friend = new Friend(user);
		for (User u: users){
			redisDAO.addFriend(u.getId(), friend);
			log("Reverse friendship with user " + user.getId() + " created for user id: " + u.getId());
		}
		
		log("Application execution successfully completed");
		System.exit(0);
	}
	
	private static void log(String message){
		System.out.println("[FriendshipCreatingTool] " + message);
	}
}

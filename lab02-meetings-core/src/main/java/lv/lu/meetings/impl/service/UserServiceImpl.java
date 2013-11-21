package lv.lu.meetings.impl.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import lv.lu.meetings.domain.jpa.JPQConst;
import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.domain.redis.Friend;
import lv.lu.meetings.interfaces.CommonJpaDAO;
import lv.lu.meetings.interfaces.RedisDAO;
import lv.lu.meetings.interfaces.service.NotificationService;
import lv.lu.meetings.interfaces.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service for operations related to users
 */
@Component
public class UserServiceImpl implements UserService {

	@Autowired
    private CommonJpaDAO jpaDAO;
	
	@Autowired
	private RedisDAO redisDAO;
	
	@Autowired
	private NotificationService notificationService;

    public User getByUsername(String username) {
        if (username == null){
            return null;
        }

        User user = null;
        try {
            Query query = jpaDAO.getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_USER_BY_USERNAME);
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
        } 
        catch (NoResultException nre) {
            // no such user
            System.out.println("User with username '" + username + "' does not exist");
        } 
        catch (NonUniqueResultException nure) {
            // multiple users with such name
            System.out.println("There are multiple users with username '" + username + "' in database");
        }
        return user;
    }

    /**
     * Returns all users if 'excludeUsername' parameter is null.
     * Otherwise, excludes user with username equal to 'excludeUsername' parameter value from the result list.
     * 
     * @param viewerUsername - viewer's username, should be excluded from result list
     */
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(String viewerUsername) {
        List<User> users;
        if (viewerUsername == null){
            users = jpaDAO.findAll(User.class);            
        }
        else{
            Query query = jpaDAO.getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_ALL_EXCLUDE_USERNAME);
            query.setParameter("username", viewerUsername);
            users = query.getResultList();
            
            // load set of friends from Redis and set flags on objects
            Set<Long> friendIds = getFriendIds(viewerUsername);
            for (User user: users){
            	user.setFriend(friendIds.contains(user.getId()));
            }
        }
        Collections.sort(users);
        
        return users;
    }
    
    private Set<Long> getFriendIds(String username){
    	// load user from database
    	Query query = jpaDAO.getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_ID_BY_USERNAME);
        query.setParameter("username", username);
        Long userId = (Long)query.getSingleResult();
        
        // get friends from Redis and collect ids
        Set<Friend> friends = redisDAO.getFriends(userId);
        Set<Long> ids = new HashSet<Long>();
        for (Friend friend: friends){
        	ids.add(friend.getId());
        }
        return ids;
    }

	public void addFriend(User currentUser, Long friendId) {
		User friend = jpaDAO.getById(User.class, friendId);
		
		// add bi-directional friendship
		redisDAO.addFriend(currentUser.getId(), new Friend(friend));
		redisDAO.addFriend(friendId, new Friend(currentUser));
		
		// notify friend user about new friendship
		notificationService.addFriendshipNotification(friend, currentUser);
	}

	public void removeFriend(User user, Long friendId) {
		User friend = jpaDAO.getById(User.class, friendId);
		redisDAO.removeFriend(user.getId(), new Friend(friend));
		redisDAO.removeFriend(friendId, new Friend(user));
	}

	public List<Friend> getFriends(Long userId) {
		List<Friend> friends = new ArrayList<Friend>(redisDAO.getFriends(userId));
		Collections.sort(friends);
		return friends;
	}
}

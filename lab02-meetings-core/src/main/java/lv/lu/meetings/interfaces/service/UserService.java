package lv.lu.meetings.interfaces.service;

import java.util.List;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.domain.redis.Friend;

/**
 * Service for operations related to users.
 */
public interface UserService {

	/**
	 * Get user from database by username
	 * 
	 * @param username
	 */
    public User getByUsername(String username);
    
    /**
     * Returns all users if 'excludeUsername' parameter is null.
     * Otherwise, excludes user with username equal to 'excludeUsername' parameter value from the result list.
     * 
     * @param excludeUsername - username, which should be excluded from result list
     */
    public List<User> getAllUsers(String excludeUsername);
    
    /**
     * Create a bi-directional friendship between two users
     * 
     * @param currentUser
     * @param friendId
     */
    public void addFriend(User currentUser, Long friendId);
    
    /**
     * Remove specified user from the list of friends (both directions)
     * 
     * @param user
     * @param friendId
     */
    public void removeFriend(User user, Long friendId);
    
    /**
     * Get list of friends for specified user
     * 
     * @param userId
     * @return
     */
    public List<Friend> getFriends(Long userId);
}

package lv.lu.meetings.interfaces;

import java.util.Collection;
import java.util.Set;

import lv.lu.meetings.domain.redis.Friend;

/**
 * Interface for operations with Redis database
 */
public interface RedisDAO {

    /**
     * Add a single friend
     * 
     * @param userId - owner of friendships set
     * @param friend - new friend
     */
    public void addFriend(Long userId, Friend friend);

    /**
     * Batch method for adding a collection of friends
     * 
     * @param userId - owner of friendships set
     * @param friends - collection of new friends
     */
    public void addFriends(Long userId, Collection<Friend> friends);
    
    /**
     * Get full set of user friends
     * 
     * @param userId
     * @return set of friends
     */
    public Set<Friend> getFriends(Long userId);
    
    /**
     * Remove specified from from the friend list
     * 
     * @param userId
     * @param friend
     */
    public void removeFriend(Long userId, Friend friend);
}

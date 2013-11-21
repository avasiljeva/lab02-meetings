package lv.lu.meetings.impl.redis;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisSet;
import org.springframework.data.redis.support.collections.RedisSet;
import org.springframework.stereotype.Component;

import lv.lu.meetings.domain.redis.Friend;
import lv.lu.meetings.interfaces.RedisDAO;

/**
 * Implementation of basic operations with data stored in Redis database
 */
@Component("redisDAO")
public class RedisDAOImpl implements RedisDAO {

    /* Limitation for storing only 500 friendships */
    private int maxFriendsCount = 500;
    
    /* Internal Spring template for Redis operations */
	private RedisTemplate<String, Friend> template;
	
    public void addFriend(Long userId, Friend friend) {
        RedisSet<Friend> friends = friends(userId);
        if (friends.size() < maxFriendsCount){
            friends.add(friend);
        }
        else{
            System.out.println("Max friends count exceeded for user: " + userId);
        }
    }

    public void addFriends(Long userId, Collection<Friend> friends) {
        RedisSet<Friend> existingFriends = friends(userId);
        for (Friend friend: friends){
            if (friends.size() < maxFriendsCount){
                existingFriends.add(friend);
            }
            else{
                System.out.println("Max friends count exceeded for user: " + userId);
                break;
            }
        }
    }

	public void removeFriend(Long userId, Friend friend) {
		RedisSet<Friend> friends = friends(userId);
		friends.remove(friend);
	}

	public Set<Friend> getFriends(Long userId){
	    RedisSet<Friend> friends = friends(userId);
	    Friend[] friendsArray = friends.toArray(new Friend[friends.size()]);
        return new HashSet<Friend>(Arrays.asList(friendsArray));
	}
	
	/**
	 * Internal method for getting list of feeds from Redis DB
	 */
	private RedisSet<Friend> friends(Long userId) {
	    DefaultRedisSet<Friend> friends = new DefaultRedisSet<Friend>(RedisKeys.friends(userId.toString()), template);
	    return friends;
	}

    public void setTemplate(RedisTemplate<String, Friend> template) {
        this.template = template;
    }

    public void setMaxFriendsCount(int maxFriendsCount) {
        this.maxFriendsCount = maxFriendsCount;
    }
}

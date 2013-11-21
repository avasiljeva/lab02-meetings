package lv.lu.meetings.impl.redis;

/**
 * A common place for Redis DB keys
 */
abstract class RedisKeys {
    
	static final String UID = "uid:";
	
	static String uid(String uid) {
	    return UID + uid;
	}

	static String friends(String uid) {
	    return UID + uid + ":friends";
	}	
}
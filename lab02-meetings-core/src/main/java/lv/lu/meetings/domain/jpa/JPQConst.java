package lv.lu.meetings.domain.jpa;

/**
 * Put names for named JPQL queries and other JPQ related constants here
 */
public final class JPQConst {	

	public final class UserJpq {
	    public static final String QUERY_GET_ID_BY_USERNAME = "User.getIdByUsername";
		public static final String QUERY_GET_USER_BY_USERNAME = "User.getUserByUsername";
		public static final String QUERY_GET_ALL_EXCLUDE_USERNAME = "User.getAllExcludeUsername";
	}
	
	public final class NotificationJpq {
	    public static final String QUERY_GET_BY_USER_ID = "Notification.getByUserUd";
	}
	
	private JPQConst() {}
}

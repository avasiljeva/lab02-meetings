package lv.lu.meetings.interfaces.service;

import java.util.List;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.domain.jpa.notification.Notification;

/**
 * Service of handling operations related to user notifications
 */
public interface NotificationService {

	/**
	 * Send notification about new friendship (established by other user)
	 * 
	 * @param user - notification receiver
	 * @param friend - user who established friendship relation
	 */
	public void addFriendshipNotification(User user, User friend);
	
	/**
	 * Get recent notifications for specified user
	 * 
	 * @param userId
	 */
	public List<Notification> getRecentNotifications(Long userId);
}

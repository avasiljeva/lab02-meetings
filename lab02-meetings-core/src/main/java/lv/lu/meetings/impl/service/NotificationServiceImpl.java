package lv.lu.meetings.impl.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import lv.lu.meetings.domain.jpa.JPQConst;
import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.domain.jpa.notification.FriendshipNotification;
import lv.lu.meetings.domain.jpa.notification.Notification;
import lv.lu.meetings.interfaces.CommonJpaDAO;
import lv.lu.meetings.interfaces.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service of handling operations related to user notifications
 */
@Component
public class NotificationServiceImpl implements NotificationService {

	/* Maximum number of notifications to show to user */
	private static final int MAX_NOTIFICATIONS = 20;
	
	@Autowired
    private CommonJpaDAO jpaDAO;
	
	/**
	 * Create notification about friendship relation establishment
	 * 
	 * @param user - recipient of notification
	 * @param friend - user who established a friendship with recipient
	 */
	public void addFriendshipNotification(User user, User friend) {
		FriendshipNotification notification = new FriendshipNotification();
		notification.setCreateDate(new Date());
		notification.setRecipient(user);
		notification.setFriend(friend);
		jpaDAO.save(notification);
	}

	@SuppressWarnings("unchecked")
	public List<Notification> getRecentNotifications(Long userId) {
		Query query = jpaDAO.getEntityManager().createNamedQuery(JPQConst.NotificationJpq.QUERY_GET_BY_USER_ID);
        query.setParameter("id", userId);
        
        // N recent notifications
        query.setMaxResults(MAX_NOTIFICATIONS);
        List<Notification> notifications = (List<Notification>) query.getResultList();
        
        // sort by date (newest on top)
        Collections.sort(notifications);
        
		return notifications;
	}
}

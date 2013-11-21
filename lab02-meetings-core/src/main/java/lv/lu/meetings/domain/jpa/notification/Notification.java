package lv.lu.meetings.domain.jpa.notification;

import java.util.Date;

import lv.lu.meetings.domain.jpa.User;

/**
 * Interface representing user notification
 */
public interface Notification extends Comparable<Notification> {

	/**
	 * Addressee of the notification
	 */
	public User getRecipient();
	
	public Date getCreateDate();
}

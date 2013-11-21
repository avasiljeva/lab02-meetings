package lv.lu.meetings.domain.jpa.notification;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lv.lu.meetings.domain.jpa.User;

@Entity
@DiscriminatorValue("FRIENDSHIP")
public class FriendshipNotification extends ANotification {
	
	/**
	 * User who established friendship relation with a notification recipient
	 */
	@ManyToOne
	private User friend;

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}
}

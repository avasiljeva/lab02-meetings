package lv.lu.meetings.domain.jpa.notification;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lv.lu.meetings.domain.jpa.JPQConst;
import lv.lu.meetings.domain.jpa.PersistentEntity;
import lv.lu.meetings.domain.jpa.User;

@NamedQueries({
    @NamedQuery(name = JPQConst.NotificationJpq.QUERY_GET_BY_USER_ID, 
		query = "SELECT n FROM ANotification n WHERE n.recipient.id = :id ORDER BY n.createDate DESC")
})
/**
 * Base class for user notification
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="notification")
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING)
public abstract class ANotification implements PersistentEntity, Notification {
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private User recipient;
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	public Long getId() {
		return id;
	}
	
	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int compareTo(Notification other) {
		return Long.compare(other.getCreateDate().getTime(), this.getCreateDate().getTime());
	}
}

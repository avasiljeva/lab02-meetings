package lv.lu.meetings.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@NamedQueries({
	
    @NamedQuery(name = JPQConst.UserJpq.QUERY_GET_USER_BY_USERNAME, 
		query = "SELECT u FROM User u WHERE u.username = :username"),
		
	@NamedQuery(name = JPQConst.UserJpq.QUERY_GET_ID_BY_USERNAME, 
        query = "SELECT u.id FROM User u WHERE u.username = :username"),
        
	// get all users except one with specified username
	@NamedQuery(name = JPQConst.UserJpq.QUERY_GET_ALL_EXCLUDE_USERNAME, 
		query = "SELECT u FROM User u WHERE u.username <> :username")
})

/**
 * Portal user profile
 */
@Entity
public class User implements PersistentEntity, Comparable<User> {
	
	/**
	 * Persistent fields
	 */
	
	@Id @GeneratedValue
	private Long id;

	private String username;

	private String password;
	
	@Transient
	private String passwordConfirm;	// for registration form UI only
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Transient
	private boolean friend;	// flag for UI, to indicate if user is in friendship with a viewer

	/**
	 * Getters and setters
	 */
	
	public Long getId() {
		return id;
	}	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName(){
    	return firstName + " " + lastName;
    }
    
    public boolean isFriend() {
		return friend;
	}

	public void setFriend(boolean friend) {
		this.friend = friend;
	}

	@Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }
}

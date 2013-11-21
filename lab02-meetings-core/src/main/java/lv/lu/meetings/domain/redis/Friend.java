package lv.lu.meetings.domain.redis;

import java.io.Serializable;

import lv.lu.meetings.domain.jpa.User;

/**
 * Friend entity stored in Redis DB
 */
public class Friend implements Serializable, Comparable<Friend> {

    private static final long serialVersionUID = 1L;
    
    private long id;
    
    private String firstName;
    
    private String lastName;

    public Friend(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Friend other = (Friend) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Friend [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

	@Override
	public int compareTo(Friend other) {
		return Long.compare(this.id, other.id);
	}
}

/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class BookingUser extends BookingBaseUser {

	private Integer userId;

	/**
	 * @param bookingId
	 * @param userId
	 */
	public BookingUser(Integer bookingId, Integer userId) {
		super(bookingId);
		this.userId = userId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingUser other = (BookingUser) obj;
		if (this.userId == null) {
			if (other.userId != null)
				return false;
		} else if (!this.userId.equals(other.userId))
			return false;
		return true;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((this.userId == null) ? 0 : this.userId.hashCode());
		return result;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BookingUser [userId=" + this.userId + "]";
	}
}

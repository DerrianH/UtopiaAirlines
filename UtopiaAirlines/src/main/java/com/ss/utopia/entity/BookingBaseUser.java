/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class BookingBaseUser {

	public static final int AGENT = 1;
	public static final int USER = 2;
	public static final int GUEST = 3;
	private Integer bookingId;

	/**
	 * @param bookingId
	 */
	public BookingBaseUser(Integer bookingId) {
		this.bookingId = bookingId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingBaseUser other = (BookingBaseUser) obj;
		if (this.bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!this.bookingId.equals(other.bookingId))
			return false;
		return true;
	}

	/**
	 * @return the bookingId
	 */
	public Integer getBookingId() {
		return this.bookingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.bookingId == null) ? 0 : this.bookingId.hashCode());
		return result;
	}

	/**
	 * @param bookingId
	 *            the bookingId to set
	 */
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	@Override
	public String toString() {
		return "BookingBaseUser [bookingId=" + this.bookingId + "]";
	}
}

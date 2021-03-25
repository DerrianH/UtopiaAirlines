/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class BookingPayment {
	private Integer bookingId;
	private String stripeId;
	private boolean refunded;
	/**
	 * @param bookingId
	 * @param stripeId
	 * @param refunded
	 */
	public BookingPayment(Integer bookingId, String stripeId,
			boolean refunded) {
		this.bookingId = bookingId;
		this.stripeId = stripeId;
		this.refunded = refunded;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingPayment other = (BookingPayment) obj;
		if (this.bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!this.bookingId.equals(other.bookingId))
			return false;
		if (this.refunded != other.refunded)
			return false;
		if (this.stripeId == null) {
			if (other.stripeId != null)
				return false;
		} else if (!this.stripeId.equals(other.stripeId))
			return false;
		return true;
	}
	/**
	 * @return the bookingId
	 */
	public Integer getBookingId() {
		return this.bookingId;
	}
	/**
	 * @return the stripeId
	 */
	public String getStripeId() {
		return this.stripeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.bookingId == null) ? 0 : this.bookingId.hashCode());
		result = prime * result + (this.refunded ? 1231 : 1237);
		result = prime * result
				+ ((this.stripeId == null) ? 0 : this.stripeId.hashCode());
		return result;
	}
	/**
	 * @return the refunded
	 */
	public boolean isRefunded() {
		return this.refunded;
	}
	/**
	 * @param bookingId
	 *            the bookingId to set
	 */
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	/**
	 * @param refunded
	 *            the refunded to set
	 */
	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}
	/**
	 * @param stripeId
	 *            the stripeId to set
	 */
	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}
	@Override
	public String toString() {
		return "BookingPayment [bookingId=" + this.bookingId + ", stripeId="
				+ this.stripeId + ", refunded=" + this.refunded + "]";
	}
}

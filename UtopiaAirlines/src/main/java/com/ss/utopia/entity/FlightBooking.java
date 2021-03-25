/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class FlightBooking {
	private Integer flightId;
	private Integer bookingId;
	/**
	 * @param flightId
	 * @param bookingId
	 */
	public FlightBooking(Integer flightId, Integer bookingId) {
		this.flightId = flightId;
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
		FlightBooking other = (FlightBooking) obj;
		if (this.bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!this.bookingId.equals(other.bookingId))
			return false;
		if (this.flightId == null) {
			if (other.flightId != null)
				return false;
		} else if (!this.flightId.equals(other.flightId))
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
	 * @return the flightId
	 */
	public Integer getFlightId() {
		return this.flightId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.bookingId == null) ? 0 : this.bookingId.hashCode());
		result = prime * result
				+ ((this.flightId == null) ? 0 : this.flightId.hashCode());
		return result;
	}
	/**
	 * @param bookingId
	 *            the bookingId to set
	 */
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	/**
	 * @param flightId
	 *            the flightId to set
	 */
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	@Override
	public String toString() {
		return "FlightBooking [flightId=" + this.flightId + ", bookingId="
				+ this.bookingId + "]";
	}
}

/**
 * 
 */
package com.ss.utopia.entity;

import java.util.List;

/**
 * @author derrianharris
 *
 */
public class Booking {

	private Integer id;
	private Boolean isActive;
	private String confirmationCode;
	private Integer seatType;

	private List<Passenger> passengers;
	/**
	 * @param id
	 * @param isActive
	 * @param confirmationCode
	 */
	public Booking(Integer id, boolean isActive, String confirmationCode,
			Integer seatType) {
		this.id = id;
		this.isActive = isActive;
		this.confirmationCode = confirmationCode;
		this.seatType = seatType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (this.confirmationCode == null) {
			if (other.confirmationCode != null)
				return false;
		} else if (!this.confirmationCode.equals(other.confirmationCode))
			return false;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * @return the confirmationCode
	 */
	public String getConfirmationCode() {
		return this.confirmationCode;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the passengers
	 */
	public List<Passenger> getPassengers() {
		return passengers;
	}
	/**
	 * @return the seatType
	 */
	public Integer getSeatType() {
		return seatType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.confirmationCode == null)
				? 0
				: this.confirmationCode.hashCode());
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return this.isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @param confirmationCode
	 *            the confirmationCode to set
	 */
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param passengers
	 *            the passengers to set
	 */
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	/**
	 * @param seatType
	 *            the seatType to set
	 */
	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}

	@Override
	public String toString() {
		return "[id = " + this.id + ", status = "
				+ (isActive ? "Active " : "Canceled ")
				+ ", Confirmation Code = " + this.confirmationCode
				+ ", seatType = " + Flight.FormatSeatType(this.seatType)
				+ ", Passenger Count = " + passengers.size() + "]";
	}
}

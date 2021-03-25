/**
 * 
 */
package com.ss.utopia.entity;

import java.time.LocalDate;

/**
 * @author derrianharris
 *
 */
public class Passenger {

	private Integer id;
	private String firstName;
	private String LastName;
	private LocalDate dob;
	private String gender;
	private String address;
	private Integer bookingId;

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param dob
	 * @param gender
	 * @param address
	 * @param bookingId
	 */
	public Passenger(Integer id, Integer bookingId, String firstName,
			String lastName, LocalDate dob, String gender, String address) {
		this.id = id;
		this.firstName = firstName;
		this.LastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
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
		Passenger other = (Passenger) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}
	/**
	 * @return the bookingId
	 */
	public Integer getBookingId() {
		return this.bookingId;
	}
	/**
	 * @return the dob
	 */
	public LocalDate getDob() {
		return this.dob;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return this.gender;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.LastName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}
	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @param bookingId
	 *            the bookingId to set
	 */
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.LastName = lastName;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.LastName;
	}
}

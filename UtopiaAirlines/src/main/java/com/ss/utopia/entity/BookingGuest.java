/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class BookingGuest extends BookingBaseUser {

	private String email;
	private String phone;
	/**
	 * @param bookingId
	 * @param email
	 * @param phone
	 */
	public BookingGuest(Integer bookingId, String email, String phone) {
		super(bookingId);
		this.email = email;
		this.phone = phone;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingGuest other = (BookingGuest) obj;
		if (this.email == null) {
			if (other.email != null)
				return false;
		} else if (!this.email.equals(other.email))
			return false;
		if (this.phone == null) {
			if (other.phone != null)
				return false;
		} else if (!this.phone.equals(other.phone))
			return false;
		return true;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result
				+ ((this.phone == null) ? 0 : this.phone.hashCode());
		return result;
	}
	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "BookingGuest [email=" + this.email + ", phone=" + this.phone
				+ "]";
	}

}

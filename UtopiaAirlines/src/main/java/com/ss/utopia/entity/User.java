/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class User {

	private Integer id;
	private Integer roleId;
	private String firstName;
	private String LastName;
	private String username;
	private String email;
	private String password;
	private String phone;

	/**
	 * @param id
	 * @param roleId
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param email
	 * @param password
	 * @param phone
	 */
	public User(Integer id, Integer roleId, String firstName, String lastName,
			String username, String email, String password, String phone) {
		this.id = id;
		this.roleId = roleId;
		this.firstName = firstName;
		this.LastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return this.roleId;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
		LastName = lastName;
	}
	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.LastName;
	}
}

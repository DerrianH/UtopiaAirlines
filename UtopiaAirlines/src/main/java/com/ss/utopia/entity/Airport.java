/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class Airport {
	private String iataId;
	private String city;
	/**
	 * @param iataId
	 * @param city
	 */
	public Airport(String iataId, String city) {
		this.iataId = iataId;
		this.city = city;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (this.iataId == null) {
			if (other.iataId != null)
				return false;
		} else if (!this.iataId.equals(other.iataId))
			return false;
		return true;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}
	/**
	 * @return the iataId
	 */
	public String getIataId() {
		return this.iataId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.iataId == null) ? 0 : this.iataId.hashCode());
		return result;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @param iataId
	 *            the iataId to set
	 */
	public void setIataId(String iataId) {
		this.iataId = iataId;
	}
	@Override
	public String toString() {
		return iataId + ", " + city;
	}
}

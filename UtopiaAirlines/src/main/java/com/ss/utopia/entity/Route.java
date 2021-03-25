/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class Route {
	private Integer id;
	private String originIataId;
	private Airport origin;
	private Airport destination;
	private String destinationIataId;
	/**
	 * @param id
	 * @param originIataId
	 * @param destinationIataId
	 */
	public Route(Integer id, String originIataId, String destinationIataId) {
		this.id = id;
		this.originIataId = originIataId;
		this.destinationIataId = destinationIataId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * @return the destination
	 */
	public Airport getDestination() {
		return this.destination;
	}
	/**
	 * @return the destinationIataId
	 */
	public String getDestinationIataId() {
		return this.destinationIataId;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the origin
	 */
	public Airport getOrigin() {
		return this.origin;
	}
	/**
	 * @return the originIataId
	 */
	public String getOriginIataId() {
		return this.originIataId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}
	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(Airport destination) {
		setDestinationIataId(destination.getIataId());
		this.destination = destination;
	}
	/**
	 * @param destinationIataId
	 *            the destinationIataId to set
	 */
	public void setDestinationIataId(String destinationIataId) {
		this.destinationIataId = destinationIataId;
	}
	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param origin
	 *            the origin to set
	 */
	public void setOrigin(Airport origin) {
		setOriginIataId(origin.getIataId());
		this.origin = origin;
	}
	/**
	 * @param originIataId
	 *            the originIataId to set
	 */
	public void setOriginIataId(String originIataId) {
		this.originIataId = originIataId;
	}
	@Override
	public String toString() {
		return this.origin + " -> " + this.destination;
	}
}

/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class Airplane {
	private Integer id;
	private Integer typeId;

	private AirplaneType airplaneType;
	/**
	 * @param id
	 * @param typeId
	 */
	public Airplane(Integer id, Integer typeId) {
		this.id = id;
		this.typeId = typeId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airplane other = (Airplane) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		if (this.typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!this.typeId.equals(other.typeId))
			return false;
		return true;
	}
	/**
	 * @return the airplaneType
	 */
	public AirplaneType getAirplaneType() {
		return airplaneType;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return this.typeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result
				+ ((this.typeId == null) ? 0 : this.typeId.hashCode());
		return result;
	}
	/**
	 * @param airplaneType
	 *            the airplaneType to set
	 */
	public void setAirplaneType(AirplaneType airplaneType) {
		this.airplaneType = airplaneType;
	}
	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "[ id: " + id + " " + airplaneType.toString() + " ]";
	}
}

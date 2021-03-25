/**
 * 
 */
package com.ss.utopia.entity;

/**
 * @author derrianharris
 *
 */
public class AirplaneType {
	private Integer id;
	private Integer firstClassCapacity;
	private Integer businessCapacity;
	private Integer economyCapacity;
	/**
	 * @param id
	 * @param firstClassCapacity
	 * @param businessCapacity
	 * @param economyCapacity
	 */
	public AirplaneType(Integer id, Integer firstClassCapacity,
			Integer businessCapacity, Integer economyCapacity) {
		this.id = id;
		this.firstClassCapacity = firstClassCapacity;
		this.businessCapacity = businessCapacity;
		this.economyCapacity = economyCapacity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirplaneType other = (AirplaneType) obj;
		if (this.businessCapacity == null) {
			if (other.businessCapacity != null)
				return false;
		} else if (!this.businessCapacity.equals(other.businessCapacity))
			return false;
		if (this.economyCapacity == null) {
			if (other.economyCapacity != null)
				return false;
		} else if (!this.economyCapacity.equals(other.economyCapacity))
			return false;
		if (this.firstClassCapacity == null) {
			if (other.firstClassCapacity != null)
				return false;
		} else if (!this.firstClassCapacity.equals(other.firstClassCapacity))
			return false;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * @return the businessCapacity
	 */
	public Integer getBusinessCapacity() {
		return this.businessCapacity;
	}
	/**
	 * @return the economyCapacity
	 */
	public Integer getEconomyCapacity() {
		return this.economyCapacity;
	}
	/**
	 * @return the firstClassCapacity
	 */
	public Integer getFirstClassCapacity() {
		return this.firstClassCapacity;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.businessCapacity == null)
				? 0
				: this.businessCapacity.hashCode());
		result = prime * result + ((this.economyCapacity == null)
				? 0
				: this.economyCapacity.hashCode());
		result = prime * result + ((this.firstClassCapacity == null)
				? 0
				: this.firstClassCapacity.hashCode());
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}
	/**
	 * @param businessCapacity
	 *            the businessCapacity to set
	 */
	public void setBusinessCapacity(Integer businessCapacity) {
		this.businessCapacity = businessCapacity;
	}
	/**
	 * @param economyCapacity
	 *            the economyCapacity to set
	 */
	public void setEconomyCapacity(Integer economyCapacity) {
		this.economyCapacity = economyCapacity;
	}

	/**
	 * @param firstClassCapacity
	 *            the firstClassCapacity to set
	 */
	public void setFirstClassCapacity(Integer firstClassCapacity) {
		this.firstClassCapacity = firstClassCapacity;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AirplaneType [id=" + this.id + ", firstClassCapacity="
				+ this.firstClassCapacity + ", businessCapacity="
				+ this.businessCapacity + ", economyCapacity="
				+ this.economyCapacity + "]";
	}

}

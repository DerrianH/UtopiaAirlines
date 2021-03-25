/**
 * 
 */
package com.ss.utopia.entity;

import java.time.LocalDateTime;

/**
 * @author derrianharris
 *
 */
public class Flight {

	public static final int FIRST = 1;
	public static final int BUSINESS = 2;
	public static final int ECONOMY = 3;

	public static String FormatSeatType(Integer type) {
		switch (type) {
			case Flight.FIRST :
				return "First";
			case Flight.BUSINESS :
				return "Business";
			case Flight.ECONOMY :
				return "Economy";
			default :
				return "Unknown";

		}
	}
	private Integer id;
	private Integer routeId;
	private Integer airplaneId;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private Integer reservedFirstSeats;

	private Float firstSeatPrice;
	private Integer reservedBusinessSeats;

	private Float BusinessSeatPrice;
	private Integer reservedEconomySeats;

	private Float economySeatPrice;
	private Route route;

	private Airplane plane;

	/**
	 * @param id
	 * @param routeId
	 * @param airplaneId
	 * @param departureTime
	 * @param reservedFirstSeats
	 * @param firstSeatPrice
	 * @param reservedBusinessSeats
	 * @param businessSeatPrice
	 * @param reservedEconomySeats
	 * @param economySeatPrice
	 */
	public Flight(Integer id, Integer routeId, Integer airplaneId,
			LocalDateTime departureTime, LocalDateTime arrivalTime,
			Integer reservedFirstSeats, Float firstSeatPrice,
			Integer reservedBusinessSeats, Float businessSeatPrice,
			Integer reservedEconomySeats, Float economySeatPrice) {
		this.id = id;
		this.routeId = routeId;
		this.airplaneId = airplaneId;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.reservedFirstSeats = reservedFirstSeats;
		this.firstSeatPrice = firstSeatPrice;
		this.reservedBusinessSeats = reservedBusinessSeats;
		this.BusinessSeatPrice = businessSeatPrice;
		this.reservedEconomySeats = reservedEconomySeats;
		this.economySeatPrice = economySeatPrice;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * @return the plane
	 */
	public Airplane getAirplane() {
		return plane;
	}
	/**
	 * @return the airplaneId
	 */
	public Integer getAirplaneId() {
		return this.airplaneId;
	}
	/**
	 * @return the arrivalTime
	 */
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public Integer getAvailibleBusinessSeats() {
		return Integer.max( getAirplane().getAirplaneType().getBusinessCapacity()
				- getReservedBusinessSeats(),0);
	}
	public Integer getAvailibleEconomySeats() {
		return Integer.max(getAirplane().getAirplaneType().getEconomyCapacity()
				- getReservedEconomySeats(),0);
	}
	public Integer getAvailibleFirstSeats() {
		return Integer.max(getAirplane().getAirplaneType().getFirstClassCapacity()
				- getReservedFirstSeats(),0);
	}

	/**
	 * @return the businessSeatPrice
	 */
	public Float getBusinessSeatPrice() {
		return this.BusinessSeatPrice;
	}

	/**
	 * @return the departureTime
	 */
	public LocalDateTime getDepartureTime() {
		return this.departureTime;
	}

	/**
	 * @return the economySeatPrice
	 */
	public Float getEconomySeatPrice() {
		return this.economySeatPrice;
	}
	/**
	 * @return the firstSeatPrice
	 */
	public Float getFirstSeatPrice() {
		return this.firstSeatPrice;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the reservedBusinessSeats
	 */
	public Integer getReservedBusinessSeats() {
		return this.reservedBusinessSeats;
	}
	/**
	 * @return the reservedEconomySeats
	 */
	public Integer getReservedEconomySeats() {
		return this.reservedEconomySeats;
	}
	/**
	 * @return the reservedFirstSeats
	 */
	public Integer getReservedFirstSeats() {
		return this.reservedFirstSeats;
	}
	/**
	 * @return the routes
	 */
	public Route getRoute() {
		return route;
	}
	/**
	 * @return the routeId
	 */
	public Integer getRouteId() {
		return this.routeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}
	/**
	 * @param plane
	 *            the plane to set
	 */
	public void setAirplane(Airplane plane) {
		airplaneId = plane.getId();
		this.plane = plane;
	}
	/**
	 * @param airplaneId
	 *            the airplaneId to set
	 */
	public void setAirplaneId(Integer airplaneId) {
		this.airplaneId = airplaneId;
	}
	/**
	 * @param dt
	 *            the arrivalTime to set
	 */
	public void setArrivalTime(LocalDateTime dt) {
		this.arrivalTime = dt;
	}
	/**
	 * @param businessSeatPrice
	 *            the businessSeatPrice to set
	 */
	public void setBusinessSeatPrice(Float businessSeatPrice) {
		this.BusinessSeatPrice = businessSeatPrice;
	}
	/**
	 * @param dt
	 *            the departureTime to set
	 */
	public void setDepartureTime(LocalDateTime dt) {
		this.departureTime = dt;
	}
	/**
	 * @param economySeatPrice
	 *            the economySeatPrice to set
	 */
	public void setEconomySeatPrice(Float economySeatPrice) {
		this.economySeatPrice = economySeatPrice;
	}

	/**
	 * @param firstSeatPrice
	 *            the firstSeatPrice to set
	 */
	public void setFirstSeatPrice(Float firstSeatPrice) {
		this.firstSeatPrice = firstSeatPrice;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param reservedBusinessSeats
	 *            the reservedBusinessSeats to set
	 */
	public void setReservedBusinessSeats(Integer reservedBusinessSeats) {
		this.reservedBusinessSeats = Math.max(Math.min(reservedBusinessSeats,
				plane.getAirplaneType().getBusinessCapacity()), 0);
	}

	/**
	 * @param reservedEconomySeats
	 *            the reservedEconomySeats to set
	 */
	public void setReservedEconomySeats(Integer reservedEconomySeats) {
		this.reservedEconomySeats = Math.max(Math.min(reservedEconomySeats,
				plane.getAirplaneType().getEconomyCapacity()), 0);
	}

	/**
	 * @param reservedFirstSeats
	 *            the reservedFirstSeats to set
	 */
	public void setReservedFirstSeats(Integer reservedFirstSeats) {

		this.reservedFirstSeats = Math.max(Math.min(reservedFirstSeats,
				plane.getAirplaneType().getFirstClassCapacity()), 0);
	}

	/**
	 * @param routes
	 *            the routes to set
	 */
	public void setRoute(Route route) {
		setRouteId(route.getId());
		this.route = route;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	@Override
	public String toString() {
		return "Flight [id=" + this.id + ", routeId=" + this.routeId
				+ ", airplaneId=" + this.airplaneId + ", departureTime="
				+ this.departureTime + ", arrivalTime=" + this.arrivalTime
				+ ", reservedFirstSeats=" + this.reservedFirstSeats
				+ ", firstSeatPrice=" + this.firstSeatPrice
				+ ", reservedBusinessSeats=" + this.reservedBusinessSeats
				+ ", BusinessSeatPrice=" + this.BusinessSeatPrice
				+ ", reservedEconomySeats=" + this.reservedEconomySeats
				+ ", economySeatPrice=" + this.economySeatPrice + ", route="
				+ this.route + ", plane=" + this.plane + "]";
	}

}

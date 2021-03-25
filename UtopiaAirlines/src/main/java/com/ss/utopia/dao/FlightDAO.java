/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;

/**
 * @author derrianharris
 *
 */
public class FlightDAO extends BaseDAO<Flight> {

	public FlightDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(Flight flight)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{flight.getId(), flight.getRouteId(),
				flight.getAirplaneId(), flight.getDepartureTime(),
				flight.getArrivalTime(), flight.getReservedFirstSeats(),
				flight.getFirstSeatPrice(), flight.getReservedBusinessSeats(),
				flight.getBusinessSeatPrice(), flight.getReservedEconomySeats(),
				flight.getEconomySeatPrice()};
		String[] columns = new String[]{"id", "route_id", "airplane_id",
				"departure_time", "arrival_time", "reserved_first",
				"first_price", "reserved_business", "business_price",
				"reserved_economy", "economy_price"};
		return executeQueryPk(QueryBuilder.insertQuery("flight", columns),
				values);
	}

	@Override
	public void deleteData(Flight flight)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{flight.getId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "id"), values);
	}

	@Override
	protected List<Flight> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<Flight> flights = new ArrayList<Flight>();
		while (rs.next()) {
			Integer id = rs.getInt("id");
			Integer routeId = rs.getInt("route_id");
			Integer airplaneId = rs.getInt("airplane_id");

			Timestamp dTimestamp = rs.getTimestamp("departure_time");
			LocalDateTime departureTime = dTimestamp.toLocalDateTime();

			Timestamp aTimestamp = rs.getTimestamp("arrival_time");
			LocalDateTime arrivalTime = aTimestamp.toLocalDateTime();

			Integer reservedFirst = rs.getInt("reserved_first");
			Float firstPrice = rs.getFloat("first_price");

			Integer reservedBusiness = rs.getInt("reserved_business");
			Float businessPrice = rs.getFloat("business_price");

			Integer reservedEconomy = rs.getInt("reserved_economy");
			Float economyPrice = rs.getFloat("economy_price");

			Flight flight = new Flight(id, routeId, airplaneId, departureTime,
					arrivalTime, reservedFirst, firstPrice, reservedBusiness,
					businessPrice, reservedEconomy, economyPrice);
			flights.add(flight);
		}
		return flights;
	}

	public List<Flight> getBookedFlights(User user)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{user.getId()};
		return readDataQuery(
				"SELECT * FROM (((( flight INNER JOIN flight_bookings ON id = flight_bookings.flight_id) INNER JOIN booking ON flight_bookings.booking_id = booking.id) INNER JOIN booking_user ON booking_user.booking_id = booking.id) INNER JOIN user ON booking_user.user_id = ?) where (booking.is_active = true)",
				values);
	}

	public Flight getFlightById(Integer id)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{id};
		return readDataQuery(QueryBuilder.selectQueryCond(tableName, "*", "id"),
				values).get(0);
	}

	@Override
	public void updateData(Flight flight)
			throws ClassNotFoundException, SQLException {

		Object[] values = new Object[]{flight.getRouteId(),
				flight.getAirplaneId(), flight.getDepartureTime(),
				flight.getArrivalTime(), flight.getReservedFirstSeats(),
				flight.getFirstSeatPrice(), flight.getReservedBusinessSeats(),
				flight.getBusinessSeatPrice(), flight.getReservedEconomySeats(),
				flight.getEconomySeatPrice(), flight.getId()};
		String[] columns = new String[]{"route_id", "airplane_id",
				"departure_time", "arrival_time", "reserved_first",
				"first_price", "reserved_business", "business_price",
				"reserved_economy", "economy_price"};
		executeQuery(QueryBuilder.updateQuery(tableName, columns, "id"),
				values);
	}

}

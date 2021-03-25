/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.FlightBooking;

/**
 * @author derrianharris
 *
 */
public class FlightBookingDAO extends BaseDAO<FlightBooking> {

	public FlightBookingDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(FlightBooking flightBooking)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{flightBooking.getFlightId(),
				flightBooking.getBookingId()};
		String[] columns = new String[]{"flight_id", "booking_id"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(FlightBooking flightBooking)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{flightBooking.getFlightId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "flight_id"), values);
	}

	@Override
	protected List<FlightBooking> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<FlightBooking> flightBookings = new ArrayList<FlightBooking>();

		while (rs.next()) {
			Integer flightId = rs.getInt("flight_id");
			Integer bookingId = rs.getInt("booking_id");
			FlightBooking flightBooking = new FlightBooking(flightId,
					bookingId);

			flightBookings.add(flightBooking);
		}
		return flightBookings;
	}

	@Override
	public void updateData(FlightBooking flightBooking)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{flightBooking.getFlightId(),
				flightBooking.getBookingId(), flightBooking.getFlightId()};
		String[] columns = new String[]{"flight_id", "booking_id"};
		executeQuery(QueryBuilder.updateQuery(tableName, columns, "flight_id"),
				values);
	}

}

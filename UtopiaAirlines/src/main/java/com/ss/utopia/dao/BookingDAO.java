/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;

/**
 * @author derrianharris
 *
 */
public class BookingDAO extends BaseDAO<Booking> {

	public BookingDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(Booking booking)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{booking.isActive(),
				booking.getConfirmationCode(), booking.getSeatType(),};
		String[] columns = new String[]{"is_active", "confirmation_code",
				"seat_type"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(Booking booking)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{booking.getId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "id"), value);
	}

	@Override
	protected List<Booking> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<Booking> bookings = new ArrayList<Booking>();

		while (rs.next()) {
			Integer id = rs.getInt("id");
			Boolean isActive = rs.getBoolean("is_active");
			String confirmationCode = rs.getString("confirmation_code");
			Integer seatType = rs.getInt("seat_type");
			Booking booking = new Booking(id, isActive, confirmationCode,
					seatType);

			bookings.add(booking);
		}
		return bookings;
	}

	public Booking getActiveBookingFromId(Integer id)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{id};
		return readDataQuery(
				"SELECT * FROM booking where is_active = true and booking.id = ?",
				value).get(0);
	}

	public List<Booking> getAllActiveBookings()
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{true};
		return readDataQuery(
				QueryBuilder.selectQueryCond(tableName, "*", "is_active"),
				value);
	}

	public List<Booking> getAllCanceledBookings()
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{false};
		return readDataQuery(
				QueryBuilder.selectQueryCond(tableName, "*", "is_active"),
				value);
	}

	public Booking getBookingFromId(Integer bookingId)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{bookingId};
		return readDataQuery(QueryBuilder.selectQueryCond(tableName, "*", "id"),
				value).get(0);
	}

	public Booking getBookingFromUserFlight(User user, Flight flight)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{user.getId(), flight.getId()};
		return readDataQuery(
				"SELECT * FROM ((booking inner join booking_user on booking.id = booking_user.booking_id and booking_user.user_id = ? and booking.is_active = true) inner join flight_bookings on booking.id = flight_bookings.booking_id and flight_bookings.flight_id = ?)",
				value).get(0);
	}

	@Override
	public void updateData(Booking booking)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{booking.isActive(),
				booking.getConfirmationCode(), booking.getSeatType(),
				booking.getId()};
		String[] columns = new String[]{"is_active", "confirmation_code",
				"seat_type"};
		executeQuery(QueryBuilder.updateQuery(tableName, columns, "id"),
				values);
	}

}

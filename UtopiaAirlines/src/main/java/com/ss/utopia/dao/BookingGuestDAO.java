/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingGuest;

/**
 * @author derrianharris
 *
 */
public class BookingGuestDAO extends BaseDAO<BookingGuest> {

	public BookingGuestDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(BookingGuest guest)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{guest.getBookingId(), guest.getEmail(),
				guest.getPhone()};
		String[] columns = new String[]{"booking_id","contact_email","contact_phone"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(BookingGuest guest)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{guest.getBookingId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "booking_id"),
				value);
	}

	@Override
	protected List<BookingGuest> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<BookingGuest> bookingGuests = new ArrayList<BookingGuest>();

		while (rs.next()) {
			Integer bookingId = rs.getInt("booking_id");
			String email = rs.getString("contact_email");
			String phone = rs.getString("contact_phone");
			BookingGuest bookingGuest = new BookingGuest(bookingId, email,
					phone);

			bookingGuests.add(bookingGuest);
		}
		return bookingGuests;
	}

	@Override
	public void updateData(BookingGuest guest)
			throws ClassNotFoundException, SQLException {
		Object[] values =new Object[]{guest.getEmail(), guest.getPhone(),
				guest.getBookingId()};
		String[] columns = new String[]{"contact_email","contact_phone","booking_id"};
		executeQuery(
				QueryBuilder.updateQuery(tableName, columns,"booking_id"),
				values);
	}
}

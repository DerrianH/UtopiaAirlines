/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingPayment;

/**
 * @author derrianharris
 *
 */
public class BookingPaymentDAO extends BaseDAO<BookingPayment> {

	public BookingPaymentDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(BookingPayment payment)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{payment.getBookingId(), payment.getStripeId(),
				payment.isRefunded()};
		String[] columns = new String[] {"booking_id","stripe_id","refunded"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(BookingPayment payment)
			throws ClassNotFoundException, SQLException {
		
		Object[] value = new Object[]{payment.getBookingId()};
		executeQuery(QueryBuilder.insertQuery(tableName, "booking_id"),
				value);
	}

	@Override
	protected List<BookingPayment> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<BookingPayment> bookingsUsers = new ArrayList<BookingPayment>();

		while (rs.next()) {
			Integer bookingId = rs.getInt("booking_id");
			String stripeId = rs.getString("stripe_id");
			Boolean isRefunded = rs.getBoolean("refunded");
			BookingPayment bookingUser = new BookingPayment(bookingId, stripeId,
					isRefunded);

			bookingsUsers.add(bookingUser);
		}
		return bookingsUsers;
	}

	@Override
	public void updateData(BookingPayment payment)
			throws ClassNotFoundException, SQLException {
		Object[] values =new Object[]{payment.getStripeId(), payment.isRefunded(),
				payment.getBookingId()};
		String[] columns = new String[] {"stripe_id","refunded"};
		executeQuery(
				QueryBuilder.updateQuery(tableName, columns, "booking_id"),
				values);
	}
}

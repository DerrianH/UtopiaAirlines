/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.User;

/**
 * @author derrianharris
 *
 */
public class BookingUserDAO extends BaseDAO<BookingUser> {

	public String idName = "user_id";

	public BookingUserDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(BookingUser user)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{user.getBookingId(), user.getUserId()};
		String[] columns = new String[]{"booking_id", idName};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(BookingUser user)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{user.getBookingId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "booking_id"), value);
	}

	@Override
	protected List<BookingUser> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<BookingUser> bookingsUsers = new ArrayList<BookingUser>();

		while (rs.next()) {
			Integer bookingId = rs.getInt("booking_id");
			Integer userId = rs.getInt("user_id");
			BookingUser bookingUser = new BookingUser(bookingId, userId);

			bookingsUsers.add(bookingUser);
		}
		return bookingsUsers;
	}

	public List<BookingUser> getAllBookingUserFromUser(User user)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{user.getId()};
		return readDataQuery(
				QueryBuilder.selectQueryCond(tableName, "*", idName), value);
	}

	@Override
	public void updateData(BookingUser user)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{user.getBookingId(), user.getUserId(),
				user.getBookingId()};
		String[] columns = new String[]{"booking_id", idName};
		executeQuery(QueryBuilder.updateQuery(tableName, columns, "booking_id"),
				values);
	}

}

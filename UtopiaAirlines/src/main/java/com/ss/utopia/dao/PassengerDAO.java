/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Passenger;

/**
 * @author derrianharris
 *
 */
public class PassengerDAO extends BaseDAO<Passenger> {

	public PassengerDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(Passenger passenger)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{passenger.getBookingId(),
				passenger.getFirstName(), passenger.getLastName(),
				passenger.getDob(), passenger.getGender(),
				passenger.getAddress()};
		String[] columns = new String[]{"booking_id", "given_name",
				"family_name", "dob", "gender", "address"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(Passenger passenger)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{passenger.getId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "id"), value);
	}

	@Override
	protected List<Passenger> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = new ArrayList<Passenger>();

		while (rs.next()) {
			Integer id = rs.getInt("id");
			Integer bookingId = rs.getInt("booking_id");
			String firstName = rs.getString("given_name");
			String lastName = rs.getString("family_name");
			LocalDate dob = rs.getDate("dob").toLocalDate();
			String gender = rs.getString("gender");
			String address = rs.getString("address");
			Passenger passenger = new Passenger(id, bookingId, firstName,
					lastName, dob, gender, address);
			passengers.add(passenger);
		}
		return passengers;
	}

	public List<Passenger> getPassngersFromBooking(Booking booking)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{booking.getId()};
		return readDataQuery(
				QueryBuilder.selectQueryCond(tableName, "*", "booking_id"),
				values);
	}

	public Passenger getUserFromId(Integer id)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{id};
		return readDataQuery(QueryBuilder.selectQueryCond(tableName, "*", "id"),
				values).get(0);
	}

	@Override
	public void updateData(Passenger passenger)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{passenger.getBookingId(),
				passenger.getFirstName(), passenger.getLastName(),
				passenger.getDob(), passenger.getGender(),
				passenger.getAddress(), passenger.getId()};
		String[] columns = new String[]{"booking_id", "given_name",
				"family_name", "dob", "gender", "address"};
		executeQuery(QueryBuilder.updateQuery(tableName, columns, "id"),
				values);
	}

}

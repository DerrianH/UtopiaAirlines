/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.User;

/**
 * @author derrianharris
 *
 */
public class UserDAO extends BaseDAO<User> {

	public UserDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(User user)
			throws ClassNotFoundException, SQLException {

		Object[] values = new Object[]{user.getRoleId(), user.getFirstName(),
				user.getLastName(), user.getUsername(), user.getEmail(),
				user.getPassword(), user.getPhone()};
		String[] columns = new String[]{"role_id", "given_name", "family_name",
				"username", "email", "password", "phone"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(User user)
			throws ClassNotFoundException, SQLException {
		executeQuery(QueryBuilder.deleteQuery(tableName, "id"),
				new Object[]{user.getId()});
	}

	@Override
	protected List<User> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<User>();

		while (rs.next()) {
			Integer id = rs.getInt("id");
			Integer roleId = rs.getInt("role_id");
			String firstName = rs.getString("given_name");
			String lastName = rs.getString("family_name");
			String username = rs.getString("username");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String phone = rs.getString("phone");
			User user = new User(id, roleId, firstName, lastName, username,
					email, password, phone);
			users.add(user);
		}
		return users;
	}

	public User getUserFromId(Integer id) throws ClassNotFoundException,
			SQLException, IndexOutOfBoundsException {
		Object[] values = new Object[]{id};
		return readDataQuery(QueryBuilder.selectQueryCond(tableName, "*", "id"),
				values).get(0);
	}

	public User getUserFromUserAndPass(String username, String password)
			throws ClassNotFoundException, SQLException,
			IndexOutOfBoundsException {
		Object[] values = new Object[]{username, password};
		return readDataQuery("select * from " + tableName
				+ " where (username = ? and password = ?)", values).get(0);
	}

	public List<User> getUsersFromRole(Integer roleId)
			throws ClassNotFoundException, SQLException,
			IndexOutOfBoundsException {
		Object[] values = new Object[]{roleId};
		return readDataQuery(
				QueryBuilder.selectQueryCond(tableName, "*", "role_id"),
				values);
	}

	@Override
	public void updateData(User user)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{user.getRoleId(), user.getFirstName(),
				user.getLastName(), user.getUsername(), user.getEmail(),
				user.getPassword(), user.getPhone(), user.getId()};
		String[] columns = new String[]{"role_id", "given_name", "family_name",
				"username", "email", "password", "phone"};
		executeQuery(QueryBuilder.updateQuery(tableName, columns, "id"),
				values);
	}

}

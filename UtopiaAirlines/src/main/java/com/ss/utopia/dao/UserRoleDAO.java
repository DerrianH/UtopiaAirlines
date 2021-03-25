/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.UserRole;

/**
 * @author derrianharris
 *
 */
public class UserRoleDAO extends BaseDAO<UserRole> {

	public UserRoleDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(UserRole role)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{role.getName()};
		String[] column = new String[] {"name"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, column),
				value);
	}

	@Override
	public void deleteData(UserRole role)
			throws ClassNotFoundException, SQLException {
		Object[] value = new Object[]{role.getId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "id"),
				value);
	}

	@Override
	protected List<UserRole> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<UserRole> roles = new ArrayList<UserRole>();

		while (rs.next()) {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			UserRole role = new UserRole(id, name);

			roles.add(role);
		}
		return roles;
	}

	@Override
	public void updateData(UserRole role)
			throws ClassNotFoundException, SQLException {
		Object[] values= new Object[]{role.getName(), role.getId()};
		String[] column = new String[] {"name"};
		executeQuery(QueryBuilder.updateQuery(tableName, column, "id"),
				values);
	}
}

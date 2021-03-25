/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airplane;

/**
 * @author derrianharris
 *
 */
public class AirplaneDAO extends BaseDAO<Airplane> {

	public AirplaneDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}
	@Override
	public Integer addData(Airplane airplane)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{airplane.getTypeId()};
		String[] columns = new String[]{"type_id"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}
	@Override
	public void deleteData(Airplane airport)
			throws ClassNotFoundException, SQLException {
		executeQuery("delete from airplane where id = ?",
				new Object[]{airport.getId()});
	}
	@Override
	protected List<Airplane> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<Airplane> airplanes = new ArrayList<Airplane>();
		while (rs.next()) {
			Integer id = rs.getInt("id");
			Integer airplaneType = rs.getInt("type_id");
			Airplane airplane = new Airplane(id, airplaneType);

			airplanes.add(airplane);
		}
		return airplanes;
	}

	public Airplane getAirplaneById(Integer id)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{id};
		return readDataQuery(QueryBuilder.selectQueryCond(tableName, "*", "id"),
				values).get(0);
	}

	@Override
	public void updateData(Airplane airplane)
			throws ClassNotFoundException, SQLException {
		executeQuery("update airplane set type_id = ? where id = ?",
				new Object[]{airplane.getTypeId(), airplane.getId()});
	}

}

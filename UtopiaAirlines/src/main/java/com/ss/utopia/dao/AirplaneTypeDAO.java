/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.AirplaneType;

/**
 * @author derrianharris
 *
 */
public class AirplaneTypeDAO extends BaseDAO<AirplaneType> {

	public AirplaneTypeDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(AirplaneType type)
			throws ClassNotFoundException, SQLException {

		Object[] values = new Object[]{type.getFirstClassCapacity(),
				type.getBusinessCapacity(), type.getEconomyCapacity()};
		String[] columns = new String[]{"first_class_capacity",
				"business_capacity", "economy_capacity"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(AirplaneType type)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{type.getId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "id"), values);
	}

	@Override
	protected List<AirplaneType> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<AirplaneType> airplaneTypes = new ArrayList<AirplaneType>();
		while (rs.next()) {
			Integer id = rs.getInt("id");
			Integer firstCapacity = rs.getInt("first_class_capacity");
			Integer businessCapacity = rs.getInt("business_capacity");
			Integer economyCapacity = rs.getInt("economy_capacity");
			AirplaneType airplaneType = new AirplaneType(id, firstCapacity,
					businessCapacity, economyCapacity);

			airplaneTypes.add(airplaneType);
		}
		return airplaneTypes;
	}

	public AirplaneType getAirplaneTypeById(Integer id)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{id};
		return readDataQuery(QueryBuilder.selectQueryCond(tableName, "*", "id"),
				values).get(0);
	}

	@Override
	public void updateData(AirplaneType type)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{type.getFirstClassCapacity(),
				type.getBusinessCapacity(), type.getEconomyCapacity(),
				type.getId()};
		String[] columns = new String[]{"first_class_capacity",
				"business_capacity", "economy_capacity"};
		executeQuery(QueryBuilder.updateQuery(tableName, columns, "id"),
				values);
	}

}

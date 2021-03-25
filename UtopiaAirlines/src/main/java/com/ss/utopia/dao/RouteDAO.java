/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Route;

/**
 * @author derrianharris
 *
 */
public class RouteDAO extends BaseDAO<Route> {

	public RouteDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(Route route)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{route.getOriginIataId(),
				route.getDestinationIataId()};
		String[] columns = new String[]{"origin_id", "destination_id"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(Route route)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{route.getId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "id"), values);
	}

	@Override
	protected List<Route> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<Route> routes = new ArrayList<Route>();
		while (rs.next()) {
			Integer id = rs.getInt("id");
			String originIataId = rs.getString("origin_id");
			String destinationIataId = rs.getString("destination_id");
			Route route = new Route(id, originIataId, destinationIataId);

			routes.add(route);
		}
		return routes;
	}

	public Route getRouteFromId(Integer id)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{id};
		return readDataQuery(QueryBuilder.selectQueryCond(tableName, "*", "id"),
				values).get(0);
	}

	public Route getRouteFromOriDest(String org, String dest)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{org, dest};
		return readDataQuery(
				"select * from " + tableName
						+ " where( origin_id = ? and destination_id = ?)",
				values).get(0);
	}

	@Override
	public void updateData(Route route)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{route.getOriginIataId(),
				route.getDestinationIataId(), route.getId()};
		String[] columns = new String[]{"origin_id", "destination_id"};
		executeQuery(QueryBuilder.updateQuery(tableName, columns, "id"),
				values);
	}
}

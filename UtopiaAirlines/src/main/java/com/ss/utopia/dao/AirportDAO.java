/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;

/**
 * @author derrianharris
 *
 */
public class AirportDAO extends BaseDAO<Airport> {

	public AirportDAO(Connection conn, String tableName) {
		super(conn, tableName);
	}

	@Override
	public Integer addData(Airport airport)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{airport.getIataId(), airport.getCity()};
		String[] columns = new String[]{"iata_id", "city"};
		return executeQueryPk(QueryBuilder.insertQuery(tableName, columns),
				values);
	}

	@Override
	public void deleteData(Airport airport)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{airport.getIataId()};
		executeQuery(QueryBuilder.deleteQuery(tableName, "iata_id"), values);
	}

	@Override
	protected List<Airport> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		List<Airport> airports = new ArrayList<Airport>();
		while (rs.next()) {
			String id = rs.getString("iata_id");
			String city = rs.getString("city");
			Airport airport = new Airport(id, city);

			airports.add(airport);
		}
		return airports;
	}

	public Airport getAirportFromCode(String iataId)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{iataId};
		return readDataQuery(
				QueryBuilder.selectQueryCond(tableName, "*", "iata_id"), values)
						.get(0);
	}

	public void updateAirportFromOldIATA(Airport airport, String oldIata)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{airport.getCity(), airport.getIataId(),
				oldIata};
		executeQuery(
				"update airport set city = ?, iata_id = ? where iata_id = ?",
				values);
	}

	@Override
	public void updateData(Airport airport)
			throws ClassNotFoundException, SQLException {
		Object[] values = new Object[]{airport.getCity(), airport.getIataId()};
		executeQuery(
				"update airport set city = ?, iata_id = ? where iata_id = ?",
				values);
	}

}

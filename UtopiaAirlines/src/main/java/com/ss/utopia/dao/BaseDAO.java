/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author derrianharris
 *
 */
public abstract class BaseDAO<T> {
	protected Connection conn = null;
	protected String tableName = "";

	public BaseDAO(Connection conn, String tableName) {
		this.conn = conn;
		this.tableName = tableName;
	}

	public abstract Integer addData(T data)
			throws ClassNotFoundException, SQLException;

	public abstract void deleteData(T data)
			throws ClassNotFoundException, SQLException;

	protected void executeQuery(String sql, Object[] vals)
			throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int count = 1;
		for (Object o : vals) {
			pstmt.setObject(count, o);
			count++;
		}
		pstmt.executeUpdate();
	}

	protected Integer executeQueryPk(String sql, Object[] vals)
			throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		for (Object o : vals) {
			pstmt.setObject(count, o);
			count++;
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();

		if (rs.next()) {
			return Integer.valueOf(rs.getInt(1));
		}
		return null;
	}

	abstract protected List<T> extractData(ResultSet rs)
			throws ClassNotFoundException, SQLException;

	public List<T> getAllData() throws ClassNotFoundException, SQLException {
		return readDataQuery(QueryBuilder.selectAllQuery(tableName));
	}

	protected List<T> readDataQuery(String sql)
			throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}

	protected List<T> readDataQuery(String sql, Object[] vals)
			throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int count = 1;
		for (Object o : vals) {
			pstmt.setObject(count, o);
			count++;
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}

	public abstract void updateData(T data)
			throws ClassNotFoundException, SQLException;

}

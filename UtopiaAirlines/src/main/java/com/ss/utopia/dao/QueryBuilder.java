/**
 * 
 */
package com.ss.utopia.dao;

import java.util.Arrays;

/**
 * @author derrianharris
 *
 */
public class QueryBuilder {

	public static String deleteQuery(String tableName, String id) {
		StringBuilder result = new StringBuilder(
				"delete from " + tableName + " where " + id + " = ?");
		return result.toString();
	}

	public static String insertQuery(String tableName, String column) {
		return insertQuery(tableName, new String[]{column});
	}

	public static String insertQuery(String tableName, String[] columns) {
		String[] valuesArr = new String[columns.length];
		Arrays.fill(valuesArr, "?");

		String columnsStr = String.join("`, `", columns);
		StringBuilder result = new StringBuilder(
				"insert into " + tableName + " (`" + columnsStr + "`)"
						+ " values(" + String.join(", ", valuesArr) + ")");
		return result.toString();
	}

	public static String selectAllQuery(String tableName) {
		return selectQuery(tableName, "*");
	}

	public static String selectQuery(String tableName, String selection) {
		return selectQuery(tableName, new String[]{selection});
	}

	public static String selectQuery(String tableName, String[] selections) {
		StringBuilder result = new StringBuilder("select "
				+ String.join(", ", selections) + " from " + tableName);
		return result.toString();
	}

	public static String selectQueryCond(String tableName, String selection,
			String idName) {
		return selectQuery(tableName, selection) + " where " + idName + " = ?";
	}

	public static String selectQueryCond(String tableName, String[] selections,
			String idName) {
		return selectQuery(tableName, selections) + " where " + idName + " = ?";
	}

	public static String updateQuery(String tableName, String column,
			String idName) {
		return updateQuery(tableName, new String[]{column}, idName);
	}

	public static String updateQuery(String tableName, String[] columns,
			String idName) {
		String setColumnsStr = String.join(",", Arrays.stream(columns)
				.map(n -> n + " = ? ").toArray(String[]::new));
		String condStr = idName + " = ?";
		StringBuilder result = new StringBuilder("update " + tableName + " set "
				+ setColumnsStr + "where " + condStr);
		return result.toString();
	}

}

package com.wangqige.jtds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.util.Log;

public class DBFactory {
	private final DBDefine dbDefine;
	private Connection conn = null;
	private final Statement statement = null;
	private final ResultSet resultSet = null;
	private final ArrayList<String> list;

	public ArrayList<String> getList() {
		return list;
	}

	public DBFactory(DBDefine dbDefine) {
		super();
		this.dbDefine = dbDefine;
		list = new ArrayList<String>();
	}

	private Connection getConntection() {

		try {
			Class.forName(DBDefine.JTDS_NAME);// load the jtds driver
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// get the connection of the specified database, username and
			// password
			conn = DriverManager.getConnection(dbDefine.getServer(),
					dbDefine.getUsername(), dbDefine.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	public void selectResult() throws SQLException {
		list.clear();
		getConntection();

		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		// Statement stmt = conn.createStatement();// create a Statement which
		// can
		// send SQL to DBMS
		Log.w("Connection", "open");
		String sql = "SELECT top(50) * FROM tel_list";
		ResultSet rs = stmt.executeQuery(sql); // execute the SQL
		Log.w("Connection", "opened");
		rs.last();
		int rows = rs.getRow();
		Log.w("db", String.valueOf(rows));
		rs.beforeFirst();
		while (rs.next()) {
			String str = rs.getString(1) + "@__@@" + rs.getString(2)+ "@__@@" + rs.getString(3)+ "@__@@" + rs.getString(4);
			Log.w("db", str);
			list.add(str);
			str = null;

		}
		close();
	}

	private void close() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (conn != null) {
			conn.close();
		}

	}

}

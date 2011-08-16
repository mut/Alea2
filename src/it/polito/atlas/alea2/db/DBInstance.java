package it.polito.atlas.alea2.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface DBInstance {
	public Connection getConnection() throws SQLException;
	public Connection getConnection(String url, String user, String password) throws SQLException;
	public Statement getStatement() throws SQLException;
	public int insert(String sql) throws SQLException;
	public int update(String sql) throws SQLException;
	public void dispose();
}

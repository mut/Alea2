package it.polito.atlas.alea2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlInstance implements DBInstance {

	@Override
	public int update(String sql) throws SQLException {
		return getStatement().executeUpdate(sql);
	}

	@Override
	public int insert(String sql) throws SQLException {
		return update(sql);
	}

	/**
	 * @return the connection
	 * @throws SQLException 
	 */

	@Override
	public Connection getConnection() throws SQLException {
		return pool().get(0);
	}

	@Override
	public Connection getConnection(String url, String user, String password) throws SQLException {
		return pool().get(0);
	}
	
	@Override
	public Statement getStatement() throws SQLException {
		return getConnection().createStatement();
	}

	private List<Connection> pool;
	private List<Connection> pool() throws SQLException {
		if(pool==null){
			return pool("jdbc:mysql://localhost/alea", "alea", "alea");
		}
		return pool;
	}
	private List<Connection> pool(String url, String user, String password) throws SQLException {
		if(pool==null){
			pool=new ArrayList<Connection>();
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pool.add((Connection) DriverManager.getConnection(url, user, password));
		}
		return pool;
	}

	@Override
	public void dispose() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
			// ignore
		}		
	}
}

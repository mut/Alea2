package it.polito.atlas.alea2.db;

import it.polito.atlas.alea2.Property;
import it.polito.atlas.alea2.Slice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

class DBProperty {
	/**
	 * Write a Slice on DB linking to a Track 
	 * @param s
	 * @param id_track
	 * @param db
	 * @throws SQLException
	 */
	protected static boolean write(Property p, long id_slice, DBInstance db) throws SQLException {
		String sql = "insert into property (name, value, id_slice) values (" +
				p.getName() + ", " +
				p.getValue() + ", " +
				id_slice + ")";
		if (db.insert(sql)==1)
			return true;
		else
			return false;
	}

	/**
	 * Read from DB all Slices of a Track 
	 * @param t 
	 * @param id_track
	 * @param db
	 * @return
	 * @throws SQLException
	 */
	protected static Collection<Slice> readAll(Slice s, long id_slice, DBInstance db) throws SQLException {
		String sql = "select key, value from property where id_slice = " + id_slice;
		ResultSet rs = db.getStatement().executeQuery(sql);
		String name, value;
		
		Collection <Slice> slices = new ArrayList<Slice>();
		while (rs.next()) {				
			try {
				name = rs.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
				name="";
			}
			try {
				value = rs.getString(2);
			} catch (SQLException e) {
				e.printStackTrace();
				value="";
				
			}
			Property p = new Property(s, name, value);
			p.setParent(s);
			slices.add(s);
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return slices;
	}
}

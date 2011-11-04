package it.polito.atlas.alea2.db;

import it.polito.atlas.alea2.Property;
import it.polito.atlas.alea2.Slice;
import it.polito.atlas.alea2.Track;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

class DBSlice {
	/**
	 * Write a Slice on DB linking to a Track 
	 * @param s
	 * @param id_track
	 * @param db
	 * @throws SQLException
	 * @throws DBRuntimeException 
	 */
	protected static boolean write(Slice s, long id_track, DBInstance db) throws SQLException, DBRuntimeException {
		boolean allOK = true;
		String name = s.getName();
		String sql = "insert into slice (start, stop, info, id_track) values (" +
				s.getStartTime() + ", " +
				s.getEndTime() + ", '" +
				name + "', " +
				id_track + ")";
		ResultSet rs = null;
		long id_slice = -1;
		Statement stmt = db.getStatement();
		if (stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) != 1)
			throw new DBRuntimeException("Insert slice failed (" + name + ")");
	
		rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			id_slice = rs.getLong(1);
		} else {
			throw new DBRuntimeException("Can't get id_slice of (" + name + ")");
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		
		for (Property p : s.getProperties()) {
			try {
				allOK &= DBProperty.write(p, id_slice, db);
			} catch (SQLException e) {
				e.printStackTrace();
				allOK = false;
			}
		}
		return allOK;
	}

	/**
	 * Read from DB all Slices of a Track 
	 * @param t 
	 * @param id_track
	 * @param db
	 * @return
	 * @throws SQLException
	 */
	protected static Collection<Slice> readAll(Track t, long id_track, DBInstance db) throws SQLException {
		String sql = "select id_slice, start, stop, info from slice where id_track = " + id_track;
		ResultSet rs = db.getStatement().executeQuery(sql);
		long id_slice, start, stop;
		String info;
		
		Collection <Slice> slices = new ArrayList<Slice>();
		while (rs.next()) {				
			try {
				id_slice = rs.getLong(1);
				start = rs.getLong(2);
				stop = rs.getLong(3);
			} catch (SQLException e) {
				e.printStackTrace();
				continue;
			}
			Slice s = new Slice(t, start, stop);
			try {
				info = rs.getString(4);
			} catch (SQLException e) {
				e.printStackTrace();
				info = "";
			}
			s.setName(info);
			try {
				s.addProperties(DBProperty.readAll(s, id_slice, db));
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

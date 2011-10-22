package it.polito.atlas.alea2.db;

import it.polito.atlas.alea2.Slice;
import it.polito.atlas.alea2.Track;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

class DBSlice {
	/**
	 * Write a Slice on DB linking to a Track 
	 * @param s
	 * @param id_track
	 * @param db
	 * @throws SQLException
	 */
	protected static boolean write(Slice s, long id_track, DBInstance db) throws SQLException {
		String sql = "insert into slice (start, stop, info, id_track) values (" +
				s.getStartTime() + ", " +
				s.getEndTime() + ", " +
				s.getInfo() + ", " +
				id_track + ")";
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
	protected static Collection<Slice> readAll(Track t, long id_track, DBInstance db) throws SQLException {
		String sql = "select start, stop, info from slice where id_track = " + id_track;
		ResultSet rs = db.getStatement().executeQuery(sql);
		long start, stop;
		String info;
		
		Collection <Slice> slices = new ArrayList<Slice>();
		while (rs.next()) {				
			try {
				start = rs.getLong(1);
				stop = rs.getLong(2);
			} catch (SQLException e) {
				e.printStackTrace();
				continue;
			}
			Slice s = new Slice(t, start, stop);
			try {
				info = rs.getString(3);
			} catch (SQLException e) {
				e.printStackTrace();
				info = "";
			}
			s.setInfo(info);
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

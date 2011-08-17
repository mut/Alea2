package it.polito.atlas.alea2.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.polito.atlas.alea2.Annotation;
import it.polito.atlas.alea2.Track;

public class DBAnnotation
{
    protected static boolean write(Annotation a, long id_project, DBInstance db)
        throws SQLException, DBRuntimeException
    {
        if(a == null)
            return true;
        
        boolean allOK = true;
		String name = a.getName();
        String sql = "insert into annotation (name, lenght, id_project) values ('" + 
        		name + "', " + 
        		a.getLenght() + ", " +
        		id_project + ")";
        
        ResultSet rs = null;
		long id_annotation = -1;
		Statement stmt = db.getStatement();
		if (stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) != 1)
			throw new DBRuntimeException("Insert annotation failed (" + name + ")");
	
		rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			id_annotation = rs.getLong(1);
		} else {
			throw new DBRuntimeException("Can't get id_track of (" + name + ")");
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		
		for (Track t : a.getTracks()) {
			try {
				allOK &= DBTrack.write(t, id_annotation, db);
			} catch (SQLException e) {
				e.printStackTrace();
				allOK = false;
			}
		} 

        return allOK;
    }
/*
    private static long getIdAnnotation(long id_project, String name, DBInstance db)
        throws SQLException
    {
        String sql = (new StringBuilder("select id_annotation from annotation where name = '")).append(name).append("'").toString();
        ResultSet rs = db.getStatement().executeQuery(sql);
        long id = -1L;
        if(rs.next())
            id = rs.getLong(1);
        rs.close();
        return id;
    }
*/
    protected static Collection<Annotation> readAll(long id_project, DBInstance db) throws SQLException {
        List<Annotation> annotations = new ArrayList<Annotation>();
        String sql = (new StringBuilder("select id_annotation, name, lenght from annotation where id_project = ")).append(id_project).toString();
        ResultSet rs = null;
        rs = db.getStatement().executeQuery(sql);
        while(rs.next()) { 
    		long id_annotation;
    		Annotation a;
			try {
				id_annotation = rs.getLong(1);
	    		a = new Annotation(rs.getString(2));
			} catch (SQLException e) {
				e.printStackTrace();
				continue;
			}
			try {
				a.setLenght(rs.getLong(3));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				a.setLenght(0);
			}
			try {
				a.addTracksVideo(DBTrack.readAll(id_annotation, Track.Types.Video, db));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				a.addTracksLIS(DBTrack.readAll(id_annotation, Track.Types.LIS, db));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				a.addTracksText(DBTrack.readAll(id_annotation, Track.Types.Text, db));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			annotations.add(a);
        }
        try {
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
            // ignore
        }
        return annotations;
    }
}

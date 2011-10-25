package it.polito.atlas.alea2.db;

import it.polito.atlas.alea2.Project;
import it.polito.atlas.alea2.Storage;

import java.sql.SQLException;
import java.util.Dictionary;
import java.util.List;

/**
 * Class to access Project stored in a database
 */
public class DBStorage implements Storage {

	/**
	 * @uml.property  name="db"
	 * @uml.associationEnd  
	 */
	private DBInstance db;
	
	@Override
	public boolean writeProject(Project project, boolean overwrite) {
		if (project == null)
			return false;

		boolean allOK;
		try {
			allOK = DBProject.write(project, db, overwrite);
		} catch (SQLException e) {
			e.printStackTrace();
			allOK = false;
		} catch (DBRuntimeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			allOK = false;
		}
		return allOK;
	}

	/**
	 * Create the DB Storage using the defaults values 
	 * (DB MySql, user alea2, password alea2)
	 */
	public DBStorage () {
		getInstance();
	}

	/**
	 * Create the DB Storage using the defaults values 
	 * (DB MySql, user alea2, password alea2)
	 */
	public DBStorage (DBInstance db) {
		setInstance(db);
	}

	/**
	 * @return The DB instance (Create a new MySql DB instance if none)
	 */
	private DBInstance getInstance() {
		if (db==null)
			db=new MySqlInstance();
		return db;
	}

	/**
	 * Set the Instance of DB
	 * @param db
	 */
	public void setInstance(DBInstance db) {
		if (this.db != null)
			getInstance().dispose();
		this.db=db;
	}

	@Override
	public List <String> getProjectNamesList()
	{
		try {
			return DBProject.listProjectNames(getInstance());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Dictionary <String, String> getProjectNamesTagsList()
	{
		try {
			return DBProject.getProjectNamesTagsList(getInstance());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

/*
	@Override
	public boolean equals(Object o) {
		return
				o != null && 
				o instanceof Project && 
				(
					(((Project) o).getName() == null && name == null)
					||
					(((Project) o).getName().equals(name))
				);
	}
	@Override
	public int hashCode() {
		return name.hashCode();
	}
*/
	public boolean containsProject(String name) {
		return getProjectNamesList().contains(name);
	}
	
	@Override
	public boolean renameProject(String oldProjectName, String newProjectName) {
		if (!containsProject(oldProjectName)) {
			return false;
		}
		try {
			return DBProject.renameProject(oldProjectName, newProjectName, getInstance());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Return a Project by name (null if not exists)
	 */
	public Project readProject(String projectName)
	{
		if (projectName == null) {
			projectName = "";
		}
		try {
			return DBProject.read(projectName, db);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	/// 
	/// Salva il progetto (sovrascrivendo)
	/// 
	/*public bool writeProject(Project p)
	{
		conn.Open();

		String sql;
		try {
			sql = "delete from project where name = ?1";
			MySqlCommand cmd = new MySqlCommand(sql, conn);
			cmd.Parameters.Add(new MySqlParameter("1", p.Name));
			cmd.ExecuteNonQuery();
			
			String tags="";
			foreach (String t in p.Tags) {
				t.Trim();
				if (t != "")
					tags += t + " ";
			}
			
			sql = "insert into project (name, tags) values (?1, ?2)";
			cmd = new MySqlCommand(sql, conn);
			cmd.Parameters.Clear();
			cmd.Parameters.Add(new MySqlParameter("1", p.Name));
			cmd.Parameters.Add(new MySqlParameter("2", tags.TrimEnd()));
			cmd.ExecuteNonQuery();
			
			sql = "select id_project from project where name = ?1";
			cmd = new MySqlCommand(sql, conn);
			cmd.Parameters.Add(new MySqlParameter("1", p.Name));
			MySqlDataReader dr = cmd.ExecuteReader();
			cmd.Dispose();
			long id = -1;
			if (dr.HasRows) {
				dr.Read();
				id = dr.GetInt64(0);
			}

			dr.Close();

			foreach (Annotation a in p.Annotations) {
				writeAnnotation(a, id);
			}
		} catch (Exception ex) {
			Console.WriteLine(ex.Message);
			return false;
		} finally {
			conn.Close();
		}
		return true;
	}
*/

	/**
	 * Close the DB Connection
	 */
	public void dispose() {
		getInstance().dispose();
	}
}

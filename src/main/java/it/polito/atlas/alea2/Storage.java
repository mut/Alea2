package it.polito.atlas.alea2;

import java.util.Dictionary;
import java.util.List;

public interface Storage {
	/**
	 * Verify if the Storage contain the Project
	 * @param name
	 * The name of Project
	 * @return
	 * True if the Project name exist
	 */
	boolean containsProject(String name);
	
	/**
	 * Returns the list of Project names in the Storage
	 * @return
	 * The array list of Project names (null if error)
	 */
	List <String> getProjectNamesList();
	
	/**
	 * Returns the list of Project names and tags in the Storage
	 * @return
	 * The dictionary of Project names and tags
	 */
	Dictionary <String, String> getProjectNamesTagsList();
	
	/**
	 * Read the Project from the Storage
	 * @param name
	 * The name of Project to read
	 * @return
	 * The Project object, null if not exists
	 */
	Project readProject(String name);

	/**
	 * Write the Project to the Storage 
	 * @param project
	 * The Project to write
	 * @param overwrite
	 * Behavior if the project already exists in Storage
	 * @return
	 * True if success
	 */
	boolean writeProject(Project project, boolean overwrite);


	boolean renameProject(String s, String s1);
}

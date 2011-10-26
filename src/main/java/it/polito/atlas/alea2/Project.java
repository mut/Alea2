package it.polito.atlas.alea2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author   DANGELOA
 */
public class Project {
	/**
	 * Constructor
	 * @param name
	 */	
	public Project (String name) {
		setName(name);
		modified=false;
	}

	/**
	 * Name of Project
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return   the Project's Annotations
	 * @uml.property  name="annotations"
	 */
	public List<Annotation> getAnnotations() {
		return annotations;
	}
	/**
	 * @param  name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
		setModified();
	}
	
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
	
	/// 
	/// Get / Set if Project is modified
	/// 
	/**
	 * @uml.property  name="modified"
	 */
	private boolean modified;
	/**
	 * @return   the modified
	 * @uml.property  name="modified"
	 */
	public boolean isModified() {
		return modified;
	}
	/**
	 * @param modified   the modified to set
	 * @uml.property  name="modified"
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public void setModified() {
		modified = true;
	}

	/**
	 * Project's Tags
	 * @uml.property  name="tags"
	 */
	private Set<String> tags = new HashSet<String>();

	/**
	 * Adds a list of tags to Tags set
	 */ 
	public void addTags(String...newTags) {
		for(String tag : newTags)
			tags.add(tag);
		setModified();
	}

	/**
	 * @return   the tags
	 * @uml.property  name="tags"
	 */
	public Set<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(HashSet<String> tags) {
		this.tags = tags;
		setModified();
	}
	
	/**
	 * @param newTags the tags to set
	 */
	public void setTags(String...newTags) {
		tags.clear();
		addTags(newTags);
		setModified();
	}
		
	/**
	 * @uml.property  name="annotations"
	 */
	private List <Annotation> annotations = new ArrayList <Annotation> ();

	/// 
	/// Aggiunge un'annotazione al progetto e la rende come corrente
	/// 
	public boolean addAnnotation(Annotation newAnnotation) {
		if (annotations.contains(newAnnotation)) {
			return false;
		}
		annotations.add(newAnnotation);
		currentAnnotation = newAnnotation;
		setModified();
		return true;
	}
	
	/// 
	/// Annotazione su cui si sta lavorando
	/// 
	/**
	 * @uml.property  name="currentAnnotation"
	 * @uml.associationEnd  
	 */
	private Annotation currentAnnotation = null;
	
	/**
	 * @return   the currentAnnotation
	 * @uml.property  name="currentAnnotation"
	 */
	public Annotation getCurrentAnnotation() {
		return currentAnnotation;
	}
	/**
	 * @param currentAnnotation   the currentAnnotation to set
	 * @uml.property  name="currentAnnotation"
	 */
	public void setCurrentAnnotation(Annotation currentAnnotation) {
		/// 
		/// Verifica che l'annotazione  da rendere
		/// corrente sia effettivamente un'annotazione
		/// del progetto, altrimenti rimane invariata
		/// 
		if (annotations.contains(currentAnnotation))
			this.currentAnnotation = currentAnnotation;
	}

	///
	/// Collegamento ad un oggetto che rappresenta il progetto
	/// 
	public Object link;

	public void dispose() {
		for (Annotation a : getAnnotations())
			a.dispose();
		getAnnotations().clear();
		setModified();
	}

	public void remove(Annotation annotation) {
		getAnnotations().remove(annotation);
		setModified();
	}

}

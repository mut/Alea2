package it.polito.atlas.alea2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author   DANGELOA
 */
public class Slice {

	/**
	 * @uml.property  name="startTime"
	 */
	private long startTime;
	/**
	 * @uml.property  name="endTime"
	 */
	private long endTime;

	/**
	 * @uml.property  name="parent"
	 * @uml.associationEnd  
	 */
	private Track parent;

	public Slice (Track parent, long startTime, long endTime)
	{
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setParent(parent);
	}
	
	/**
	 * @return   the Slice Start Time in milliseconds
	 * @uml.property  name="startTime"
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime   the Slice Start Time to set in milliseconds
	 * @uml.property  name="startTime"
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
		setModified();
	}

	/**
	 * @return   the Slice End Time in milliseconds
	 * @uml.property  name="endTime"
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime   the Slice End Time to set in milliseconds
	 * @uml.property  name="endTime"
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
		setModified();
	}
	
	/**
	 * @uml.property  name="info"
	 */
	private Object info;
	/**
	 * @return   the info
	 * @uml.property  name="info"
	 */
	public Object getInfo() {
		return info;
	}

	/**
	 * @param info   the info to set
	 * @uml.property  name="info"
	 */
	public void setInfo(Object info) {
		this.info = info;
		setModified();
	}

	/**
	 * @uml.property  name="properties"
	 */
	private List <Property> properties = new ArrayList <Property> ();

	/**
	 * @return   the Slice Properties
	 * @uml.property  name="properties"
	 */
	public List<Property> getProperties() {
		return properties;
	}

	/// 
	/// Add a Property to Slice
	/// 
	public boolean addProperty(Property newProperty) {
		if (properties.contains(newProperty)) {
			return false;
		}
		properties.add(newProperty);
		setModified();
		return true;
	}
	
    /**
	 * Link to a Object representing the Slice
	 */
	public Object link;

	/**
	 * @param parent  the parent to set
	 * @uml.property  name="parent"
	 */
	public void setParent(Track parent) {
		this.parent = parent;
	}

	/**
	 * @return  the parent
	 * @uml.property  name="parent"
	 */
	private Track getParent() {
		return parent;
	}

	/**
	 * set the Project as modified
	 */
	public void setModified() {
		if (parent != null)
			parent.setModified();
	}

	public void dispose() {
		for (Property p : getProperties())
			p.dispose();
		getProperties().clear();
	}

	/**
	 * @param property The Property to remove
	 */
	public void remove(Property property) {
		getProperties().remove(property);
		setModified();
	}

	/**
	 * Remove this element from parent and dispose
	 */
	public void remove() {
		if (getParent()!=null)
			getParent().remove(this);
		dispose();
	}
}

package it.polito.atlas.alea2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  DANGELOA
 */
public class Slice {

	/**
	 */
	private long startTime;
	/**
	 */
	private long endTime;

	private Track parent;

	public Slice (Track parent, long startTime, long endTime)
	{
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	/**
	 * @return  the Slice Start Time in milliseconds
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime  the Slice Start Time to set in milliseconds
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return  the Slice End Time in milliseconds
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime  the Slice End Time to set in milliseconds
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	/**
	 */
	private Object info;
	/**
	 * @return  the info
	 */
	public Object getInfo() {
		return info;
	}

	/**
	 * @param info  the info to set
	 */
	public void setInfo(Object info) {
		this.info = info;
	}

	private List <Property> properties = new ArrayList <Property> ();

	/**
	 * @return  the Slice Properties
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
		return true;
	}
	
    /**
	 * Link to a Object representing the Slice
	 */
	public Object link;

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Track parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	private Track getParent() {
		return parent;
	}

	public void dispose() {
		for (Property p : getProperties())
			p.dispose();
		getProperties().clear();
		if (getParent()!=null)
			getParent().remove(this);
	}

	/**
	 * @param property The Property to remove
	 */
	public void remove(Property property) {
		getProperties().remove(property);
	}
}

package it.polito.atlas.alea2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  DANGELOA
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

	public Slice (long startTime, long endTime)
	{
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	/**
	 * @return  the Slice Start Time in milliseconds
	 * @uml.property  name="startTime"
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime  the Slice Start Time to set in milliseconds
	 * @uml.property  name="startTime"
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return  the Slice End Time in milliseconds
	 * @uml.property  name="endTime"
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime  the Slice End Time to set in milliseconds
	 * @uml.property  name="endTime"
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @uml.property  name="info"
	 */
	private Object info;
	/**
	 * @return  the info
	 * @uml.property  name="info"
	 */
	public Object getInfo() {
		return info;
	}

	/**
	 * @param info  the info to set
	 * @uml.property  name="info"
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
}

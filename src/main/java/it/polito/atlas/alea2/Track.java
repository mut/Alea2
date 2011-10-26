package it.polito.atlas.alea2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author   DANGELOA
 */
public abstract class Track {

	/**
	 * @uml.property  name="name"
	 */
	private String name;
	public Track (Annotation parent, String name) {
		this.setName(name);
		this.setParent(parent);
	}

	/**
	 * @return   the name
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name   the name to set
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
		setModified();
	}
	/**
	 * @author     DANGELOA
	 */
	public enum Types {
		/**
		 * @uml.property  name="video"
		 * @uml.associationEnd  
		 */
		Video, /**
		 * @uml.property  name="lIS"
		 * @uml.associationEnd  
		 */
		LIS, /**
		 * @uml.property  name="text"
		 * @uml.associationEnd  
		 */
		Text
	}		
	/**
	 * @uml.property  name="type"
	 * @uml.associationEnd  
	 */
	protected Types type;
	
	/**
	 * @return   the type of Track
	 * @uml.property  name="type"
	 */
	public Types getType() {
		return type;
	}
	
	public String getTypeString() {
		return getTypeString(this.type);
	}
	
	public static String getTypeString(Types type) {
		String typeStr = null;
		switch (type) {
			case Video:
				typeStr = "video";
				break;
			case LIS:
				typeStr = "lis";
				break;
			case Text:
				typeStr = "text";
				break;
		}
		return typeStr;
	}
	
	/**
	 * @uml.property  name="slices"
	 */
	private Collection <Slice> slices = new ArrayList<Slice>();
	
	public long getEndTime() {
		long tmp, end = -1;
		for (Slice s : slices) {
			tmp = s.getEndTime();
			if ( tmp > end)
				end = tmp;
		}
		return end;
	}
	
	/**
	 * @return
	 * @uml.property  name="slices"
	 */
	public Collection <Slice> getSlices() {
		return slices;
	}

	public boolean addSlices(Collection<Slice> slices) {
		this.slices.addAll(slices);
		setModified();
		return true;
	}

	public boolean addSlice(Slice slice) {
		this.slices.add(slice);
		setModified();
		return true;
	}

	/**
	 * Collegamento ad un oggetto che rappresenta la traccia
	 */
	public Object link;
	
	/**
	 * @uml.property  name="parent"
	 * @uml.associationEnd  
	 */
	private Annotation parent;
	
	/**
	 * @param parent  the parent to set
	 * @uml.property  name="parent"
	 */
	public void setParent(Annotation parent) {
		this.parent = parent;
	}

	/**
	 * @return  the parent
	 * @uml.property  name="parent"
	 */
	public Annotation getParent() {
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
		for (Slice s : getSlices()) {
			s.dispose();
		}
		getSlices().clear();
		if (getParent()!=null)
			getParent().remove(this);
		if ((this.type == Track.Types.Video) && (((TrackVideo)this).player != null))
			((TrackVideo)this).player.dispose();		
	}

	/**
	 * @param slice The Slice to remove
	 */
	public void remove(Slice slice) {
		getSlices().remove(slice);
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

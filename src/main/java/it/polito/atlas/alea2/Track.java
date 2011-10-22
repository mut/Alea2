package it.polito.atlas.alea2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author  DANGELOA
 */
public abstract class Track {

	/**
	 */
	private String name;
	public Track (Annotation parent, String name) {
		this.setParent(parent);
		this.setName(name);
	}

	/**
	 * @return  the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name  the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @author   DANGELOA
	 */
	public enum Types {
		/**
		 */
		Video, /**
		 */
		LIS, /**
		 */
		Text
	}		
	/**
	 */
	protected Types type;
	
	/**
	 * @return  the type of Track
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
	 */
	public Collection <Slice> getSlices() {
		return slices;
	}

	public boolean addSlices(Collection<Slice> slices) {
		this.slices.addAll(slices);
		return true;
	}

	public boolean addSlice(Slice slice) {
		this.slices.add(slice);
		return true;
	}

	/**
	 * Collegamento ad un oggetto che rappresenta la traccia
	 */
	public Object link;
	
	private Annotation parent;
	
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Annotation parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	private Annotation getParent() {
		return parent;
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

	public void remove(Slice slice) {
		getSlices().remove(slice);
	}
}

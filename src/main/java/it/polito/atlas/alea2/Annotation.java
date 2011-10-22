package it.polito.atlas.alea2;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import static java.lang.String.format;

/**
 * @author  DANGELOA
 */
public class Annotation {
	public Annotation (Project parent, String name) {
		setParent(parent);
		setName(name);
		setLenght(0);
	}

	/**
	 * Name of annotation
	 */
	private String name;

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

	/// 
	/// Length of annotation
	/// 
	private long length;
	/**
	 * @return the length
	 */
	public long getLenght() {
		if (length < 0)
			length = 0;
		return length;
	}
	/**
	 * @param lenght the length to set
	 */
	public void setLenght(long length) {
		this.length = length;
	}


	///
	/// LIS Tracks
	///
	/**
	 */
	private List<TrackLIS> tracksLIS = new ArrayList<TrackLIS>();

	/**
	 * @return  the LIS tracks
	 */
	public List<TrackLIS> getTracksLIS() {
		return tracksLIS;
	}

	///
	/// Video Tracks
	///
	/**
	 */
	private List <TrackVideo> tracksVideo = new ArrayList <TrackVideo> ();

	/**
	 * @return  the Video tracks
	 */
	public List<TrackVideo> getTracksVideo() {
		return tracksVideo;
	}

	///
	/// Text Tracks
	///
	/**
	 */
	private List <TrackText> tracksText = new ArrayList <TrackText> ();
	
	/**
	 * @return  the Text tracks
	 */
	public List<TrackText> getTracksText() {
		return tracksText;
	}

	public Collection<Track> getTracks() {
		Collection<Track> c = new ArrayList<Track>();
		c.addAll(getTracksLIS());
		c.addAll(getTracksText());
		c.addAll(getTracksVideo());
		return c;
	}

	/**
	 * @return The length in milliseconds
	 */
	public long calcLenght () {
		long end = 0;
		for (Track t : getTracks()) {
			long tmp=t.getEndTime();
			if (tmp > end)
				end = tmp;
		}
		length = end;
		return end;
	}
	
    public void addTracksVideo(List<Track> list)
    {
        for(Track t : list)
        	tracksVideo.add((TrackVideo)t);

    }

    public void addTracksLIS(List<Track> list)
    {
        for(Track t : list)
        	tracksLIS.add((TrackLIS)t);
    }

    public void addTracksText(List<Track> list)
    {
        for(Track t : list)
        	tracksText.add((TrackText)t);
    }

    public void addTrackVideo(Track t)
    {
    	if (t instanceof TrackVideo)
    		tracksVideo.add((TrackVideo)t);

    }

    public void addTrackLIS(Track t)
    {
    	if (t instanceof TrackLIS)
    		tracksLIS.add((TrackLIS)t);
    }

    public void addTrackText(Track t)
    {
    	if (t instanceof TrackText)
    		tracksText.add((TrackText)t);
    }

    /**
	 * Link to a Object representing the Annotation
	 */
	public Object link;

	private Project parent;
	
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Project parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	private Project getParent() {
		return parent;
	}
	public void dispose() {
		for (Track t : getTracks()) {
			t.dispose();
		}
		tracksLIS.clear();
		tracksText.clear();
		tracksVideo.clear();
		if (getParent()!=null)
			getParent().remove(this);
	}
	public void remove(Track track) {
    	if (track instanceof TrackLIS)
    		tracksLIS.remove(track);
    	else if (track instanceof TrackText)
    		tracksText.remove(track);
    	else if (track instanceof TrackVideo)
    		tracksVideo.remove(track);
	}
}


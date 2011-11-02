package it.polito.atlas.alea2;

import java.util.ArrayList;
import java.util.List;

public class LIS_Tag {
	/**
	 * Tag name
	 */
	private String name;
	/**
	 * Tag description
	 */
	private String description;
	/**
	 * Sub-tree of tags
	 */
	private List <LIS_Tag> tags = new ArrayList<LIS_Tag>();
	/**
	 * Leaf tags
	 */
	private List <String> values = new ArrayList<String>();
	/**
	 * Leaf tags description
	 */
	private List <String> valuesDescription = new ArrayList<String>();

	/**
	 * Generate the LIS Tracks identifiers Tree
	 * @return LIS Tracks tree
	 */
	static public List <LIS_Tag> getLISTracks() {
		List <LIS_Tag> tracks=new ArrayList<LIS_Tag>();
		LIS_Tag track;
		LIS_Tag tag1, tag2;
		
		track = new LIS_Tag("Hands");
		tag1 = new LIS_Tag("Involved Hands");
		tag1.setValues(new String[] {"Both", "Left", "Right"});
		tag1 = new LIS_Tag("Sign Absolute Spatial Location");
		tag1.setValues(new String[] {"neutral space", "L", "R", "U", "D", "F", "B"});
		tag1 = new LIS_Tag("Sign Relative Spatial Location");
		tag2 = new LIS_Tag("Starting Location");
		tag2.setValues(new String[] {"To Be Defined", "mod1", "mod2", "mod3"});
		tag1.addTags(tag1);
		tag2 = new LIS_Tag("Ending Location");
		tag2.setValues(new String[] {"To Be Defined", "mod1", "mod2", "mod3"});
		tag1.addTags(tag1);
		tag2 = new LIS_Tag("Trajectory");
		tag2.setValues(new String[] {"To Be Defined", "mod1", "mod2", "mod3"});
		tag1.addTags(tag1);
		tag2 = new LIS_Tag("Context Table");
		tag2.setValues(new String[] {"To Be Defined", "mod1", "mod2", "mod3"});
		tag1.addTags(tag1);
		tag1 = new LIS_Tag("Sign Modifier");
		tag1.setValues(new String[] {"To Be Defined", "mod1", "mod2", "mod3"});
		tag1 = new LIS_Tag("Not signing Hand");
		tag1.setValues(new String[] {"null", "Res"});
		tracks.add(track);
		
		track = new LIS_Tag("Orientation");
		track.setValues(new String[] {"turn_L", "turn_F"});
		tag1 = new LIS_Tag("Trajectory");
		tag1.setValues(new String[] {"To Be Defined", "mod1", "mod2", "mod3"});
		tracks.add(track);
		
		track = new LIS_Tag("Torso");
		track.setValues(new String[] {"turn_L", "turn_F"});
		tag1 = new LIS_Tag("Trajectory");
		tag1.setValues(new String[] {"To Be Defined", "mod1", "mod2", "mod3"});
		tracks.add(track);
		
		return tracks;		
	}

	private void setValues(String[] strings) {
		for (String s : strings)
			values.add(s);		
	}

	public LIS_Tag(String name) {
		setName(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tags
	 */
	public List<LIS_Tag> getTags() {
		return tags;
	}

	/**
	 * @param tag the tag to add
	 */
	public void addTags(LIS_Tag tag) {
		this.tags.add(tag);
	}

	/**
	 * @return the values
	 */
	public List <String> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List <String> values) {
		this.values = values;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		if ((description==null) || (description==""))
			return name;
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the valuesDescription
	 */
	public List <String> getValuesDescription() {
		return valuesDescription;
	}

	/**
	 * @param valuesDescription the valuesDescription to set
	 */
	public void setValuesDescription(List <String> valuesDescription) {
		this.valuesDescription = valuesDescription;
	}
}

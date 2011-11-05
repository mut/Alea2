package it.polito.atlas.alea2.tule;

import java.util.HashSet;
import java.util.Hashtable;

public class Lemma {
	/**
	 * The set of lemma valid attributes
	 */
	private static HashSet<String> attrValidNames;
	static {
		attrValidNames = new HashSet<String>();
		attrValidNames.add("PARENTPOSITION"); // this is not a TULE attribute
		attrValidNames.add("FORM");
		attrValidNames.add("POSITION");
		attrValidNames.add("LEMMA");
		attrValidNames.add("CAT");
		attrValidNames.add("TYPE");
		attrValidNames.add("MOOD");
		attrValidNames.add("TENSE");
		attrValidNames.add("TRANS");
		attrValidNames.add("PERSON");
		attrValidNames.add("NUMBER");
		attrValidNames.add("GENDER");
		attrValidNames.add("LINK");
	}
	
	/**
	 * The pairs Attribute-Value
	 */
	private Hashtable<String, String> attributes = new Hashtable<String, String>();
	
	/**
	 * The link to the parent Lemma from which this Lemma depends
	 */
	private Lemma parent = null;
	
	/**
	 * Get the attributes of this Lemma
	 * @return The pairs Attribute-Value 
	 */
	public Hashtable<String, String>  getAttributes() {
		return attributes;
	}

	/**
	 * Set the text that generates this Lemma
	 * corresponding to the TULE FORM attribute
	 * @param text
	 */
	public void setText(String text) {
		if (text!=null)
			attributes.put("FORM", text);
	}
	
	/**
	 * Get the text from that generates this Lemma
	 * corresponding to the TULE FORM attribute
	 */
	public String getText() {
		return attributes.get("FORM");
	}
	/**
	 * Returns the LEMMA value
	 * @return the lemma
	 */
	public String getLemma() {
		return attributes.get("LEMMA");
	}
	/**
	 * Set the LEMMA value
	 * @param lemma the lemma to set
	 */
	public void setLemma(String lemma) {
		if (lemma!=null)
			attributes.put("LEMMA", lemma);
	}
	
	/**
	 * Check if the attribute is valid 
	 * @param attribute
	 * @return true if the parameter is a valid attribute
	 */
	public static boolean isAttrValidName(String attribute) {
		if (attrValidNames.contains(attribute))
			return true;
		return false;
	}
	
	/**
	 * Set/Add a attribute and the corresponding value
	 * @param attrName
	 * @param word
	 * @return true if the Attribute is valid
	 */
	public boolean setAttr(String attrName, String word) {
		if ((attrName != null) && (word != null) && (Lemma.isAttrValidName(attrName))) {
			attributes.put(attrName, word);
			return true;
		}
		return false;
	}
	
	/**
	 * @return The parent Lemma from which this Lemma depends
	 */
	public Lemma getParent() {
		return parent;
	}

	/**
	 * Set the link to the parent Lemma from which this Lemma depends
	 * (Also add the position of the parent Lemma FORM in written text)
	 * @param parent the parent to set
	 */
	public void setParent(Lemma parent) {
		this.parent = parent;
		if (parent != null)
			setAttr("PARENTPOSITION", parent.getPosition());
	}

	/**
	 * Get the position of the Lemma FORM in written text
	 * @return the string representing the position
	 */
	private String getPosition() {
		return attributes.get("POSITION");
	}
}

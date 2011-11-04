package it.polito.atlas.alea2.tule;

import java.util.HashSet;
import java.util.Hashtable;

public class Lemma {
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
	
	private Hashtable<String, String> attributes = new Hashtable<String, String>();
	private Lemma parent = null;
	
	public Hashtable<String, String>  getAttributes() {
		return attributes;
	}

	public void setText(String text) {
		if (text!=null)
			attributes.put("FORM", text);
	}
	
	public String getText() {
		return attributes.get("FORM");
	}
	/**
	 * @return the lemma
	 */
	public String getLemma() {
		return attributes.get("LEMMA");
	}
	/**
	 * @param lemma the lemma to set
	 */
	public void setLemma(String lemma) {
		if (lemma!=null)
			attributes.put("LEMMA", lemma);
	}
	
	public static boolean isAttrValidName(String attribute) {
		if (attrValidNames.contains(attribute))
			return true;
		return false;
	}
	public boolean setAttr(String attrName, String word) {
		if ((attrName != null) && (word != null) && (Lemma.isAttrValidName(attrName))) {
			attributes.put(attrName, word);
			return true;
		}
		return false;
	}
	/**
	 * @return the parent
	 */
	public Lemma getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Lemma parent) {
		this.parent = parent;
		if (parent != null)
			setAttr("PARENTPOSITION", parent.getPosition());
	}

	private String getPosition() {
		return attributes.get("POSITION");
	}
}

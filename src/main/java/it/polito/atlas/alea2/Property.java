package it.polito.atlas.alea2;

/**
 * @author  DANGELOA
 */
public class Property extends Object {
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="value"
	 */
	private String value;
	/**
	 * @uml.property  name="parent"
	 * @uml.associationEnd  
	 */
	private Slice parent;

	public Property(Slice parent, String name, String value) {
		this.setParent(parent);
		this.setName(name);
		this.setValue(value);		
	}
	
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 * @uml.property  name="value"
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value
	 * @uml.property  name="value"
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return  the parent
	 * @uml.property  name="parent"
	 */
	public Slice getParent() {
		return parent;
	}

	/**
	 * @param parent  the parent to set
	 * @uml.property  name="parent"
	 */
	public void setParent(Slice parent) {
		this.parent = parent;
	}

	public void dispose() {
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

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
		this.setName(name);
		this.setValue(value);		
		this.setParent(parent);
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
		setModified();
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
		setModified();
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

	/**
	 * set the Project as modified
	 */
	public void setModified() {
		if (parent != null)
			parent.setModified();
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

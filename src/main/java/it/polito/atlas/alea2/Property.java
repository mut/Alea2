package it.polito.atlas.alea2;

public class Property extends Object {
	private String name;
	private String value;
	private Slice parent;

	public Property(Slice parent, String name, String value) {
		this.setParent(parent);
		this.setName(name);
		this.setValue(value);		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the parent
	 */
	public Slice getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Slice parent) {
		this.parent = parent;
	}

	public void dispose() {
		if (getParent()!=null)
			getParent().remove(this);
	}
}

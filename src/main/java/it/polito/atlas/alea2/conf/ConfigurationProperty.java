package it.polito.atlas.alea2.conf;

/**
 * @author     DANGELOA
 */
public enum ConfigurationProperty {
	/**
	 * @uml.property  name="dB_URL"
	 * @uml.associationEnd  
	 */
	DB_URL("db.url", "jdbc:mysql://localhost/alea"), /**
	 * @uml.property  name="dB_USERNAME"
	 * @uml.associationEnd  
	 */
	DB_USERNAME("db.username", "alea"), /**
	 * @uml.property  name="dB_PASSWORD"
	 * @uml.associationEnd  
	 */
	DB_PASSWORD("db.password", "alea");

	/**
	 * @uml.property  name="property"
	 */
	private final String property;
	/**
	 * @uml.property  name="def"
	 */
	private final String def;

	ConfigurationProperty(String property, String def) {
		this.property = property;
		this.def = def;
	}

	/**
	 * @return
	 * @uml.property  name="property"
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @return
	 * @uml.property  name="def"
	 */
	public String getDef() {
		return def;
	}

}

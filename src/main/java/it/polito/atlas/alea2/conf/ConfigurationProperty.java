package it.polito.atlas.alea2.conf;

/**
 * @author   DANGELOA
 */
public enum ConfigurationProperty {
	/**
	 */
	DB_URL("db.url", "jdbc:mysql://localhost/alea"), /**
	 */
	DB_USERNAME("db.username", "alea"), /**
	 */
	DB_PASSWORD("db.password", "alea");

	/**
	 */
	private final String property;
	/**
	 */
	private final String def;

	ConfigurationProperty(String property, String def) {
		this.property = property;
		this.def = def;
	}

	/**
	 * @return
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @return
	 */
	public String getDef() {
		return def;
	}

}

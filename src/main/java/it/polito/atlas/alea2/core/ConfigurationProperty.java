package it.polito.atlas.alea2.core;

public enum ConfigurationProperty {
	DB_URL("db.url", "jdbc:mysql://localhost/alea"), DB_USERNAME("db.username", "alea"), DB_PASSWORD("db.password", "alea");

	private final String property;
	private final String def;

	ConfigurationProperty(String property, String def) {
		this.property = property;
		this.def = def;
	}

	public String getProperty() {
		return property;
	}

	public String getDef() {
		return def;
	}

}

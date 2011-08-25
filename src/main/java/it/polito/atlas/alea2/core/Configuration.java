package it.polito.atlas.alea2.core;

import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private static final Configuration instance;

	private Properties properties;

	static {
		instance = new Configuration();
		try {
			instance.load();
		} catch (IOException e) {
			System.out.println("can't load configuration");
			e.printStackTrace();
		}
	}

	public static String conf(ConfigurationProperty property) {
		return instance.get(property);
	}

	private String get(ConfigurationProperty property) {
		return properties.getProperty(property.getProperty(), property.getDef());
	}

	private void load() throws IOException {
		properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("alea.properties"));
	}
}

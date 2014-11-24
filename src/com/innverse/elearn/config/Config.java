package com.innverse.elearn.config;

import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class Config {

	public static Properties properties;

	// Cheating spring to set a static property.
	public static Properties setInstance(Properties propertiesArgs) {
		properties = propertiesArgs;
		return properties;
	}

	public static String getMsg(String key) {
		return properties.getProperty(key);
	}

}

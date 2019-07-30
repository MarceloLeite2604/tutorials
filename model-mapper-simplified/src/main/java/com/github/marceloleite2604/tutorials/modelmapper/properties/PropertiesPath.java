package com.github.marceloleite2604.tutorials.modelmapper.properties;

public final class PropertiesPath {

	public static final String MODEL_MAPPER = "model-mapper";
	
	public static final String DATABASE = MODEL_MAPPER + "." + "database";

	public static final String ENCRYPTION = MODEL_MAPPER + "." + "encryption";
	
	private PropertiesPath() {
		// Private constructor to avoid object instantiation.
	}
}

package com.github.marceloleite2604.tutorials.modelmapper.properties;

import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.DATABASE)
@Validated
public class DatabaseProperties {

	/**
	 * Driver class name for database connection.
	 */
	@NotBlank
	private String driverClassName;

	/**
	 * Database connection URL.
	 */
	@NotBlank
	private String url;

	/**
	 * Username for database connection.
	 */
	@NotBlank
	private String username;

	/**
	 * Encrypted password for database connection.
	 */
	@NotBlank
	private String password;
	
	/**
	 * Quantity of connections to establish with database. 
	 */
	@Min(1L)
	private Integer connections = 1;
	
	/**
	 * Other connection properties.
	 */
	private Map<String, String> otherConnectionProperties;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getConnections() {
		return connections;
	}

	public void setConnections(Integer connections) {
		this.connections = connections;
	}

	public Map<String, String> getOtherConnectionProperties() {
		return otherConnectionProperties;
	}

	public void setOtherConnectionProperties(Map<String, String> otherConnectionProperties) {
		this.otherConnectionProperties = otherConnectionProperties;
	}
}

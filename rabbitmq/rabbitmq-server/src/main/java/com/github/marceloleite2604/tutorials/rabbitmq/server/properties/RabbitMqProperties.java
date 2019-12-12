package com.github.marceloleite2604.tutorials.rabbitmq.server.properties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.RABBITMQ)
@Validated
public class RabbitMqProperties {

	/**
	 * RabbitMQ server host.
	 */
	@NotBlank
	private String host;
	
	/**
	 * RabbitMQ server port.
	 */
	@Min(1)
	@Max(65535)
	private int port;
	
	/**
	 * Username to connect on RabbitMQ server.
	 */
	@NotBlank
	private String username;
	
	/**
	 * Password to connect on RabbitMQ server.
	 */
	@NotBlank
	private String password;
	
	/**
	 * Queue to monitor requests.
	 */
	@NotBlank
	private String requestQueue;
	
	/**
	 * Topic to send responses.
	 */
	@NotBlank
	private String responseTopic;
	
	/**
	 * Routing key for response message.
	 */
	private String responseRoutingKey;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
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

	public String getRequestQueue() {
		return requestQueue;
	}

	public void setRequestQueue(String receiverQueueName) {
		this.requestQueue = receiverQueueName;
	}

	public String getResponseTopic() {
		return responseTopic;
	}

	public void setResponseTopic(String responseQueueName) {
		this.responseTopic = responseQueueName;
	}

	public String getResponseRoutingKey() {
		return responseRoutingKey;
	}

	public void setResponseRoutingKey(String responseRoutingKey) {
		this.responseRoutingKey = responseRoutingKey;
	}
}

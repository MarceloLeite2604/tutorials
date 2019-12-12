package com.github.marceloleite2604.tutorials.rabbitmq.server.properties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.RABBITMQ_SERVER)
@Validated
public class RabbitMqServerProperties {

	@Min(0)
	@Max(1)
	private double failureRatio;

	public double getFailureRatio() {
		return failureRatio;
	}

	public void setFailureRatio(double failureRatio) {
		this.failureRatio = failureRatio;
	}
}

package org.marceloleite.tutorials.spring.cloud.archaius;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

@RestController
public class ConfigPropertiesController {

	private DynamicStringProperty propertyOneWithDynamic = DynamicPropertyFactory.getInstance()
			.getStringProperty("program.message", "Message not found.");

	@GetMapping("/property-from-dynamic-management")
	public String getPropertyValue() {
		return propertyOneWithDynamic.getName() + ": " + propertyOneWithDynamic.get();
	}
}
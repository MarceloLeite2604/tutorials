package com.github.marceloleite2604.tutorials.spring.cloud.stream.client.inversor;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("inversor")
public class InversorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InversorController.class);

	@Inject
	private InversorService inversorService;

	@PostMapping
	public String inverter(@RequestBody String texto) throws Exception {

		LOGGER.info("Requisitado a inversão do texto: \"{}\".", texto);
		return inversorService.inverter(texto);
	}
}

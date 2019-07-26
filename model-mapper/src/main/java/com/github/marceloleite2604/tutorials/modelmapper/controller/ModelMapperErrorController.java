package com.github.marceloleite2604.tutorials.modelmapper.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.marceloleite2604.tutorials.modelmapper.model.ErrorInformation;
import com.github.marceloleite2604.tutorials.modelmapper.util.ControllerUtil;

@ControllerAdvice
@Controller
public class ModelMapperErrorController implements ErrorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelMapperErrorController.class);

	private static final String ERROR_ATTRIBUTE_NAME = "error";

	private static final Integer DEFAULT_ERROR_PAGE_CODE = 500;

	private static final List<Integer> ERROR_PAGES_CODES = new ArrayList<>();

	static {
		ERROR_PAGES_CODES.add(HttpStatus.UNAUTHORIZED.value());
		ERROR_PAGES_CODES.add(HttpStatus.NOT_FOUND.value());
	}

	@Inject
	private ControllerUtil controllerUtil;

	@ExceptionHandler(value = Exception.class)
	@GetMapping(path = "/error")
	public String getError(HttpServletRequest httpServletRequest, HttpServletResponse response,
			Model model, Exception exception) {

		int statusCode = controllerUtil.retrieveStatusCode(httpServletRequest);

		if (statusCode == DEFAULT_ERROR_PAGE_CODE && !Objects.isNull(exception) && !StringUtils.isBlank(exception.getMessage())) {
			LOGGER.error("Handling exception", exception);
		}

		int errorPageCode = retrieveErrorPageCode(statusCode);

		addErrorAttributeToModel(httpServletRequest, model, exception);

		return Paths.elaborateErrorPagePath(errorPageCode);
	}

	private void addErrorAttributeToModel(HttpServletRequest httpServletRequest, Model model,
			Exception exception) {
		ErrorInformation errorInformation = controllerUtil
				.createErrorInformation(httpServletRequest, exception);

		model.addAttribute(ERROR_ATTRIBUTE_NAME, errorInformation);
	}

	private int retrieveErrorPageCode(int statusCode) {

		int result = DEFAULT_ERROR_PAGE_CODE;

		if (ERROR_PAGES_CODES.contains(statusCode)) {
			result = statusCode;
		}

		return result;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	static class Paths {
		private static final String ERROR_PAGE_PATH_TEMPLATE = "error/error-%d";

		static String elaborateErrorPagePath(int errorPageCode) {
			return String.format(ERROR_PAGE_PATH_TEMPLATE, errorPageCode);
		}

		private Paths() {
			// Private constructor to avoid object instantiation.
		}
	}
}

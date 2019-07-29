package com.github.marceloleite2604.tutorials.modelmapper.util;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marceloleite2604.tutorials.modelmapper.model.DetailedMessage;
import com.github.marceloleite2604.tutorials.modelmapper.model.ErrorInformation;
import com.github.marceloleite2604.tutorials.modelmapper.model.ThymeleafModelAttributeNames;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.Message;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.MessageLoader;

@Component
public class ServiceUtil {

	private static final String BINDING_RESULT_ATTRIBUTE_PREFIX = "org.springframework.validation.BindingResult.";

	@Inject
	private ExceptionUtil exceptionUtil;

	@Inject
	private LocalDateTimeUtil localDateTimeUtil;

	@Inject
	private MessageLoader messageLoader;

	public ErrorInformation createErrorInformation(HttpServletRequest httpServletRequest,
			Exception exception) {
		return ErrorInformation.builder()
				.instant(localDateTimeUtil.formatToHumanReadableDateTime(LocalDateTime.now()))
				.path(URI.create(httpServletRequest.getRequestURI())
						.getPath())
				.status(retrieveStatusCode(httpServletRequest))
				.message(exception.getMessage())
				.stackTrace(exceptionUtil.retrieveStackTrace(exception))
				.build();
	}

	public int retrieveStatusCode(HttpServletRequest httpServletRequest) {
		Optional<Object> optionalStatusCodeObjet = Optional
				.ofNullable(httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));

		if (optionalStatusCodeObjet.isPresent()) {
			Object statusCodeObject = optionalStatusCodeObjet.get();
			if (statusCodeObject instanceof Integer) {
				return (int) statusCodeObject;
			}
		}

		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public String redirectTo(String path) {
		return "redirect:" + path;
	}

	public void addErrorMessage(Model model, String message) {
		model.addAttribute(ThymeleafModelAttributeNames.ERROR_MESSAGE, message);
	}

	public void addErrorMessage(Model model, Message message, Object... messageParameters) {
		model.addAttribute(ThymeleafModelAttributeNames.ERROR_MESSAGE,
				messageLoader.getMessage(message, messageParameters));
	}

	public void addDetailedErrorMessage(Model model, DetailedMessage detailedMessage) {
		model.addAttribute(ThymeleafModelAttributeNames.DETAILED_ERROR_MESSAGE, detailedMessage);
	}

	public void addInformationMessage(Model model, String message) {
		model.addAttribute(ThymeleafModelAttributeNames.INFORMATION_MESSAGE, message);
	}

	public void addInformationMessage(Model model, Message message, Object... messageParameters) {
		model.addAttribute(ThymeleafModelAttributeNames.INFORMATION_MESSAGE,
				messageLoader.getMessage(message, messageParameters));
	}

	public void addInformationMessage(RedirectAttributes redirectAttributes, Message message,
			Object... messageParameters) {
		redirectAttributes.addFlashAttribute(ThymeleafModelAttributeNames.INFORMATION_MESSAGE,
				messageLoader.getMessage(message, messageParameters));
	}

	public void addDetailedInformationMessage(Model model, DetailedMessage detailedMessage) {
		model.addAttribute(ThymeleafModelAttributeNames.DETAILED_INFORMATION_MESSAGE,
				detailedMessage);
	}

	public void addBindingResultOnRedirectAttributes(RedirectAttributes redirectAttributes,
			BindingResult bindingResult, String validatedObjectName, Object validatedObject) {
		redirectAttributes.addFlashAttribute(BINDING_RESULT_ATTRIBUTE_PREFIX + validatedObjectName,
				bindingResult);
		redirectAttributes.addFlashAttribute(validatedObjectName, validatedObject);
	}

	public void addDetailedErrorMessage(RedirectAttributes redirectAttributes,
			DetailedMessage detailedErrorMessage) {
		redirectAttributes.addFlashAttribute(ThymeleafModelAttributeNames.DETAILED_ERROR_MESSAGE,
				detailedErrorMessage);
	}
}

package io.github.marceloleite2604.util;

import com.figtreelake.util.time.local.LocalDateTimeUtil;
import io.github.marceloleite2604.model.DetailedMessage;
import io.github.marceloleite2604.model.ErrorInformation;
import io.github.marceloleite2604.model.message.Message;
import io.github.marceloleite2604.model.server.ServerMessage;
import io.github.marceloleite2604.model.server.ServerMessageType;
import io.github.marceloleite2604.model.server.ServerResponse;
import io.github.marceloleite2604.service.ThymeleafModelAttributeNames;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Component
public class ServiceUtil {

    private static final String BINDING_RESULT_ATTRIBUTE_PREFIX = "org.springframework.validation.BindingResult.";

    private final ExceptionUtil exceptionUtil;

    private final  LocalDateTimeUtil localDateTimeUtil;

    private final MessageRetriever messageRetriever;

    private final Locale locale;

    @Inject
    public ServiceUtil(ExceptionUtil exceptionUtil, LocalDateTimeUtil localDateTimeUtil, MessageRetriever messageRetriever, Locale locale) {
        this.exceptionUtil = exceptionUtil;
        this.localDateTimeUtil = localDateTimeUtil;
        this.messageRetriever = messageRetriever;
        this.locale = locale;
    }

    public ErrorInformation createErrorInformation(HttpServletRequest httpServletRequest,
                                                   Exception exception) {
        return ErrorInformation.Builder.anErrorInformation()
                .instant(localDateTimeUtil.toStringAsIso8601(LocalDateTime.now()))
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
                messageRetriever.getMessage(message.getCode(), messageParameters, locale));
    }

    public void addDetailedErrorMessage(Model model, DetailedMessage detailedMessage) {
        model.addAttribute(ThymeleafModelAttributeNames.DETAILED_ERROR_MESSAGE, detailedMessage);
    }

    public void addInformationMessage(Model model, String message) {
        model.addAttribute(ThymeleafModelAttributeNames.INFORMATION_MESSAGE, message);
    }

    public void addInformationMessage(Model model, Message message, Object... messageParameters) {
        model.addAttribute(ThymeleafModelAttributeNames.INFORMATION_MESSAGE,
                messageRetriever.getMessage(message.getCode(), messageParameters, locale));
    }

    public void addInformationMessage(RedirectAttributes redirectAttributes, Message message,
                                      Object... messageParameters) {
        redirectAttributes.addFlashAttribute(ThymeleafModelAttributeNames.INFORMATION_MESSAGE,
                messageRetriever.getMessage(message.getCode(), messageParameters, locale));
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

    public void logErrorMessage(Logger logger, Exception exception, Message message, Object... parameters) {
        if (logger.isErrorEnabled()) {
            String messageContent = messageRetriever.getMessage(message, parameters);
            logger.error(messageContent, exception);
        }
    }

    public <T> ResponseEntity<ServerResponse<T>> createInternalServerErrorResponse(Class<T> contentClass, Message message, Object... messageParameters) {

        String messageContent = messageRetriever.getMessage(message, messageParameters);

        ServerMessage serverMessage = ServerMessage.Builder
                .aServerMessage()
                .content(messageContent)
                .type(ServerMessageType.ERROR)
                .build();

        ServerResponse<T> serverResponse = ServerResponse.Builder
                .aServerResponse(contentClass)
                .message(serverMessage)
                .build();

        return new ResponseEntity<>(serverResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


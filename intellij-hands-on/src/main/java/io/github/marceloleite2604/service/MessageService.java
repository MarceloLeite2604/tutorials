package io.github.marceloleite2604.service;

import io.github.marceloleite2604.model.message.ErrorMessage;
import io.github.marceloleite2604.model.message.SiteMessage;
import io.github.marceloleite2604.model.server.ServerMessage;
import io.github.marceloleite2604.model.server.ServerMessageType;
import io.github.marceloleite2604.model.server.ServerResponse;
import io.github.marceloleite2604.util.MessageRetriever;
import io.github.marceloleite2604.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private final MessageRetriever messageRetriever;

    private final ServiceUtil serviceUtil;

    @Inject
    public MessageService(MessageRetriever messageRetriever, ServiceUtil serviceUtil) {
        this.messageRetriever = messageRetriever;
        this.serviceUtil = serviceUtil;
    }

    public ResponseEntity<ServerResponse<Void>> get(String code, List<String> parameters) {

        try {
            String message = messageRetriever.getMessage(code, retrieveParametersArray(parameters));
            ServerMessage serverMessage = ServerMessage.Builder
                    .aServerMessage()
                    .content(message)
                    .type(ServerMessageType.INFORMATION)
                    .build();

            ServerResponse<Void> serverResponse = ServerResponse.Builder
                    .aServerResponse(Void.class)
                    .message(serverMessage)
                    .build();

            return new ResponseEntity<>(serverResponse, HttpStatus.OK);
        } catch (NoSuchMessageException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            serviceUtil.logErrorMessage(LOGGER, exception, ErrorMessage.ERROR_GET_MESSAGE, code);

            return serviceUtil.createInternalServerErrorResponse(Void.class, SiteMessage.COULD_NOT_GET_MESSAGE);
        }
    }

    private Object[] retrieveParametersArray(List<String> parameters) {
        return Optional.ofNullable(parameters)
                    .map(List::toArray)
                    .orElse(new Object[0]);
    }
}

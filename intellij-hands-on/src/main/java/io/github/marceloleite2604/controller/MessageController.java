package io.github.marceloleite2604.controller;

import io.github.marceloleite2604.model.server.ServerResponse;
import io.github.marceloleite2604.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;

@Controller
public class MessageController {

    private final MessageService messageService;

    @Inject
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = SitePaths.MESSAGE+"/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerResponse<Void>> get(@PathVariable(value = "code", required = true) String code, @RequestParam(value = "param", required = false) List<String> parameters) {
        return messageService.get(code, parameters);
    }
}

package io.github.marceloleite2604.configuration;

import io.github.marceloleite2604.model.message.ErrorMessage;
import io.github.marceloleite2604.model.message.SiteMessage;
import io.github.marceloleite2604.properties.MessagesProperties;
import io.github.marceloleite2604.util.PathUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MessageSourceConfiguration {

    @Bean(BeanNames.MESSAGE_SOURCE)
    public MessageSource createMessageSource(MessagesProperties messagesProperties) {
        String[] messageFilePaths = retrieveMessageFilePaths(messagesProperties);
        ReloadableResourceBundleMessageSource validationMessageSource = new ReloadableResourceBundleMessageSource();
        validationMessageSource.setBasenames(messageFilePaths);
        validationMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return validationMessageSource;
    }

    private String[] retrieveMessageFilePaths(MessagesProperties messagesProperties) {
        List<String> messageFilePaths = new ArrayList<>(Arrays.asList(SiteMessage.FILE_PATH,
                ErrorMessage.FILE_PATH));

        messageFilePaths.addAll(messagesProperties.getAdditionalMessageFilePaths());

        PathUtil pathUtil = new PathUtil();

        String messageFileBaseDirectoryPath = pathUtil.appendSeparatorIfNecessary(messagesProperties.getFileBaseDirectoryPath());

        messageFilePaths = messageFilePaths.stream()
                .map(messageFilePath -> messageFileBaseDirectoryPath + messageFilePath)
                .map(pathUtil::removeMultipleSeparators)
                .collect(Collectors.toList());
        return messageFilePaths.toArray(String[]::new);
    }
}

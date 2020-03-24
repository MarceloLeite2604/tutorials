package com.github.marceloleite2604.tutorials.publisher;

import com.github.marceloleite2604.tutorials.Configuration;
import com.github.marceloleite2604.tutorials.util.EnvironmentVariableUtil;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class PublisherExample {

    private static final String TOPIC_ID = "hello_topic";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        EnvironmentVariableUtil.throwExceptionIfDoesNotExist(Configuration.CREDENTIALS_ENVIRONMENT_VARIABLE_NAME);

        ProjectTopicName projectTopicName = ProjectTopicName.of(Configuration.PROJECT_ID, TOPIC_ID);

        Publisher publisher = null;
        List<ApiFuture<String>> futures = new ArrayList<>();

        try {
            publisher = Publisher.newBuilder(projectTopicName).build();

            PubsubMessage pubsubMessage = createMessage("Hello from the other side @ " + LocalDateTime.now().toString());

            ApiFuture<String> future = publisher.publish(pubsubMessage);
            futures.add(future);
        } finally {

            waitPendingRequests(futures);
            Optional.ofNullable(publisher)
                    .ifPresent(Publisher::shutdown);
        }
    }

    private static void waitPendingRequests(List<ApiFuture<String>> futures) throws ExecutionException, InterruptedException {
        List<String> messageIds = ApiFutures.allAsList(futures).get();

        for (String messageId : messageIds) {
            System.out.println(messageId);
        }
    }

    private static PubsubMessage createMessage(String content) {
        ByteString byteString = ByteString.copyFromUtf8(content);
        return PubsubMessage.newBuilder()
                .setData(byteString)
                .build();
    }
}

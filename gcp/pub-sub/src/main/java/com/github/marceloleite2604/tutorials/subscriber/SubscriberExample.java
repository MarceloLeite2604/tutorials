package com.github.marceloleite2604.tutorials.subscriber;

import com.github.marceloleite2604.tutorials.Configuration;
import com.github.marceloleite2604.tutorials.util.EnvironmentVariableUtil;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;

public class SubscriberExample {

    private static final String SUBSCRIPTION_ID = "sub_one";

    public static void main(String[] args) {

        EnvironmentVariableUtil.throwExceptionIfDoesNotExist(Configuration.CREDENTIALS_ENVIRONMENT_VARIABLE_NAME);

        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(Configuration.PROJECT_ID, SUBSCRIPTION_ID);

        try {
            System.out.println("Creating subscriber.");
            Subscriber subscriber = Subscriber.newBuilder(subscriptionName, new MyMessageReceiver()).build();
            System.out.println("Waiting messages.");
            subscriber.startAsync().awaitRunning();
            subscriber.awaitTerminated();
        } catch (IllegalStateException exception) {
            System.err.println("Something has happened with the subscriber.");
            exception.printStackTrace();
        }
    }
}

package com.github.marceloleite2604.tutorials.subscriber;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

public class MyMessageReceiver implements MessageReceiver {

        @Override
        public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
            System.out.println("Received message " + pubsubMessage.getMessageId() + ".");
            System.out.println("Its content is: " + pubsubMessage.getData().toStringUtf8());

            ackReplyConsumer.ack();
        }
}

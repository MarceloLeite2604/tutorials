package org.marceloleite.tutorials.spring.kafka;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class SendMessageListenableFutureCallback
		implements ListenableFutureCallback<SendResult<String, String>> {

	private String message;

	public SendMessageListenableFutureCallback(String message) {
		this.message = message;
	}

	@Override
	public void onSuccess(SendResult<String, String> result) {
		System.out
				.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata()
						.offset() + "]");
	}

	@Override
	public void onFailure(Throwable ex) {
		System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
	}
}

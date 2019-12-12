package main

import (
	"encoding/json"
	"fmt"
	"log"
	"time"

	"github.com/streadway/amqp"
)

func subscribeOnQueue(channel *amqp.Channel, queue string) <-chan amqp.Delivery {
	delivery, err := channel.Consume(
		queue,
		consumerID,
		false,
		false,
		false,
		false,
		nil,
	)

	if err != nil {
		errorMessage := fmt.Sprintf("Error when subscribing to \"%v\" queue.", queue)
		errorExit(err, errorMessage)
	}

	return delivery
}

func monitorDelivery(delivery <-chan amqp.Delivery, done chan<- bool) {

	var result bool

	timeout := time.Tick(waitingTimeSeconds * time.Second)

	log.Printf("Will wait a response for %v seconds.\n", waitingTimeSeconds)

	select {
	case message := <-delivery:

		receiveUserCreationResponse(message)

		message.Ack(false)
		result = true
	case <-timeout:
		log.Printf("Timeout.")
		result = false
	}

	done <- result
}

func receiveUserCreationResponse(delivery amqp.Delivery) {
	var response userCreationResponse
	err := json.Unmarshal(delivery.Body, &response)
	if err != nil {
		errorMessage := fmt.Sprintf("Error while unmarshalling message.\n")
		errorExit(err, errorMessage)
	}

	switch response.Status {
	case userCreationStatusSuccess:
		fmt.Printf("User \"%v\" created successfully.\n", response.User.Name)
		fmt.Printf("User ID is \"%v\".\n", response.User.ID)
		fmt.Printf("Response from server: %v\n", response.Summary)
	case userCreationStatusFailure:
		fmt.Printf("Could not create user \"%v\".\n", response.User.Name)
		fmt.Printf("Response from server: %v\n", response.Summary)
		if response.Messages != nil {
			fmt.Println("The following messages were returned from server:")
			for _, message := range response.Messages {
				fmt.Printf("\t%v\n", message)
			}
		}
	}
}

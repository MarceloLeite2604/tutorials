package main

import (
	"encoding/json"
	"fmt"

	"github.com/streadway/amqp"
)

func publishUser(channel *amqp.Channel, rmqConf rabbitMqConfiguration, user user) {
	message := createMessage(user)
	publish(channel, rmqConf, message)
}

func createMessage(i interface{}) amqp.Publishing {
	bytes, err := json.Marshal(i)

	if err != nil {
		errorMessage := fmt.Sprintf("Error while marshalling object %v.", i)
		errorExit(err, errorMessage)
	}

	return amqp.Publishing{
		Body: bytes,
	}
}

func publish(channel *amqp.Channel, rmqConf rabbitMqConfiguration, message amqp.Publishing) {
	err := channel.Publish(rmqConf.RequestTopic, rmqConf.RoutingKey, false, false, message)
	if err != nil {
		errorMessage := fmt.Sprintf("Error while publishing %v.", message)
		errorExit(err, errorMessage)
	}
}

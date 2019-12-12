package main

import (
	"flag"
	"fmt"
	"os"

	"github.com/streadway/amqp"
)

const (
	configurationFile  = "config/application.yml"
	consumerID         = "rabbitmq-client"
	successExitCode    = 0
	failureExitCode    = 1
	waitingTimeSeconds = 5
)

var (
	userName = flag.String("name", "John Doe", "Person's name to request user creation.")
)

func main() {

	flag.Parse()

	configuration := retrieveConfiguration()

	rmqConf := configuration.RabbitMqClient.RabbitMqConfiguration

	connection := dial(rmqConf)
	defer connection.Close()

	channel := createChannel(connection)
	defer channel.Close()

	delivery := subscribeOnQueue(channel, rmqConf.ResponseQueue)

	deliveryResult := make(chan bool)
	go monitorDelivery(delivery, deliveryResult)

	var myUser = user{
		Name: *userName,
	}

	publishUser(channel, rmqConf, myUser)

	if !<-deliveryResult {
		fmt.Println("Exiting with failure.")
		os.Exit(failureExitCode)
	}

	fmt.Println("Exiting gracefully.")
	os.Exit(successExitCode)
}

func retrieveConfiguration() configuration {
	var conf configuration
	conf.getConfiguration()
	return conf
}

func errorExit(err error, msg string) {
	panic(fmt.Sprintf("%s: %s", msg, err))
}

func dial(rmqConf rabbitMqConfiguration) *amqp.Connection {
	url := fmt.Sprintf("amqp://%v:%v@%v:%v/", rmqConf.Username, rmqConf.Password, rmqConf.Host, rmqConf.Port)

	connection, err := amqp.Dial(url)
	if err != nil {
		errorExit(err, "Error while connecting to AMQP broker.")
	}

	return connection
}

func createChannel(conn *amqp.Connection) *amqp.Channel {
	channel, err := conn.Channel()
	if err != nil {
		errorExit(err, "Error while creating a channel")
	}

	return channel
}

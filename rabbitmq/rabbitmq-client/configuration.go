package main

import (
	"fmt"
	"io/ioutil"

	"gopkg.in/yaml.v2"
)

type rabbitMqConfiguration struct {
	Host          string `yaml:"host"`
	Port          uint16 `yaml:"port"`
	Username      string `yaml:"username"`
	Password      string `yaml:"password"`
	RequestTopic  string `yaml:"request-topic"`
	RoutingKey    string `yaml:"routing-key"`
	ResponseQueue string `yaml:"response-queue"`
}

type configuration struct {
	RabbitMqClient struct {
		RabbitMqConfiguration rabbitMqConfiguration `yaml:"rabbitmq"`
	} `yaml:"rabbitmq-client"`
}

func (conf *configuration) getConfiguration() *configuration {
	yamlFile, err := ioutil.ReadFile(configurationFile)
	if err != nil {
		message := fmt.Sprintf("Error reading \"%v\" configuration file.", configurationFile)
		errorExit(err, message)
	}

	err = yaml.Unmarshal(yamlFile, conf)
	if err != nil {
		message := fmt.Sprintf("Error unmarshalling \"%v\" file.", configurationFile)
		errorExit(err, message)
	}

	return conf
}

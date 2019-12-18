package main

import (
	"fmt"
	"io/ioutil"

	"gopkg.in/yaml.v2"
)

type mySQLConfiguration struct {
	Username string `yaml:"username"`
	Password string `yaml:"password"`
	Protocol string `yaml:"protocol"`
	Host     string `yaml:"host"`
	Port     uint16 `yaml:"port"`
	Database string `yaml:"database"`
}

type goMySQLConfiguration struct {
	MySQL mySQLConfiguration `yaml:"mysql"`
}

type configuration struct {
	GoMySQL goMySQLConfiguration `yaml:"go-mysql"`
}

const (
	configurationFile = "config/application.yml"
)

func (conf *configuration) getConfiguration() *configuration {
	yamlFile, err := ioutil.ReadFile(configurationFile)
	if err != nil {
		message := fmt.Sprintf("Error reading \"%v\" configuration file.", configurationFile)
		checkError(err, message)
	}

	err = yaml.Unmarshal(yamlFile, conf)
	if err != nil {
		message := fmt.Sprintf("Error unmarshalling \"%v\" file.", configurationFile)
		checkError(err, message)
	}

	return conf
}

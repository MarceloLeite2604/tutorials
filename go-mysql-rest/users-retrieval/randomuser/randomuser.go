package randomuser

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

type login struct {
	UUID     string `json:"uuid"`
	Username string `json:"username"`
}

type name struct {
	First string `json:"first"`
	Last  string `json:"last"`
}

// User is a structure which contains all user information mapped and retrieved from the API
type User struct {
	Name  name  `json:"name"`
	Login login `json:"login"`
}

type results struct {
	Users []User `json:"results"`
}

const (
	templateURL = "http://randomuser.me/api/?results=%v"
)

func elaborateURL(quantity int) string {
	return fmt.Sprintf(templateURL, quantity)
}

// RequestUsers requests the defired amount of users to randomuser.me API
func RequestUsers(amount int) ([]User, error) {

	url := elaborateURL(amount)

	response, err := http.Get(url)
	if err != nil {
		err = fmt.Errorf("Error requesting HTTP GET verb on URL \"%v\": %w", url, err)
		return nil, err
	}

	data, err := ioutil.ReadAll(response.Body)
	if err != nil {
		err = fmt.Errorf("Error reading data from response body: %w", err)
		return nil, err
	}

	var res results

	err = json.Unmarshal(data, &res)
	if err != nil {
		err = fmt.Errorf("Error while unmarshalling random user API response body: %w", err)
		return nil, err
	}

	return res.Users, nil
}

func checkError(err error, message string) {
	if err != nil {
		panic(fmt.Sprintf("%s: %s", message, err))
	}
}

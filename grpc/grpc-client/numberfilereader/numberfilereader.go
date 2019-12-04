package numberfilereader

import (
	"bufio"
	"log"
	"os"
	"strconv"
)

func retrieveIntegersFromFile(file *os.File) ([]int32, error) {

	scanner := bufio.NewScanner(file)

	var values []int32
	for scanner.Scan() {
		value, err := strconv.Atoi(scanner.Text())
		if err != nil {
			return nil, err
		}
		values = append(values, int32(value))
	}

	return values, nil
}

// ReadFile reads the number file
func ReadFile(filePath string) []int32 {
	file, err := os.Open(filePath)

	if err != nil {
		log.Printf("Error while opening file \"%v\": %v.", filePath, err)
		panic(err)
	}
	defer file.Close()

	values, err := retrieveIntegersFromFile(file)

	if err != nil {
		log.Printf("Error while retrieving integers from file: %v", err)
		panic(err)
	}

	return values
}

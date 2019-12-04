package main

import (
	"context"
	"flag"
	"fmt"
	"log"

	pb "github.com/MarceloLeite2604/grpc-client/primenumbersguide"
	pns "github.com/MarceloLeite2604/grpc-client/primenumbersserver"
)

var (
	number        = flag.Int("number", 0, "Verified number.")
	serverAddress = flag.String("serverAddress", "localhost:8091", "Grpc server address.")
)

func executeGrpcIsPrime(ctx context.Context, number int32, pnClient pb.PrimeNumbersClient) {
	fmt.Printf("Checking if %v is prime.\n", number)

	var pbNumber = new(pb.Number)

	*pbNumber = pb.Number{Value: number}

	boolean, err := pnClient.IsPrime(ctx, pbNumber)
	if err != nil {
		log.Fatalf("Error while executing grpc method \"IsPrime\": %v", err)
	}

	if boolean.Value {
		fmt.Printf("%v is prime.\n", number)
	} else {
		fmt.Printf("%v is NOT prime.\n", number)
	}

}

func main() {
	flag.Parse()

	pnClient, conn := pns.CreateClient(*serverAddress)
	defer conn.Close()

	ctx, cancel := pns.CreateDefaultContext()
	defer cancel()

	executeGrpcIsPrime(ctx, int32(*number),
		pnClient)

}

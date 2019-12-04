package main

import (
	"context"
	"flag"
	"fmt"
	"log"

	nfr "github.com/MarceloLeite2604/grpc-client/numberfilereader"
	pb "github.com/MarceloLeite2604/grpc-client/primenumbersguide"
	pns "github.com/MarceloLeite2604/grpc-client/primenumbersserver"
)

var (
	filePath      = flag.String("filePath", "numbers.txt", "Path to file which contains numbers to be verified.")
	serverAddress = flag.String("serverAddress", "localhost:8091", "Grpc server address.")
)

func executeGrpcAreAllPrimes(ctx context.Context, values []int32, pnClient pb.PrimeNumbersClient) {
	fmt.Printf("Checking if %v number(s) is(are) prime(s).\n", len(values))

	stream, err := pnClient.AreAllPrimes(ctx)
	if err != nil {
		log.Fatalf("Error while executing grpc method \"AreAllPrimes\": %v", err)
		panic(err)
	}

	var pbNumber = new(pb.Number)
	for _, value := range values {
		*pbNumber = pb.Number{Value: value}
		stream.Send(pbNumber)
	}

	boolean, err := stream.CloseAndRecv()
	if err != nil {
		log.Fatalf("Error while closing and receiving gRPC result: %v", err)
	}

	if boolean.GetValue() {
		fmt.Println("All numbers are prime.")
	} else {
		fmt.Println("At least one number is not prime.")
	}
}

func main() {
	flag.Parse()

	pnClient, conn := pns.CreateClient(*serverAddress)
	defer conn.Close()

	ctx, cancel := pns.CreateDefaultContext()
	defer cancel()

	numbers := nfr.ReadFile(*filePath)

	executeGrpcAreAllPrimes(ctx, numbers,
		pnClient)

}

package main

import (
	"context"
	"flag"
	"fmt"
	"io"
	"log"

	nfr "github.com/MarceloLeite2604/grpc-client/numberfilereader"
	pb "github.com/MarceloLeite2604/grpc-client/primenumbersguide"
	pns "github.com/MarceloLeite2604/grpc-client/primenumbersserver"
)

var (
	filePath      = flag.String("filePath", "numbers.txt", "Path to file which contains numbers to be verified.")
	serverAddress = flag.String("serverAddress", "localhost:8091", "Grpc server address.")
)

func executeGrpcWhichArePrimes(ctx context.Context, values []int32, pnClient pb.PrimeNumbersClient) {
	fmt.Printf("Checking if %v number(s) is(are) prime(s).\n", len(values))

	stream, err := pnClient.WhichArePrimes(ctx)
	if err != nil {
		log.Printf("Error while executing grpc method \"AreAllPrimes\": %v", err)
		panic(err)
	}

	waitc := make(chan struct{})

	go func() {
		index := 0
		for {
			result, err := stream.Recv()
			if err == io.EOF {
				// read done.
				close(waitc)
				return
			}
			if err != nil {
				log.Printf("Error while receiving gRPC response. : %v", err)
			}

			if result.GetValue() {
				fmt.Printf("%v is prime.\n", values[index])
			} else {
				fmt.Printf("%v is NOT prime.\n", values[index])
			}
			index++
		}
	}()

	var pbNumber = new(pb.Number)
	for _, value := range values {
		*pbNumber = pb.Number{Value: value}
		stream.Send(pbNumber)
	}

	stream.CloseSend()
	<-waitc
}
func main() {
	flag.Parse()

	pnClient, conn := pns.CreateClient(*serverAddress)
	defer conn.Close()

	ctx, cancel := pns.CreateDefaultContext()
	defer cancel()

	numbers := nfr.ReadFile(*filePath)

	executeGrpcWhichArePrimes(ctx, numbers, pnClient)
}

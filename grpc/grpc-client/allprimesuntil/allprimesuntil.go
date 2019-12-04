package main

import (
	"context"
	"flag"
	"fmt"
	"io"
	"log"

	pb "github.com/MarceloLeite2604/grpc-client/primenumbersguide"
	pns "github.com/MarceloLeite2604/grpc-client/primenumbersserver"
)

var (
	number        = flag.Int("number", 0, "Verified number.")
	serverAddress = flag.String("serverAddress", "localhost:8091", "Grpc server address.")
)

func executeGrpcAllPrimesUtil(ctx context.Context, number int32, pnClient pb.PrimeNumbersClient) {
	fmt.Printf("Retrieving all prime numbers until %v.\n", number)

	var pbNumber = new(pb.Number)

	*pbNumber = pb.Number{Value: number}

	stream, err := pnClient.AllPrimesUntil(ctx, pbNumber)
	if err != nil {
		log.Fatalf("Error while executing grpc method \"AllPrimesUntil\": %v", err)
	}

	for {
		number, err := stream.Recv()
		if err == io.EOF {
			break
		}
		if err != nil {
			log.Fatalf("Error while executing gRPC method \"AllPrimesUtil\": %v", err)
		}
		fmt.Println(number.GetValue())
	}
}

func main() {
	flag.Parse()

	pnClient, conn := pns.CreateClient(*serverAddress)
	defer conn.Close()

	ctx, cancel := pns.CreateDefaultContext()
	defer cancel()

	executeGrpcAllPrimesUtil(ctx, int32(*number),
		pnClient)

}

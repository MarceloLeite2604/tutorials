package primenumbersserver

import (
	"context"
	"log"
	"time"

	pb "github.com/MarceloLeite2604/grpc-client/primenumbersguide"
	"google.golang.org/grpc"
)

// CreateClient creates a gRPC prime numbers client.
func CreateClient(serverAddress string) (pb.PrimeNumbersClient, *grpc.ClientConn) {
	conn, err := grpc.Dial(serverAddress, grpc.WithInsecure())
	if err != nil {
		log.Fatalf("fail to dial: %v", err)
		panic(err)
	}

	primeNumbersClient := pb.NewPrimeNumbersClient(conn)
	return primeNumbersClient, conn
}

// CreateDefaultContext creates a default context to use on prime numbers client.
func CreateDefaultContext() (context.Context, context.CancelFunc) {
	ctx, cancel := context.WithTimeout(context.Background(), 60*time.Second)
	return ctx, cancel
}

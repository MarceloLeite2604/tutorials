# grpc

This is a small PoC is created to study [gRPC](https://grpc.io/). By using remote procedure calls, the Go programs communicate with a micro Java server to:

1. Check if a number is prime ([isprime.go](grpc-client/isprime/isprime.go));
2. Check if all numbers informed are primes ([areallprimes.go](grpc-client/areallprimes/areallprimes.go));
3. Retrieve all primes from 2 until a specified number ([allprimesuntil.go](grpc-client/allprimesuntil/allprimesuntil.go));
4. Check which numbers are prime given a set of numbers ([whichareprimes.go](grpc-client/whichareprimes/whichareprimes.go)).

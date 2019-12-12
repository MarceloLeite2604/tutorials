# RabbitMQ

This project is an example of two programs communicating asynchronously using [RabbitMQ](https://www.rabbitmq.com/) - an [AMPQ](https://en.wikipedia.org/wiki/Advanced_Message_Queuing_Protocol) message broker.

## Project Contents
1. A [docker-composer yaml file](docker/docker-compose.yml);
2. A [client program](rabbitmq-client/) written in Go;
3. A [server program](rabbitmq-server/) written in Java.

## Structure and flow
![project structure](docs/structure.png)

1. When client program is executed, it creates a "create user" message and send it through "user-creation-request-topic" which dispatches it to "user-creation-request" queue.
2. Server program is monitoring "user-creation-request" queue, so it receives a copy of "create user" message.
3. Server program processes user creation request and sends a "create user response" message though "user-creation-response-topic" exchange which dispatches it to "user-creation-response" queue.
4. Client program is monitoring "user-creation-response" queue, so it receives the response message sent by server and process it.

## Requirements
The following versions were the ones used to create it on my environment. Higher versions might work as well, but who knows? :)
- Docker 19.03.2
- Docker compose 1.21.0
- Java 1.8
- Maven 3.6.2
- Go 1.13.4

## Execution
1. On [docker](docker/) directory, start the RabbitMQ container using `docker-compose up` command. It will build a new Docker image based on original RabbitMQ's. This new image adds some configuration files to automatically create users, queues, exchanges and bindings.
2. On [rabbitmq-server](rabbitmq-server/) directory, execute `mvn package` to generate the executable jar file.
3. Start the server program by executing `java -jar target/rabbitmq-server-1.0-executable.jar`.
4. On [rabbitmq-client](rabbitmq-client/) directory, execute `go build` to generate the client executable.
5. To run the client, execute `rabbitmq-client -name="Marcelo Leite"`. It accepts a parameter `name` wich informs the user name for its creation. If no argument if informed, the client will send "John Doe" as the user name.

## (no-so) FAQs

**Q:** _You said the communication is assynchronous, but the client waits for a server answer._

**A:** True, but the monitoring process is being executed on a goroutine (i. e. a concurrent function). So, technically, our main routine is not waiting for an answer.
If you are curious to run this project receiving an answer from a different execution, try the following:
1. Be sure that server program is not running. If it is, just `ctrl + c` it.
2. Execute the client using the command `rabbitmq-client -name="Neil Peart"` and wait for its conclusion.
3. Now start the server program.
4. Finally, execute once again the client by the command `rabbitmq-client -name="Geddy Lee"` and check the result. :)
  
**Q:** _The client/server/broker is not working. What it might be?_

**A:** This project was created to be executed with the minimal setup requirements, but it still might have some issues. Take a deep breath, check the error messages to see if something spots up. I've also used a RabbitMQ Docker image which contains the monitoring plugin, so you can check the broker status by accesing the `http://localhost:15672` address. Username and password are `admin` and `admin` (no, this is NOT my user/pass for real-world projects).

**Q:** _I have exaustedly checked every error message, but still can't figure out what's wrong. What should I do then?_

**A:** Please open a Github issue about it. I'll be glad to help you once I have some free time.

**Q:** _What is "Out of cheese error"?_

**A:** This is an error returned by [Hex](https://en.wikipedia.org/wiki/Hex_(Discworld)) on Pratchett's _Interesting Times_ book. It is a (sort of) self-assembled computer built inside Unseen Univerity. It mocks well-known "Out of..." error messages such as "Out of memory" or "Out of range". Since one of Hex parts are mices, an "Out of cheese" error makes kinda makes sense!

**Q:** _Neil Peart and Geddy Lee... are you a fan of Rush?_

**A:** Big one!

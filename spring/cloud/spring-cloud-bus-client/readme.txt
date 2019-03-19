Este projeto, assim como o "spring-cloud-bus-properties-server" foram criados seguindo o tutorial "Spring Cloud Bus" da Baeldung 
(https://www.baeldung.com/spring-cloud-bus), entretanto, por utilizar o Spring 2.0, algumas alterações foram necessárias.

Comandos para atualização de propriedades:
curl -X POST http://localhost:8082/actuator/refresh
curl -X POST http://localhost:8082/actuator/bus-refresh

Também, para a criação do WebHook no Git, foi necessário utilizar o ngrok para redirecionar as requisições para o endereço local.
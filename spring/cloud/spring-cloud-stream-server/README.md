# spring-cloud-stream-server

Este projeto deve ser utilizado junto com o projeto "spring=cloud-stream-server".
 
O projeto possui dois profiles: `kafka` e `rabbitmq`. Cada um deles realizando a comunicação através de um serviço de mensagens diferente.

Para que não ocorram alertas de problemas de dependências no Eclipse, configure o Maven para manter os dois perfis ativos. 
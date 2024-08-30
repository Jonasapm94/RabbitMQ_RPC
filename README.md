# Atividade RabbitMQ - básico
## Instalação

 - executar o comando `mvn install`
 - executar após o comando `docker compose build`

## Rodar o programa

 - executar uma instância do rabbitmq: `docker compose up`
 - executar o programa produtor (quantas vezes achar necessário);
 - executar o programa consumidor (quantas instâncias quiser), cada consumidor é um worker que pegará as mensagens da fila e executará um trabalho sobre elas; Até que a fila tenha terminado.
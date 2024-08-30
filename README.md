# Atividade RabbitMQ - básico
## Instalação

 - executar o comando `mvn install`
 - rodar docker compose build

## Rodar o programa

 - executar o programa produtor (quantas vezes achar necessário);
 - executar o programa consumidor (quantas instâncias quiser), cada consumidor é um worker que pegará as mensagens da fila e executará um trabalho sobre elas; Até que a fila tenha terminado.
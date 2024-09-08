# Atividade RabbitMQ - Routing-key
## Instalação

 - executar o comando `mvn install`
 - executar após o comando `docker compose build`

## Rodar o programa

 - executar uma instância do rabbitmq: `docker compose up`
 - executar o programa produtor (quantas vezes achar necessário), configurando qual o nível de log que será usado (info, warning ou error);
 - executar o programa consumidor (quantas instâncias quiser) passando no array args quais níveis de log que seu consumidor deseja receber. Cada consumidor é um worker que pegará as mensagens da fila e executará um trabalho sobre elas; Até que a fila tenha terminado.
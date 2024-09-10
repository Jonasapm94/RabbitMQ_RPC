# Atividade RabbitMQ - RPC
## Instalação

 - executar o comando `mvn install`
 - executar após o comando `docker compose build`

## Rodar o programa

 - executar uma instância do rabbitmq: `docker compose up`
 - executar o programa servidor (quantas vezes achar necessário - quanto mais workers, melhor será a distribuição da carga de tarefas).
 - executar o programa cliente, que enviará um grupo de chamadas rpc e aguardará a resposta.
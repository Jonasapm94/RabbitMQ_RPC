
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Produtor {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");
        try (
                Connection connection = connectionFactory.newConnection();
                Channel canal = connection.createChannel();
        ) {
            // String mensagem = "Olá";
            // String mensagem = String.join("", args);
            String NOME_FILA = "queue_total_time_duravel_persistente";
            
            //(queue, passive, durable, exclusive, autoDelete, arguments)
            boolean duravel = true;
            int prefetchCount = 1;
            canal.basicQos(prefetchCount);
            canal.queueDeclare(NOME_FILA, duravel, false, false, null);
            
            
            
            // ​(exchange, routingKey, mandatory, immediate, props, byte[] body)
            BasicProperties messageProperty = MessageProperties.PERSISTENT_TEXT_PLAIN;
            for(int i=1; i <= 1000000; i++){
                Long timestamp = System.currentTimeMillis();
    
                String mensagem = i + "-" + timestamp.toString();
                canal.basicPublish("", NOME_FILA, false, false, messageProperty , mensagem.getBytes());
                System.out.println("Mensagem publicada: " + mensagem);
                // Random random = new Random();
                // for (int j = 0; j< random.nextInt(4) + 1; j++){
                //     mensagem += ".";
                // }
            }

        }
    }
}



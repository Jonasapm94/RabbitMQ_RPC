import java.util.Random;
import java.time.Instant;
import java.sql.Timestamp;


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
            String NOME_FILA = "queue_total_time";
            
            //(queue, passive, durable, exclusive, autoDelete, arguments)
            boolean duravel = false;
            int prefetchCount = 1;
            canal.basicQos(prefetchCount);
            canal.queueDeclare(NOME_FILA, duravel, false, false, null);
            
            
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            String mensagem = timestamp.toString();

            // ​(exchange, routingKey, mandatory, immediate, props, byte[] body)
            BasicProperties messageProperty = null;
            canal.basicPublish("", NOME_FILA, false, false, messageProperty , mensagem.getBytes());
            System.out.println("Mensagem publicada: " + mensagem);
            // for(int i=0; i< 10; i++){
            //     Random random = new Random();
            //     for (int j = 0; j< random.nextInt(4) + 1; j++){
            //         mensagem += ".";
            //     }
            // }

        }
    }
}



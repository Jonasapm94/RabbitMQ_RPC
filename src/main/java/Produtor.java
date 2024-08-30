import java.util.Random;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

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
            String NOME_FILA = "task_queue";
            
            //(queue, passive, durable, exclusive, autoDelete, arguments)
            boolean duravel = true;
            int prefetchCount = 1;
            canal.basicQos(prefetchCount);
            canal.queueDeclare(NOME_FILA, duravel, false, false, null);
            
            
            for(int i=0; i< 10; i++){
                String mensagem = "Mensagem com meu nome: Jonas Ariel Passos de Medeiros - " + i + " - ";
                Random random = new Random();
                for (int j = 0; j< random.nextInt(4) + 1; j++){
                    mensagem += ".";
                }
                // ​(exchange, routingKey, mandatory, immediate, props, byte[] body)
                canal.basicPublish("", NOME_FILA, false, false, MessageProperties.PERSISTENT_TEXT_PLAIN, mensagem.getBytes());
                System.out.println("Mensagem publicada: " + mensagem);
            }

        }
    }
}



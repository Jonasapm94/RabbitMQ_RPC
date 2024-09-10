
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Produtor {

    private static final String EXCHANGE_NAME = "direct_logs";
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("mqadmin");
        factory.setPassword("Admin123XX_");
        String[] messageProduced = new String[2];
        messageProduced[0] = "warning";
        messageProduced[1] = "Terceira Mensagem de teste: Jonas Ariel Passos de Medeiros";
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // channel.exchangeDeclare(EXCHANGE_NAME, "direct");
    
            // String severity = getSeverity(messageProduced);
            // String message = getMessage(messageProduced);
    
            // channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            // System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
            
            String message = "Teste 1";
            String callbackQueueName = channel.queueDeclare().getQueue();

            BasicProperties props = new BasicProperties
                                        .Builder()
                                        .replyTo(callbackQueueName)
                                        .build();

            channel.basicPublish("", "rpc_queue", props, message.getBytes());
        }
    }

    private static String getMessage(String[] argv) {
        String message = "";
        for (int i = 1; i< argv.length; i++){
            message += argv[i] + " ";
        }
        return message;
    }

    private static  String getSeverity(String[] argv){
        return argv[0];

    }
}



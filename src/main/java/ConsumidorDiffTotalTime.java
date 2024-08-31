import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ConsumidorDiffTotalTime {

    private static long firstTimestamp = 0;
    private static long lastTimestamp = 0;
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");
        Connection conexao = connectionFactory.newConnection();
        Channel canal = conexao.createChannel();

        String NOME_FILA = "diffTotalTime_2";

        boolean duravel = true;
        int prefetchCount = 1;
        canal.basicQos(prefetchCount);
        canal.queueDeclare(NOME_FILA, duravel, false, false, null);


        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody());
            System.out.println("Eu " + consumerTag + " Recebi: " + mensagem);
            
            try {
                doWork(mensagem);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            } finally {
                System.out.println("Trabalho feito.");
                canal.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        boolean autoAck = false;
        // fila, noAck, callback, callback em caso de cancelamento (por exemplo, a fila foi deletada)
        canal.basicConsume(NOME_FILA, autoAck, callback, consumerTag -> {
            System.out.println("Cancelaram a fila: " + NOME_FILA);
        });
    }

    private static void doWork(String mensagem) {
        if(firstTimestamp == 0){
            firstTimestamp = Long.parseLong(mensagem);
            System.out.println("Peguei o primeiro timestamp: " + firstTimestamp);
        } else {
            lastTimestamp = Long.parseLong(mensagem);
            System.out.println("Peguei o último timestamp: " + lastTimestamp);
            System.out.println("Tempo total: " + (lastTimestamp - firstTimestamp));
            firstTimestamp = 0;
            lastTimestamp = 0;
        }
    }
}

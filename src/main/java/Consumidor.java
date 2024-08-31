import java.sql.Timestamp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


public class Consumidor {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");
        Connection conexao = connectionFactory.newConnection();
        Channel canal = conexao.createChannel();

        String NOME_FILA = "queue_total_time";

        boolean duravel = false;
        int prefetchCount = 1;
        canal.basicQos(prefetchCount);
        canal.queueDeclare(NOME_FILA, duravel, false, false, null);

        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody());
            System.out.println("Eu " + consumerTag + " Recebi: " + mensagem);
            
            try {
                doWork(mensagem);
            } catch (Exception e) {
                // TODO: handle exception
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
    static long firstTimestamp;
    static long lastTimestamp;

    private static void doWork(String task) throws InterruptedException {
        // for (char a : task.toCharArray()){
        //     if (a == '.') Thread.sleep(1000);
        // }
        // Thread.sleep(1000);
        String[] strings = task.split("-");
        if (strings[0].equals("1")){
            firstTimestamp = Long.parseLong(strings[1]);
            System.out.println("chegou no 1");
        }
        if (strings[0].equals("1000")){
            System.out.println("chegou no 1000");
            lastTimestamp = Long.parseLong(strings[1]);
            Long diffTimestamp = lastTimestamp - firstTimestamp;
            System.out.println("Diferença: " + diffTimestamp + " milissegundos");
        }
    }
}



import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.ConcurrentHashMap;

public class TaskHandler extends Thread {
    public static ConcurrentHashMap<String, String> orders = new ConcurrentHashMap<>();

    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public void run() {
        setDaemon(true);
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue queue = session.createQueue("geekmarket");
            MessageConsumer consumer = session.createConsumer(queue);

            try {
                while (true) {
                    String msg = ((TextMessage) consumer.receive()).getText();
                    if (!msg.isEmpty()) {
                        for (String s :
                                msg
                                        .replace("[", "")
                                        .replace("]", "")
                                        .split(",")) {
                            orders.put(s, "0");
                        }
                    }
                }
            } finally {
                connection.close();
            }
        } catch (
                Exception ex) {
            Thread t = Thread.currentThread();
            t.getUncaughtExceptionHandler().uncaughtException(t, ex);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(new TaskHandler());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String cmd = reader.readLine();
                if (cmd.equals("all")) {
                    System.out.println("Orders to validate: " + TaskHandler.orders.keySet());
                }
                if (cmd.equals("peak")) {
                    if (TaskHandler.orders.size() != 0) {
                        Map.Entry<String, String> entry = TaskHandler.orders.entrySet().iterator().next();
                        NetClientGet.connect(entry.getKey());
                    } else {
                        System.out.println("No orders");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
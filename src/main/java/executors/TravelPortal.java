package main.java.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class TravelPortal implements Callable {
    private final String providerName;
    private final int responseTime;

    public TravelPortal(String providerName, int responseTime) {
        this.providerName = providerName;
        this.responseTime = responseTime;
    }
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " .Object initializing...");
        TimeUnit.SECONDS.sleep(responseTime);
        return "Quote from " + providerName;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);


        List<Callable<String>> tasks = Arrays.asList(
                new TravelPortal("Air Java", 1),
                new TravelPortal("Hotel Kotlin", 3),
                new TravelPortal("Car Scala", 5)
        );

        // goi lai tat ca cac luong ke ca da xong hay chua
        List<Future<String>> futures = executor.invokeAll(tasks, 3, TimeUnit.SECONDS);

        for (int i = 0; i < tasks.size(); i++) {
            Future<String> future = futures.get(i);
            if (future.isCancelled()) {
                System.out.println("Task " + (i + 1) + ": Timed out or cancelled.");
            } else {
                try {
                    System.out.println("Task " + (i + 1) + ": " + future.get());
                } catch (ExecutionException e) {
                    System.out.println("Task " + (i + 1) + ": Failed with exception - " + e.getMessage());
                }
            }
        }

        executor.shutdown();
    }


}

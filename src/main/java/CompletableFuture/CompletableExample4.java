package main.java.CompletableFuture;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class CompletableExample4 {

    public static final int CORE_POOL_SIZE = 0;
    public static final int MAXIMUM_POOL_SIZE = 1;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final AtomicLong count = new AtomicLong(0);
        final ThreadPoolExecutor pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                String threadName = "Thread - " + count.getAndIncrement();
                t.setName(threadName);
                return t;
            }
        });

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {

            System.out.println("Execute supplyAsync, " + Thread.currentThread());
            sleep(1);
            return "Done supplyAsync";
        }, pool).thenApplyAsync(msg -> {
            System.out.println("Execute thenApplyAsync, " + Thread.currentThread());
            sleep(1);
            return msg.length();
        }, pool).thenAcceptAsync(n -> {
            System.out.println("Execute thenAcceptAsync: " + n + ", " + Thread.currentThread());
            sleep(1);
        }, pool);

        CompletableFuture<Void> future2 = future.thenRunAsync(() -> {
            System.out.println("Done, " + Thread.currentThread());
            sleep(1);
        }, pool);

        future.get();
        future2.get();
        System.out.println("----------------------------------");
        System.out.println("Total Completed Task Count = " + pool.getCompletedTaskCount());
        System.out.println("Total Task Count = " + pool.getTaskCount());
        System.out.println("----------------------------------");
    }

    private static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

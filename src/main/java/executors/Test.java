package main.java.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();

        Runnable r = () -> {
            for(int i = 0;  i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("i = " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        exe.submit(r);
        exe.shutdown();
    }

}

package main.java.threadlocal;

import java.util.HashSet;
import java.util.Set;

public class Main {

    private static ThreadLocal<String> connectionHolder = ThreadLocal.withInitial(() -> "Guest");

    private final Set<String> stooges = new HashSet<String>();
    public Main() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }
    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
    public static void main(String[] args) {
        Main m = new Main();
        Runnable task1 = () -> {
            try {
                connectionHolder.set("Alice");
                Thread.sleep(2000);
                System.out.println("Thread 1: " + connectionHolder.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable task2 = () -> {
            System.out.println("Thread 2: " + connectionHolder.get());
            connectionHolder.set("Bob");
            System.out.println("Thread 2: " + connectionHolder.get());
        };

        Runnable task3 = () -> {
            System.out.println("Thread 3: " + connectionHolder.get());
        };

        new Thread(task1).start();
        new Thread(task2).start();
        new Thread(task3).start();
//        Thread t1 = new Thread(() -> {
//            JdbcSingleton.getInstance(" from t1");
//
//        });
//
//        Thread t2 = new Thread(() -> {
//            JdbcSingleton.getInstance(" from t2");
//
//        });
//
//        Thread t3 = new Thread(() -> {
//            JdbcSingleton.getInstance(" from t3");
//
//        });
//
//        t1.start();
//        t2.start();
//        t3.start();
    }

}

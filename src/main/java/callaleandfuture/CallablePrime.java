package main.java.callaleandfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallablePrime {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
                System.out.println("Hello");
        });
        t.start();

        Callable<Integer> callable = () -> {
            System.out.println("Hello callable");
            return 1;
        };
//        callable.call();
        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread t2 = new Thread(task);
        t2.start();
        System.out.println("task = " + task.get());
    }

}

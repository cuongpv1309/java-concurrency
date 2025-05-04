package main.java.CompletableFuture;

import main.java.callableandfuture.CallableWorker;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "CompletableFuture complete";
                });

        System.out.println(future.get());
    }

}

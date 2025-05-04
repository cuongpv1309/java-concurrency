package main.java.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        System.out.println("Manual complete ");
        completableFuture.complete(computeSomething());

        System.out.println("Get the result ");
        String result = completableFuture.get();
        System.out.println(result);
    }

    public static String computeSomething() {
        try {
            System.out.println("Computing ...");
            Thread.sleep(3000);
            System.out.println("Computing complete !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Future's result";
    }

}

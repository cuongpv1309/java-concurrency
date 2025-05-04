package main.java.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableExample3 {

    public static final int NUMBER = 5;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> times2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return MathUtils.times(NUMBER, 2);
        }).thenApply(n -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return MathUtils.squared(n);
        }).thenApply(n -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return MathUtils.isEvent(n);
        });
        System.out.println(times2.get());
    }

}

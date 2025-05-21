package main.java.executorTask2;

import java.util.concurrent.*;

public class CompletionServiceExample {

    private static ExecutorService executors = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executors);
        for(int i = 0 ; i < 10; i++) {
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {

                    return 0;
                }
            });
        }

    }



}

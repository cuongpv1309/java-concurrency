package main.java.callableandfuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvokeAnyExample {
    private static final int FIX_THREAD_POOL = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(FIX_THREAD_POOL);
        List<Callable<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= 10; i++) {
            list.add(new CallableWorker(i));
        }

        // chỉ cần 1 thread hoàn thành hoặc thrown exception thì luồng sẽ bị huỷ bỏ
        Integer result = executor.invokeAny(list);


        System.out.println("Result = " + result);

        executor.shutdown();

    }
}

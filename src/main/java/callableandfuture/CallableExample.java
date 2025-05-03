package main.java.callableandfuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableExample {

    private static final int GET_TIME_OUT = 3;
    public static void main(String[] args){

        List<Future<Integer>> list = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Callable<Integer> callable;
        Future<Integer> future;
        for(int i = 0; i < 10; i++) {
            callable = new CallableWorker(i);
            future = executor.submit(callable);
            list.add(future);
        }

//        executor.shutdown();
//
//        // di vao day neu van con task dang chay / roi moi chay tiep chuong trinh
//        while(!executor.isTerminated()) {
////            System.out.println("Few thread still running...");
//        }

        int sum = 0;
        for (Future<Integer> f : list) {
            try {
                // chặn chương trình cho đến khi TẤT CẢ task được hoàn thành
                int result = f.get(GET_TIME_OUT, TimeUnit.SECONDS);
                sum += result;
                System.out.println("Result: " + result);
                System.out.println("Is completed? : " + f.isDone());
                System.out.println("Is canceled? : " + f.isCancelled());
            } catch (Exception e) {
                f.cancel(true);
                System.out.println("Exception = " + e);
            }
        }

        executor.shutdown();

        // di vao day neu van con task dang chay
//        while(!executor.isTerminated()) {
////            System.out.println("Few thread still running...");
//        }

        System.out.println("Finished all threads: ");
        System.out.println("Sum all = " + sum);

    }


}

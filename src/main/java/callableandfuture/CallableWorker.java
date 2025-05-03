package main.java.callableandfuture;

import java.util.Random;
import java.util.concurrent.Callable;


// Luồng đếm số lượng tệp phù hợp với keyword truyền vào
public class CallableWorker implements Callable<Integer> {

    private int num;
    private Random random;

    public CallableWorker(int num) {
        this.num = num;
        random = new Random();
    }

    @Override
    public Integer call() throws Exception {
        if(num == 5) Thread.sleep(5000);
        else Thread.sleep(1000);
        int result = num * num;
        System.out.println("Complete " + num);
        return result;
    }
}

package main.java.reentrancy;

public class Widget {
    public synchronized void doSomething() throws InterruptedException {
        for(int i = 0 ; i < 10; i++) {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + ": Widget doing something times = " + i);
        }
    }
}

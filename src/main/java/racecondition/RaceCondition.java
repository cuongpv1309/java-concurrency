package main.java.racecondition;

public class RaceCondition {

    public static int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public static void main(String[] args) throws InterruptedException {
        RaceCondition raceCondition = new RaceCondition();
        new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread() + " " + i);
                RaceCondition.count++;
            }
        }, "increment").start();

        new Thread(() -> {
                for(int i = 0; i < 10000; i++) {
                    System.out.println(Thread.currentThread() + " " + i);
                    RaceCondition.count++;
                }
        }, "decrement").start();

        Thread.sleep(1000);
        System.out.println("count = " + count);
    }

}

package main.java.start;

public class ThreadPrime extends Thread{

    public static class LogLoop implements Runnable {

        @Override
        public void run() {
            for(int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(300);
                    System.out.println("Logs " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread() + " - " + i);
        }
    }

    // run() just simple call method BUT using start() to init new thread
    public static void main(String[] args) {
        new Thread(new ThreadPrime()).start();

        new Thread(new LogLoop()).start();
    }
}

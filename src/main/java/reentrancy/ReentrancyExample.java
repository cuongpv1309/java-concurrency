package main.java.reentrancy;

public class ReentrancyExample {
    public static void main(String[] args) {
        LoggingWidget loggingWidget = new LoggingWidget();

        Thread thread = new Thread(() -> {
            try {
                loggingWidget.doSomething();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-1");
        thread.start();
    }
}

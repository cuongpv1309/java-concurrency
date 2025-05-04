package main.java.CompletableFuture;

public class MathUtils {

    public static int times(int number, int times) {
        return number * times;
    }

    public static int squared(int number) {
        return number * number;
    }

    public static boolean isEvent(int event) {
        return event%2==0;
    }
}

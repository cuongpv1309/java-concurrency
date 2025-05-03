package main.java.test;

import java.util.HashSet;
import java.util.Set;

public class ThreadPrime {

    private static boolean ready;
    private static int number;

    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<>();
    }
    private static class ReaderThread extends Thread {
        public void run() {
            while(!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
        new ThreadPrime().initialize();
        ThreadPrime.knownSecrets.size();
    }
}

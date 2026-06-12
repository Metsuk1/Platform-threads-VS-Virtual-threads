package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {
    private static volatile int increment = 0;

    public static    int increment() {
        return increment++;
    }

    public static void main(String[] args) {
        int numberOfThreads = 10_000;
        List<Thread> threads = new ArrayList<>(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            Thread t = new Thread(() -> {
                increment();
            });
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Ожидаемый результат: " + numberOfThreads);
        System.out.println("Фактический результат: " + increment);
    }
}
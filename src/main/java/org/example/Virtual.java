package org.example;

import java.util.concurrent.Executors;

public class Virtual {
    public static void main(String[] args) {
        int numberOfThreads = 10_0000;

        Runtime runtime = Runtime.getRuntime();
        System.gc();

        long startTime = System.currentTimeMillis();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Starting virtual threads creation...");

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < numberOfThreads; i++) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(1000); // simulate I/O
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long endTime = System.currentTimeMillis();

        System.out.println("Threads created: " + numberOfThreads);
        System.out.println("Total time: " + (endTime - startTime) + " ms");
        System.out.println("Approx memory used: " +
                (memoryAfter - memoryBefore) / 1024 / 1024 + " MB");
    }
}

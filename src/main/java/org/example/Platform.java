package org.example;

import java.util.ArrayList;
import java.util.List;

public class Platform {

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10_000; //  10,000 platform threads
        List<Thread> threads = new ArrayList<>(numberOfThreads);

        Runtime runtime = Runtime.getRuntime();
        System.gc();


        long startTime = System.currentTimeMillis();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Starting platform threads creation...");

        for(int i = 0; i < numberOfThreads; i++) {
            Thread t = new Thread(() -> {
                try{
                    // Simulation of some work
                    Thread.sleep(1000);

                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(t);
            t.start();
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        // Wait for all threads to finish
        for (Thread t : threads) {
            t.join();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Threads created: " + numberOfThreads);
        System.out.println("Total time: " + (endTime - startTime) + " ms");
        System.out.println("Approx memory used: " + (memoryAfter - memoryBefore) / 1024 / 1024 + " MB");
    }
}

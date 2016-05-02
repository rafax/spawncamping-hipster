package com.gajdulewicz.problems;

/**
 * Created by rafal on 28/07/14.
 */
public class Timer implements AutoCloseable {

    private String message;
    private long startTime;

    public Timer(String message) {
        this.message = message;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void close() {
        long end = System.currentTimeMillis();
        System.out.println(String.format("%s took %s ms", message, end - startTime));
    }
}

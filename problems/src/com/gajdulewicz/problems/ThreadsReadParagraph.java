package com.gajdulewicz.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rafal on 23/07/14.
 */
public class ThreadsReadParagraph {

    private final List<String> words;
    private volatile int index = 0;
    private final int threadCount;
    private final List<Object> locks;
    private final Runnable printer;


    public ThreadsReadParagraph(String input, int threadCount) {
        words = Arrays.asList(input.split("\\s"));
        this.threadCount = threadCount;
        locks = new ArrayList<>(threadCount);
        for (int i = 0; i < 10; i++) {
            locks.add(new Object());
        }
        printer = () -> {
            int tId = Integer.parseInt(Thread.currentThread().getName());
            final Object myLock = locks.get(tId);
            final Object nextLock = locks.get((tId + 1) % locks.size());
            while (index < words.size()) {
                try {
                    synchronized (myLock) {
                        locks.get(tId).wait();
                    }
                    if (index < words.size()) {
                        System.out.println(String.format("Thread %s printing index %s: %s", Thread.currentThread().getName(), index, words.get(index)));
                        index++;
                    }
                    synchronized (nextLock) {
                        nextLock.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread " + Thread.currentThread().
                    getName()
                    + " exiting");
        }

        ;
    }

    public void startPrinter() {
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(printer, Integer.toString(i));
            t.start();
        }
        Object firstLock = locks.get(0);
        synchronized (firstLock) {
            firstLock.notify();
        }
    }


    public static void main(String[] args) {
        new ThreadsReadParagraph("In my opinion, this is a fabulous interview question -- at least assuming (1) the candidate is expected to have deep knowledge of threading, and (2) the interviewer also has deep knowledge and is using the question to probe the candidate. It's always possible that the interviewer was looking for a specific, narrow answer, but a competent interviewer should be looking for the following", 10).startPrinter();
    }
}

package com.gajdulewicz.problems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by rafal on 23/07/14.
 */
public class ThreadsReadParagraphWithQueues {

    private Iterator<String> input;
    private final int threadCount;
    private final List<SynchronousQueue<Iterator<String>>> queues;


    public ThreadsReadParagraphWithQueues(Iterator<String> input, int threadCount) {
        this.input = input;
        this.threadCount = threadCount;
        queues = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            queues.add(new SynchronousQueue<>());
        }
    }

    public void startPrinter() {
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Printer(queues.get(i), queues.get((i + 1) % threadCount)), Integer.toString(i)).start();
        }
        try {
            queues.get(0).put(input);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new ThreadsReadParagraphWithQueues(getInput(), 10).startPrinter();
    }

    private static Iterator<String> getInput() {
        try {
            return Files.lines(Paths.get("/usr/share/dict/words")).iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>().iterator();
    }

    private static class Printer implements Runnable {
        private final BlockingQueue<Iterator<String>> input;
        private final BlockingQueue<Iterator<String>> output;

        private Printer(BlockingQueue<Iterator<String>> input, BlockingQueue<Iterator<String>> output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public void run() {
            try {
                Iterator<String> words = input.take();
                while (words.hasNext()) {
                    String firstWord = words.next();
                    System.out.println(String.format("Thread %s printing: %s", Thread.currentThread().getName(), firstWord));
                    output.put(words);
                    words = input.take();
                }
                output.offer(words);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().
                    getName()
                    + " exiting");

        }
    }
}

package com.gajdulewicz.problems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by rafal on 23/07/14.
 */
public class ThreadsReadParagraphWithQueues {

    private final List<String> inputWords;
    private volatile int index = 0;
    private final int threadCount;
    private final List<SynchronousQueue<List<String>>> queues;


    public ThreadsReadParagraphWithQueues(Collection<String> input, int threadCount) {
        inputWords = new LinkedList<>(input);
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
            queues.get(0).put(inputWords);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new ThreadsReadParagraphWithQueues(getInput(), 10).startPrinter();
    }

    private static List<String> getInput() {
        try {
            return Files.readAllLines(Paths.get("/usr/share/dict/words"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static class Printer implements Runnable {
        private final BlockingQueue<List<String>> input;
        private final BlockingQueue<List<String>> output;

        private Printer(BlockingQueue<List<String>> input, BlockingQueue<List<String>> output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public void run() {
            try {
                List<String> words = input.take();
                while (!words.isEmpty()) {
                    String firstWord = words.remove(0);
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

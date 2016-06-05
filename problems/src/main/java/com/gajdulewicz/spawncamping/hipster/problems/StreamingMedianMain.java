package com.gajdulewicz.spawncamping.hipster.problems;

import com.google.common.collect.MinMaxPriorityQueue;

import java.util.*;
import java.util.stream.IntStream;

public class StreamingMedianMain {

    public static Random r = new Random();

    public static void main(String[] args) {
        StreamingMedian sm = new ListStreamingMedian();
        int[] ints = IntStream.generate(() -> r.nextInt()).limit(200000)
                .toArray();
        LinkedList<Float> medians = new LinkedList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < ints.length; i++) {
            sm.add(ints[i]);
            medians.add(sm.getMedian());
        }
        long end = System.currentTimeMillis();
        StreamingMedian hsm = new HeapStreamingMedian();
        System.out.println("Sort took " + (end - start));
        LinkedList<Float> hmedians = new LinkedList<>();
        long hstart = System.currentTimeMillis();
        for (int i = 0; i < ints.length; i++) {
            hsm.add(ints[i]);
            hmedians.add(hsm.getMedian());
        }
        long hend = System.currentTimeMillis();
        System.out.println("hSort took " + (hend - hstart));

        Iterator<Float> iterator = medians.iterator();
        Iterator<Float> hiterator = hmedians.iterator();
        while (iterator.hasNext() && hiterator.hasNext()) {
            if (!Objects.equals(iterator.next(), hiterator.next())) {
                System.out.println("Not equal!!!!");
                return;
            }
        }
        return ;
    }


    public static class HeapStreamingMedian implements StreamingMedian {

        private final MinMaxPriorityQueue<Integer> lower;
        private final MinMaxPriorityQueue<Integer> upper;


        public HeapStreamingMedian() {
            lower = MinMaxPriorityQueue.create();
            upper = MinMaxPriorityQueue.create();
        }

        @Override
        public void add(int n) {
            if (lower.size() == 0) {
                lower.add(n);
            } else if (n > lower.peekLast()) {
                upper.add(n);
            } else {
                lower.add(n);
            }
            if (lower.size() - upper.size() > 1) {
                upper.add(lower.pollLast());
            }
            if (lower.size() - upper.size() < -1) {
                lower.add(upper.pollFirst());
            }
        }

        @Override
        public float getMedian() {
            if (lower.size() == upper.size()) {
                return (lower.peekLast() + upper.peekFirst()) / 2f;
            }
            if (lower.size() > upper.size()) {
                return lower.peekLast();
            } else {
                return upper.peekFirst();
            }

        }
    }

    public static class ListStreamingMedian implements StreamingMedian {
        private List<Integer> data = new ArrayList<>();

        public void add(int n) {
            data.add(n);
        }

        public float getMedian() {
            Collections.sort(data);
            return data.size() % 2 == 1 ? data.get(data.size() / 2) : (data.get(data.size() / 2) + data.get((data.size() / 2) - 1)) / 2f;
        }
    }

    public interface StreamingMedian {
        void add(int n);

        float getMedian();
    }
}

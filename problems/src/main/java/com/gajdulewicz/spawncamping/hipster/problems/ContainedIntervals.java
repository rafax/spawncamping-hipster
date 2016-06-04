package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.*;

/**
 * Created by rafal on 06/08/14.
 */
public class ContainedIntervals {

    static Interval[] find(List<Interval> intervals) {
        //Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        for (int i = 0; i < intervals.size(); i++) {
            for (int j = 0; j < intervals.size(); j++) {
                if (i != j) {
                    Interval a = intervals.get(i), b = intervals.get(j);
                    if (a.start
                            <= b.start && a.start
                            >= b.start || b.start <= a.start && b.start >= a.start) {
                        return new Interval[]{a, b};
                    }
                }
            }
        }
        return null;
    }

    static Interval[] findSorted(List<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i - 1).end > intervals.get(i).end) {
                return new Interval[]{intervals.get(i - 1), intervals.get(i)};
            }
        }
        return null;
    }


    public static void main(String[] args) {
        int bound = 1000000;
        Random r = new Random();
        for (int iteration = 0; iteration < 10; iteration++) {
            List<Interval> it = new ArrayList<>();
            for (int i = 0; i < bound; i++) {
                int start = r.nextInt(bound * 100);
                int end = start + r.nextInt(bound * 100);
                it.add(new Interval(start, end));
            }
            try (com.gajdulewicz.spawncamping.hipster.problems.Timer t = new com.gajdulewicz.spawncamping.hipster.problems.Timer("n2")) {
                System.out.println(Arrays.toString(ContainedIntervals.find(it)));
            }
            try (com.gajdulewicz.spawncamping.hipster.problems.Timer t = new com.gajdulewicz.spawncamping.hipster.problems.Timer("nlogn")) {
                System.out.println(Arrays.toString(ContainedIntervals.findSorted(it)));
            }
        }
    }

    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", start, end);
        }
    }
}

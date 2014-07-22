package com.gajdulewicz.algo.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;


public class MergeSorterTest {

    private static int TEST_SIZE = 100000;

    @Test
    public void itShouldSortTheInputWhileNotBeingMuchSlowerThanBuiltinSort() throws Exception {
        List<Integer> list = new ArrayList<>(TEST_SIZE);
        Random r = new Random();
        for (int i = 0; i < TEST_SIZE; i++) {
            list.add(r.nextInt(TEST_SIZE));
        }
        List<Integer> result;
        try (Timer t = new Timer("Merge sorter")) {
            result = new MergeSorter<Integer>().sort(list);
        }
        try (Timer t = new Timer("Collections.sort")) {
            Collections.sort(list);
        }
        assertEquals(list, result);
    }

    class Timer implements AutoCloseable {

        private String message;
        private long startTime;

        Timer(String message) {
            this.message = message;
            startTime = System.currentTimeMillis();
        }

        @Override
        public void close() throws Exception {
            long end = System.currentTimeMillis();
            System.out.println(String.format("%s took %s ms", message, end - startTime));
        }
    }
}
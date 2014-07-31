package com.gajdulewicz.algo.sort;

import com.gajdulewicz.ds.maps.BinaryHeap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rafal on 29/07/14.
 */
public class HeapSorter<T extends Comparable> implements Sorter<T> {

    private static final int TEST_SIZE = 100;

    @Test
    public void itShouldSortAsCollectionsSort() {
        List<Integer> list = new ArrayList<>(TEST_SIZE);
        Random r = new Random();
        for (int i = 0; i < TEST_SIZE; i++) {
            list.add(r.nextInt(TEST_SIZE));
        }
        List<Integer> result;
        result = new MergeSorter<Integer>().sort(list);

        Collections.sort(list);

        assertEquals(list, result);
    }

    @Override
    public List<T> sort(List<T> input) {
        BinaryHeap<T> heap = new BinaryHeap<T>();
        List<T> result = new ArrayList<>();
        for (T elem : input) {
            heap.add(elem);
        }
        for (int i = 0; i < input.size(); i++) {
            result.add(heap.min());
        }
        return result;
    }
}

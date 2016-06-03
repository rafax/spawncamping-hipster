package com.gajdulewicz.spawncamping.hipster.ds.maps;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class BinaryHeapTest {

    @Test
    public void testAdd() throws Exception {
        BinaryHeap<Integer> heap = getHeap();
        for (int i = 100; i >= 0; i--) {
            heap.add(i);
            assertEquals((100 - i) + 1, heap.size());
            assertEquals(Integer.valueOf(i), heap.min());
            assertTrue(heap.isValid());
        }
    }

    private BinaryHeap<Integer> getHeap() {
        return new BinaryHeap<>((o1, o2) -> o1.compareTo(o2));
    }

    @Test
    public void testMin() throws Exception {

    }

    @Test
    public void testRemoveMin() throws Exception {
        BinaryHeap<Integer> heap = getHeap();
        for (int i = 100; i >= 0; i--) {
            heap.add(i);
        }
        for (int i = 0; i < 101; i++) {
            Integer min = heap.removeMin();
            assertEquals(Integer.valueOf(i), min);
            assertTrue(heap.isValid());
        }
    }

    @Test
    public void testEntrySet() throws Exception {

    }
}
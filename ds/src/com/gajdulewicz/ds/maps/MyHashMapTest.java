package com.gajdulewicz.ds.maps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyHashMapTest {

    @Test
    public void ItShouldStoreValuesAndCorrectlyRespondToContains() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, i + 100);
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(map.containsKey(i));
            assertTrue(map.containsValue(i + 100));
            assertEquals((long)map.get(i), i + 100);
        }
    }

    @Test
    public void ItShouldSetTheSizeToZeroAfterClear() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(i, i + 100);
        }
        map.clear();
        assertEquals(map.size(), 0);
    }

    @Test
    public void ItShouldIncrementSizeOnPut() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, i + 100);
            assertEquals(map.size(), i + 1);
        }
    }


}
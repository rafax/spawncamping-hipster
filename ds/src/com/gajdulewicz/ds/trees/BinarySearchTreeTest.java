package com.gajdulewicz.ds.trees;

import org.junit.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;

public class BinarySearchTreeTest {

    @Test
    public void testInsertIncreasesSizeAndStoresValues() {
        Tree<Integer, String> tree = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        tree.insert(5, "-1");
        for (int i = 0; i < 10; i++) {
            tree.insert(i, Integer.toString(i));
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(Integer.toString(i), tree.find(i));
        }
        assertEquals(10, tree.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteThrowsOnNonExistentKey() {
        Tree<Integer, String> tree = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        tree.insert(0, "1");
        tree.delete(1);
    }

    @Test()
    public void deleteDecreasesSizeAndRemovesElements() {
        Tree<Integer, String> tree = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        tree.insert(5, "-1");
        for (int i = 0; i < 10; i++) {
            tree.insert(i, Integer.toString(i));
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(Integer.toString(i), tree.delete(i));
            assertEquals(10 - i - 1, tree.size());
        }
    }
}
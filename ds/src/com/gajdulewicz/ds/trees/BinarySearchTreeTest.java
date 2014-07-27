package com.gajdulewicz.ds.trees;

import org.junit.Test;

import java.util.Comparator;

import static junit.framework.TestCase.assertEquals;

public class BinarySearchTreeTest {

    @Test
    public void testInsert() throws Exception {
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

        System.out.println(tree);

        assertEquals(10, tree.size());
    }
}
package com.gajdulewicz.spawncamping.hipster.problems;

import com.gajdulewicz.spawncamping.hipster.ds.trees.Entry;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by rafal on 07/08/14.
 */
public class BinaryTreeChecker {

    static boolean isBinaryTree(Entry<Integer, Integer> root) {
        return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean isBinaryTreeIterator(Entry<Integer, Integer> root) {
        final Iterator<Integer> integerIterator = inOrderIterator(root);
        if (!integerIterator.hasNext()) {
            return true;
        }
        int prev = integerIterator.next();
        while (integerIterator.hasNext()) {
            int curr = integerIterator.next();
            if (prev > curr) {
                return false;
            }
            prev = curr;
        }
        return true;
    }

    static Iterator<Integer> inOrderIterator(Entry<Integer, Integer> root) {
        return new Iterator<Integer>() {

            boolean initialized = false;
            Entry<Integer, Integer> next = root;


            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                if (!initialized) {
                    while (next.getLeftChild() != null) {
                        next = next.getLeftChild();
                    }
                    initialized = true;
                }
                Integer result = next.getKey();
                findNext();
                return result;
            }

            private void findNext() {
                if (next.getRightChild() != null) {
                    next = next.getRightChild();
                    while (next.getLeftChild() != null) {
                        next = next.getLeftChild();
                    }
                } else {
                    while (next.getParent() != null) {
                        if (next.getParent().getLeftChild() == next) {
                            next = next.getParent();
                            return;
                        }
                        next = next.getParent();
                    }
                    next = next.getParent();
                }
            }
        };
    }

    private static boolean check(Entry<Integer, Integer> root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }
        if (root.getKey() < minValue || root.getKey() > maxValue) {
            return false;
        }
        if (root.getLeftChild() != null) {
            if (root.getLeftChild().getKey() > root.getKey()) {
                return false;
            }
            boolean subTreeBst = check(root.getLeftChild(), minValue, Math.min(maxValue, root.getKey()));
            if (!subTreeBst) {
                return false;
            }
        }
        if (root.getRightChild() != null) {
            if (root.getRightChild().getKey() < root.getKey()) {
                return false;
            }
            boolean subTreeBst = check(root.getRightChild(), Math.max(minValue, root.getKey()), maxValue);
            if (!subTreeBst) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void rootNull() {
        assertTrue(BinaryTreeChecker.isBinaryTreeIterator(null));
    }

    @Test
    public void justRoot() {
        Entry<Integer, Integer> root = new Entry<>(1, null);
        assertTrue(BinaryTreeChecker.isBinaryTreeIterator(root));
    }

    @Test
    public void bstTriangle() {
        Entry<Integer, Integer> root = new Entry<>(2, null);
        root.setLeftChild(new Entry<>(1, null));
        root.getLeftChild().setParent(root);
        root.setRightChild(new Entry<>(3, null));
        root.getRightChild().setParent(root);
        assertTrue(BinaryTreeChecker.isBinaryTreeIterator(root));
    }

    @Test
    public void badTriangle() {
        Entry<Integer, Integer> root = new Entry<>(2, null);
        root.setLeftChild(new Entry<>(3, null));
        root.getLeftChild().setParent(root);
        root.setRightChild(new Entry<>(1, null));
        root.getRightChild().setParent(root);
        assertFalse(BinaryTreeChecker.isBinaryTreeIterator(root));

    }

    @Test
    public void badDeep() {
        Entry<Integer, Integer> root = new Entry<>(2, null);
        root.setLeftChild(new Entry<>(1, null));
        root.getLeftChild().setParent(root);
        root.getLeftChild().setRightChild(new Entry<>(-1, null));
        root.getLeftChild().getRightChild().setParent(root.getLeftChild());
        root.setRightChild(new Entry<>(3, null));
        root.getRightChild().setParent(root);
        assertFalse(BinaryTreeChecker.isBinaryTreeIterator(root));
    }

    @Test
    public void badDeepRight() {
        Entry<Integer, Integer> root = new Entry<>(3, null);
        root.setLeftChild(new Entry<>(1, null));
        root.getLeftChild().setParent(root);
        root.getLeftChild().setRightChild(new Entry<>(4, null));
        root.getLeftChild().getRightChild().setParent(root.getLeftChild());
        root.setRightChild(new Entry<>(5, null));
        root.getRightChild().setParent(root);
        assertFalse(BinaryTreeChecker.isBinaryTreeIterator(root));
    }


}

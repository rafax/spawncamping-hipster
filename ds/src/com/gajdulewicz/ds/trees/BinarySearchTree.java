package com.gajdulewicz.ds.trees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by rafal on 27/07/14.
 */
public class BinarySearchTree<K, V> implements Tree<K, V> {
    private Entry<K, V> root;
    private int size;

    private Comparator<K> comparator;

    public BinarySearchTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(new Comparator<K>() {
            @Override
            @SuppressWarnings("unchecked")
            public int compare(K o1, K o2) {
                return ((Comparable) o1).compareTo(o2);
            }
        });
    }

    @Override
    public void insert(K key, V value) {
        if (root == null) {
            root = new Entry<>(key, value);
        } else {
            insertRecursive(root, key, value);
        }
    }

    @Override
    public V delete(K key) {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        return deleteRecursive(root, key);
    }

    @Override
    public V find(K key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<V> inOrder() {
        return null;
    }

    @Override
    public Iterator<V> preOrder() {
        return null;
    }

    @Override
    public Iterator<V> postOrder() {
        return null;
    }

    private void insertRecursive(Entry<K, V> parent, K key, V value) {
        int cmp = comparator.compare(parent.getKey(), key);
        if (cmp == 0) {
            parent.setValue(value);
        } else if (cmp > 0) {
            if (parent.getLeftChild() != null) {
                insertRecursive(parent.getLeftChild(), key, value);
            } else {
                Entry<K, V> entry = new Entry<>(key, value);
                parent.setLeftChild(entry);
                entry.setParent(parent);
            }
        } else if (cmp < 0) {
            if (parent.getRightChild() != null) {
                insertRecursive(parent.getRightChild(), key, value);
            } else {
                Entry<K, V> entry = new Entry<>(key, value);
                parent.setRightChild(entry);
                entry.setParent(parent);
            }
        }

    }

    private V deleteRecursive(Entry<K, V> node, K key) {
        int cmp = comparator.compare(node.getKey(), key);
        if (cmp == 0) {
            return deleteNode(node);
        } else if (cmp > 0) {
            return deleteRecursive(node.getRightChild(), key);
        } else {
            return deleteRecursive(node.getLeftChild(), key);
        }
    }

    private V deleteNode(Entry<K, V> node) {
        V result = node.getValue();
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            if (node.getParent().getRightChild() == node) {
                node.getParent().setRightChild(null);
            } else {
                node.getParent().setLeftChild(null);
            }
        } else if (node.getLeftChild() != null && node.getRightChild() == null) {
            node.getLeftChild().setParent(node.getParent());
            if (node.getParent().getRightChild() == node) {
                node.getParent().setRightChild(node.getLeftChild());
            } else {
                node.getParent().setLeftChild(node.getLeftChild());
            }
        } else if (node.getLeftChild() == null && node.getRightChild() != null) {
            node.getRightChild().setParent(node.getParent());
            if (node.getParent().getRightChild() == node) {
                node.getParent().setRightChild(node.getRightChild());
            } else {
                node.getParent().setLeftChild(node.getRightChild());
            }
        } else {// two children
            Entry<K, V> smallestSuccessor = minChild(node.getRightChild());
            node.setValue(smallestSuccessor.getValue());
            deleteNode(smallestSuccessor);
        }
        return result;
    }

    private Entry<K, V> minChild(Entry<K, V> node) {
        Entry<K, V> current = node;
        while (current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return current;
    }
}
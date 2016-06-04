package com.gajdulewicz.spawncamping.hipster.ds.trees;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;

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
            size++;
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
        if (root == null) {
            return null;
        }
        Entry<K, V> current = root;
        while (current != null) {
            int cmp = comparator.compare(current.getKey(), key);
            if (cmp == 0) {
                return current.getValue();
            } else if (cmp < 0) {
                current = current.getRightChild();
            } else {
                current = current.getLeftChild();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Stream<K> keySet() {
        return inOrderStream(root).map(e -> e.getKey());
    }

    @Override
    public Stream<V> inOrder() {
        return inOrderStream(root).map(e -> e.getValue());
    }

    @Override
    public Stream<V> preOrder() {
        return preOrderStream(root).map(e -> e.getValue());
    }

    private Stream<Entry<K, V>> preOrderStream(Entry<K, V> current) {
        if (current == null) {
            return Stream.empty();
        }
        return concat(
                concat(of(current), preOrderStream(current.getLeftChild())),
                preOrderStream(current.getRightChild()));
    }

    @Override
    public Stream<V> postOrder() {
        return postOrderStream(root).map(e -> e.getValue());
    }

    private Stream<Entry<K, V>> postOrderStream(Entry<K, V> current) {
        if (current == null) {
            return Stream.empty();
        }
        return concat(
                concat(inOrderStream(current.getLeftChild()), inOrderStream(current.getRightChild())),
                of(current));
    }

    private Stream<Entry<K, V>> inOrderStream(Entry<K, V> current) {
        if (current == null) {
            return Stream.empty();
        }
        return concat(
                concat(inOrderStream(current.getLeftChild()), of(current)),
                inOrderStream(current.getRightChild()));
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
                size++;
                parent.setLeftChild(entry);
                entry.setParent(parent);
            }
        } else if (cmp < 0) {
            if (parent.getRightChild() != null) {
                insertRecursive(parent.getRightChild(), key, value);
            } else {
                Entry<K, V> entry = new Entry<>(key, value);
                size++;
                parent.setRightChild(entry);
                entry.setParent(parent);
            }
        }

    }

    private V deleteRecursive(Entry<K, V> node, K key) {
        if (node == null) {
            throw new NoSuchElementException("Node with given key not found: " + key);
        }
        int cmp = comparator.compare(node.getKey(), key);
        if (cmp == 0) {
            return deleteNode(node);
        } else if (cmp > 0) {
            return deleteRecursive(node.getLeftChild(), key);
        } else {
            return deleteRecursive(node.getRightChild(), key);
        }
    }

    private V deleteNode(Entry<K, V> node) {
        V result = node.getValue();
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            if (node.getParent() == null) {
                root = null;
            } else {
                if (node.getParent().getRightChild() == node) {
                    node.getParent().setRightChild(null);
                } else {
                    node.getParent().setLeftChild(null);
                }
            }
        } else if (node.getLeftChild() != null && node.getRightChild() == null) {
            node.getLeftChild().setParent(node.getParent());
            if (node.getParent() == null) {
                root = node.getLeftChild();
            } else {
                if (node.getParent().getRightChild() == node) {
                    node.getParent().setRightChild(node.getLeftChild());
                } else {
                    node.getParent().setLeftChild(node.getLeftChild());
                }
            }
        } else if (node.getLeftChild() == null && node.getRightChild() != null) {
            node.getRightChild().setParent(node.getParent());
            if (node.getParent() == null) {
                root = node.getRightChild();
            } else {
                if (node.getParent().getRightChild() == node) {
                    node.getParent().setRightChild(node.getRightChild());
                } else {
                    node.getParent().setLeftChild(node.getRightChild());
                }
            }
        } else {// two children
            Entry<K, V> smallestSuccessor = minChild(node.getRightChild());
            node.setValue(smallestSuccessor.getValue());
            deleteNode(smallestSuccessor);
        }
        size--;
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

package com.gajdulewicz.ds.trees;

/**
 * Created by rafal on 27/07/14.
 */
public class Entry<K, V> {
    private K key;
    private V value;
    private Entry<K, V> parent;
    private Entry<K, V> leftChild;
    private Entry<K, V> rightChild;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Entry<K, V> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Entry<K, V> rightChild) {
        this.rightChild = rightChild;
    }

    public Entry<K, V> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Entry<K, V> leftChild) {
        this.leftChild = leftChild;
    }

    public Entry<K, V> getParent() {
        return parent;
    }

    public void setParent(Entry<K, V> parent) {
        this.parent = parent;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }
}

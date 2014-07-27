package com.gajdulewicz.ds.trees;

import java.util.Iterator;

/**
 * Created by rafal on 27/07/14.
 */
public interface Tree<K, V>{
        void insert(K key,V value);

        V delete(K key);

        V find(K key);

        int size();

        Iterator<V> inOrder();

        Iterator<V>preOrder();

        Iterator<V>postOrder();

        }

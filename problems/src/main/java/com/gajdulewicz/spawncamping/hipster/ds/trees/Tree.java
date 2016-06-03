package com.gajdulewicz.spawncamping.hipster.ds.trees;

import java.util.stream.Stream;

/**
 * Created by rafal on 27/07/14.
 */
public interface Tree<K, V> {
    void insert(K key, V value);

    V delete(K key);

    V find(K key);

    int size();

    Stream<K> keySet();

    Stream<V> inOrder();

    Stream<V> preOrder();

    Stream<V> postOrder();


}

package com.gajdulewicz.ds.maps;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rafal on 21/07/14.
 */
public class MyHashMap<K, V> implements Map<K, V> {


    private int size;
    private float loadFactor = 0.75f;
    private int initialCapacity = 10;

    private List<List<com.gajdulewicz.ds.maps.Entry<K, V>>> buckets = new ArrayList<>();

    public MyHashMap() {
        buckets.clear();
        for (int i = 0; i < initialCapacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public boolean containsKey(Object key) {
        final List<com.gajdulewicz.ds.maps.Entry<K, V>> bucket = buckets.get(indexFor(key));
        for (int i = 0; i < bucket.size(); i++) {
            com.gajdulewicz.ds.maps.Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<com.gajdulewicz.ds.maps.Entry<K, V>> bucket : buckets) {
            for (com.gajdulewicz.ds.maps.Entry<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        final List<com.gajdulewicz.ds.maps.Entry<K, V>> bucket = buckets.get(indexFor(key));
        for (int i = 0; i < bucket.size(); i++) {
            com.gajdulewicz.ds.maps.Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        final List<com.gajdulewicz.ds.maps.Entry<K, V>> bucket = buckets.get(indexFor(key));
        for (int i = 0; i < bucket.size(); i++) {
            com.gajdulewicz.ds.maps.Entry<K, V> entry = bucket.get(i);
            if (bucket.get(i).getKey().equals(key)) {
                V oldValue = entry.getValue();
                bucket.add(i, new com.gajdulewicz.ds.maps.Entry<K, V>(key, value));
                return oldValue;
            }
        }
        size++;
        bucket.add(new com.gajdulewicz.ds.maps.Entry<K, V>(key, value));
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry entry : map.entrySet()) {
            put((K) entry.getKey(), (V) entry.getValue());
        }
    }

    @Override
    public void clear() {
        buckets.clear();
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return buckets.stream().flatMap((bucket) -> bucket.stream()).map(com.gajdulewicz.ds.maps.Entry::getKey).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return buckets.stream().flatMap((bucket) -> bucket.stream()).map(com.gajdulewicz.ds.maps.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return buckets.stream().flatMap((bucket) -> bucket.stream()).collect(Collectors.toSet());
    }

    private int indexFor(Object key) {
        return key.hashCode() % buckets.size();
    }

}

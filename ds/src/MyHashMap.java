import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rafal on 21/07/14.
 */
public class MyHashMap<K, V> implements Map<K, V> {


    private int size;
    private float loadFactor = 0.75f;
    private int initialCapacity = 10;

    private List<List<MyEntry<K, V>>> buckets = new ArrayList<>();

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
        final List<MyEntry<K, V>> bucket = buckets.get(indexFor(key));
        for (int i = 0; i < bucket.size(); i++) {
            MyEntry<K, V> entry = bucket.get(i);
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<MyEntry<K, V>> bucket : buckets) {
            for (MyEntry<K, V> entry : bucket) {
                if (entry.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        final List<MyEntry<K, V>> bucket = buckets.get(indexFor(key));
        for (int i = 0; i < bucket.size(); i++) {
            MyEntry<K, V> entry = bucket.get(i);
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        final List<MyEntry<K, V>> bucket = buckets.get(indexFor(key));
        for (int i = 0; i < bucket.size(); i++) {
            MyEntry<K, V> entry = bucket.get(i);
            if (bucket.get(i).key.equals(key)) {
                V oldValue = entry.value;
                bucket.add(i, new MyEntry<K, V>(key, value));
                return oldValue;
            }
        }
        size++;
        bucket.add(new MyEntry<K, V>(key, value));
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        buckets.clear();
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return buckets.stream().flatMap((bucket) -> bucket.stream()).map(MyEntry::getKey).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return buckets.stream().flatMap((bucket) -> bucket.stream()).map(MyEntry::getValue).collect(Collectors.toList());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return buckets.stream().flatMap((bucket) -> bucket.stream()).collect(Collectors.toSet());
    }

    private int indexFor(Object key) {
        return key.hashCode() % buckets.size();
    }

    private class MyEntry<K, V> implements Entry<K, V> {
        private  K key;
        private  V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value= value;
            return oldValue;
        }
    }
}

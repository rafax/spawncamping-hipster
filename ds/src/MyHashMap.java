import java.util.*;

/**
 * Created by rafal on 21/07/14.
 */
public class MyHashMap<K, V> implements Map<K, V> {


    private int size;
    private float loadFactor = 0.75f;

    private List<List<MyEntry<K, V>>> buckets = new ArrayList<>();

    public MyHashMap() {
        buckets.add(new ArrayList<MyEntry<K, V>>());
        buckets.add(new ArrayList<MyEntry<K, V>>());
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
        buckets = new ArrayList<>();
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private int indexFor(Object key) {
        return key.hashCode() % buckets.size();
    }

    private class MyEntry<K, V> {
        public final K key;
        public final V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

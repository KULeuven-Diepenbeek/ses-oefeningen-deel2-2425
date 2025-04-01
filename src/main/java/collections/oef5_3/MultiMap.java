package collections.oef5_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MultiMap<K, V> {

    private final Map<K, Set<V>> entries = new HashMap<>();

    public void put(K key, V value) {
        Set<V> set = entries.computeIfAbsent(key, k -> new HashSet<>());
        set.add(value);
    }

    public Set<V> get(K key) {
        return entries.get(key);
    }

    public void remove(K key, V value) {
        Set<V> set = entries.get(key);
        set.remove(value);
    }

    public int size() {
        return entries.size();
    }

}

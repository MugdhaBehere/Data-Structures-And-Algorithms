package Miscellaneous;

import java.util.LinkedList;




class HashTable<K, V> {
    private LinkedList<Entry<K, V>>[] buckets;
    private int capacity;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        this.size = 0;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        buckets[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(10);

        // Insert some key-value pairs
        hashTable.put("apple", 10);
        hashTable.put("banana", 20);
        hashTable.put("orange", 30);

        // Retrieve values
        System.out.println("Value for 'apple': " + hashTable.get("apple"));
        System.out.println("Value for 'banana': " + hashTable.get("banana"));
        System.out.println("Value for 'orange': " + hashTable.get("orange"));

        // Update value for existing key
        hashTable.put("apple", 15);
        System.out.println("Updated value for 'apple': " + hashTable.get("apple"));

        // Insert more key-value pairs
        hashTable.put("grapes", 25);
        hashTable.put("watermelon", 40);

        // Retrieve values
        System.out.println("Value for 'grapes': " + hashTable.get("grapes"));
        System.out.println("Value for 'watermelon': " + hashTable.get("watermelon"));

        // Size of the hash table
        System.out.println("Size of hash table: " + hashTable.size());
    }

}

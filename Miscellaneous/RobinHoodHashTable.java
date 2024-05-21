package Miscellaneous;

import java.util.Arrays;

class RobinHoodHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private Entry<K, V>[] table;
    private int size;
    private int capacity;

    public RobinHoodHashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public RobinHoodHashTable(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = hash(key);
        int distance = 0;

        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                table[index].value = value;
                return;
            }

            if (table[index].distance < distance) {
                swap(index, key, value, distance);
                return;
            }

            index = (index + 1) % capacity;
            distance++;
        }

        table[index] = new Entry<>(key, value, distance);
        size++;
    }

    public V get(K key) {
        int index = findIndex(key);
        return index != -1 ? table[index].value : null;
    }

    public void remove(K key) {
        int index = findIndex(key);
        if (index != -1) {
            table[index] = null;
            size--;
        }
    }

    public boolean containsKey(K key) {
        return findIndex(key) != -1;
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private int findIndex(K key) {
        int index = hash(key);
        int distance = 0;

        while (table[index] != null && distance <= table[index].distance) {
            if (table[index].key.equals(key)) {
                return index;
            }
            index = (index + 1) % capacity;
            distance++;
        }

        return -1;
    }

    private void swap(int index, K key, V value, int distance) {
        Entry<K, V> temp = new Entry<>(key, value, distance);
        Entry<K, V> currentEntry = table[index];
        table[index] = temp;
        int currentDistance = 0;
        index = (index + 1) % capacity;

        while (currentEntry != null) {
            if (currentDistance > currentEntry.distance) {
                Entry<K, V> tempEntry = currentEntry;
                currentEntry = temp;
                temp = tempEntry;
            }
            Entry<K, V> tempEntry = table[index];
            table[index] = currentEntry;
            currentEntry = tempEntry;
            currentDistance++;
            index = (index + 1) % capacity;
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        Entry<K, V>[] oldTable = table;
        table = new Entry[newCapacity];
        capacity = newCapacity;
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;
        int distance;

        public Entry(K key, V value, int distance) {
            this.key = key;
            this.value = value;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        RobinHoodHashTable<String, Integer> hashTable = new RobinHoodHashTable<>();
        hashTable.put("John", 25);
        hashTable.put("Emily", 30);
        hashTable.put("Michael", 28);

        System.out.println("John's age: " + hashTable.get("John"));
        System.out.println("Emily's age: " + hashTable.get("Emily"));

        hashTable.remove("Michael");

        System.out.println("Size of hash table: " + hashTable.size());
        System.out.println("Does hash table contain 'Michael'? " + hashTable.containsKey("Michael"));
    }
}

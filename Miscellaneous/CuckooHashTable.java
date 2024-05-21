package Miscellaneous;

class CuckooHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ITERATIONS = 1000;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private Entry<K, V>[] table1;
    private Entry<K, V>[] table2;
    private int size;
    private int capacity;
    private HashFunction<K>[] hashFunctions;

    public CuckooHashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CuckooHashTable(int capacity) {
        this.capacity = capacity;
        table1 = new Entry[capacity];
        table2 = new Entry[capacity];
        size = 0;
        hashFunctions = new HashFunction[] { new HashFunction1(), new HashFunction2() };
    }

    public void put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index1 = hashFunctions[0].hash(key) % capacity;
        int index2 = hashFunctions[1].hash(key) % capacity;

        Entry<K, V> entry = new Entry<>(key, value);

        if (table1[index1] == null) {
            table1[index1] = entry;
            size++;
        } else if (table1[index1].key.equals(key)) {
            table1[index1].value = value;
            return;
        } else if (table2[index2] == null) {
            table2[index2] = entry;
            size++;
        } else if (table2[index2].key.equals(key)) {
            table2[index2].value = value;
            return;
        } else {
            // Evict and rehash
            Entry<K, V> evictedEntry = entry;
            int evictIndex1 = index1;
            int evictIndex2 = index2;

            for (int i = 0; i < MAX_ITERATIONS; i++) {
                Entry<K, V> temp;
                if (i % 2 == 0) {
                    temp = table1[evictIndex1];
                    table1[evictIndex1] = evictedEntry;
                    evictedEntry = temp;
                    evictIndex2 = hashFunctions[1].hash(evictedEntry.key) % capacity;
                } else {
                    temp = table2[evictIndex2];
                    table2[evictIndex2] = evictedEntry;
                    evictedEntry = temp;
                    evictIndex1 = hashFunctions[0].hash(evictedEntry.key) % capacity;
                }

                if (evictedEntry == null) {
                    size++;
                    return;
                }

                if (evictedEntry.key.equals(key)) {
                    evictedEntry.value = value;
                    return;
                }
            }
            // Resize if maximum iterations reached without success
            resize();
            put(evictedEntry.key, evictedEntry.value);
        }
    }

    public V get(K key) {
        int index1 = hashFunctions[0].hash(key) % capacity;
        int index2 = hashFunctions[1].hash(key) % capacity;

        if (table1[index1] != null && table1[index1].key.equals(key)) {
            return table1[index1].value;
        } else if (table2[index2] != null && table2[index2].key.equals(key)) {
            return table2[index2].value;
        } else {
            return null;
        }
    }

    public void remove(K key) {
        int index1 = hashFunctions[0].hash(key) % capacity;
        int index2 = hashFunctions[1].hash(key) % capacity;

        if (table1[index1] != null && table1[index1].key.equals(key)) {
            table1[index1] = null;
            size--;
        } else if (table2[index2] != null && table2[index2].key.equals(key)) {
            table2[index2] = null;
            size--;
        }
    }

    public boolean containsKey(K key) {
        int index1 = hashFunctions[0].hash(key) % capacity;
        int index2 = hashFunctions[1].hash(key) % capacity;

        return table1[index1] != null && table1[index1].key.equals(key) ||
                table2[index2] != null && table2[index2].key.equals(key);
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Entry<K, V>[] oldTable1 = table1;
        Entry<K, V>[] oldTable2 = table2;

        table1 = new Entry[newCapacity];
        table2 = new Entry[newCapacity];
        capacity = newCapacity;
        size = 0;

        for (Entry<K, V> entry : oldTable1) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }

        for (Entry<K, V> entry : oldTable2) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    private interface HashFunction<K> {
        int hash(K key);
    }

    private class HashFunction1 implements HashFunction<K> {
        @Override
        public int hash(K key) {
            return Math.abs(key.hashCode());
        }
    }

    private class HashFunction2 implements HashFunction<K> {
        @Override
        public int hash(K key) {
            return Math.abs(~key.hashCode());
        }
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
        CuckooHashTable<String, Integer> cuckooHashTable = new CuckooHashTable<>();
        cuckooHashTable.put("John", 25);
        cuckooHashTable.put("Emily", 30);
        cuckooHashTable.put("Michael", 28);

        System.out.println("John's age: " + cuckooHashTable.get("John"));
        System.out.println("Emily's age: " + cuckooHashTable.get("Emily"));

        cuckooHashTable.remove("Michael");

        System.out.println("Size of hash table: " + cuckooHashTable.size());
        System.out.println("Does hash table contain 'Michael'? " + cuckooHashTable.containsKey("Michael"));
    }
}
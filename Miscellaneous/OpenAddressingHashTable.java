package Miscellaneous;

class OpenAddressingHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private Entry<K, V>[] table;
    private int size;
    private int capacity;

    public OpenAddressingHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public OpenAddressingHashTable(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        if (size >= capacity) {
            throw new IllegalStateException("Hash table is full");
        }
        int index = hash(key);
        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % capacity;
        }
        if (table[index] != null && table[index].key.equals(key)) {
            table[index].value = value;
        } else {
            table[index] = new Entry<>(key, value);
            size++;
        }
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
        int startIndex = index;
        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % capacity;
            if (index == startIndex) {
                return -1; // Key not found
            }
        }
        if (table[index] != null && table[index].key.equals(key)) {
            return index;
        }
        return -1; // Key not found
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
        OpenAddressingHashTable<String, Integer> hashTable = new OpenAddressingHashTable<>();
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

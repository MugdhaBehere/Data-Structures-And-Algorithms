package Miscellaneous;

import java.util.BitSet;
import java.util.function.Function;

public class BloomFilter<T> {
    private BitSet bitSet;
    private int size;
    private Function<T, Integer>[] hashFunctions;

    public BloomFilter(int size, Function<T, Integer>[] hashFunctions) {
        this.size = size;
        bitSet = new BitSet(size);
        this.hashFunctions = hashFunctions;
    }

    public void add(T item) {
        for (Function<T, Integer> hashFunction : hashFunctions) {
            int index = Math.abs(hashFunction.apply(item) % size); // Ensure non-negative index
            bitSet.set(index, true);
        }
    }

    public boolean contains(T item) {
        for (Function<T, Integer> hashFunction : hashFunctions) {
            int index = Math.abs(hashFunction.apply(item) % size); // Ensure non-negative index
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Function<String, Integer>[] hashFunctions = new Function[2];
        hashFunctions[0] = s -> Math.abs(s.hashCode() % 10); // Ensure non-negative hash value
        hashFunctions[1] = s -> Math.abs((s.hashCode() * 31) % 10); // Ensure non-negative hash value

        BloomFilter<String> bloomFilter = new BloomFilter<>(10, hashFunctions);

        // Add elements to the filter
        bloomFilter.add("hello");
        bloomFilter.add("world");

        // Check if an element is likely in the set
        System.out.println(bloomFilter.contains("hello")); // Output: true
        System.out.println(bloomFilter.contains("world")); // Output: true
        System.out.println(bloomFilter.contains("foo")); // Output: false (might be a false positive)
    }
}

package Algorithms.Sorting;


import java.util.*;

public class BucketSort {

    public static void bucketSort(float[] arr) {
        int n = arr.length;

        // Create empty buckets
        List<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Distribute elements into buckets
        for (float num : arr) {
            int bucketIndex = (int) (num * n);
            buckets[bucketIndex].add(num);
        }

        // Sort each bucket
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Concatenate buckets back into the original array
        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        float[] arr = { 0.42f, 0.32f, 0.33f, 0.52f, 0.37f, 0.47f, 0.51f };

        System.out.println("Array before sorting: " + Arrays.toString(arr));

        bucketSort(arr);

        System.out.println("Array after sorting: " + Arrays.toString(arr));
    }
}

package Algorithms.Sorting;

import java.util.Arrays;

public class CountingSort {

    public static void countingSort(int[] arr) {
        int n = arr.length;

        // Find the maximum and minimum elements in the array
        int max = Arrays.stream(arr).max().orElse(0);
        int min = Arrays.stream(arr).min().orElse(0);

        // Create a counting array to store the count of each element
        int[] count = new int[max - min + 1];

        // Store the count of each element in the counting array
        for (int num : arr) {
            count[num - min]++;
        }

        // Modify the count array to store the position of each element in the output
        // array
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Create the output array
        int[] output = new int[n];

        // Build the output array using the counting array
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copy the output array to the original array
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] arr = { 4, 2, 2, 8, 3, 3, 1 };

        System.out.println("Array before sorting: " + Arrays.toString(arr));

        countingSort(arr);

        System.out.println("Array after sorting: " + Arrays.toString(arr));
    }
}

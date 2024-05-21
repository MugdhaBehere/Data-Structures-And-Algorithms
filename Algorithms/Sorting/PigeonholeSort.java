package Algorithms.Sorting;

import java.util.Arrays;

public class PigeonholeSort {

    public static void pigeonholeSort(int[] arr) {
        int min = Arrays.stream(arr).min().orElse(0);
        int max = Arrays.stream(arr).max().orElse(0);
        int range = max - min + 1;

        // Create an array to store counts of occurrences
        int[] pigeonholes = new int[range];

        // Traverse through the input array and increment the count of occurrences in
        // the pigeonholes array
        for (int num : arr) {
            pigeonholes[num - min]++;
        }

        // Traverse through the pigeonholes array and copy elements back to the original
        // array in sorted order
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (pigeonholes[i] > 0) {
                arr[index++] = i + min;
                pigeonholes[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 8, 3, 2, 7, 4, 6, 8 };

        System.out.println("Array before sorting: " + Arrays.toString(arr));

        pigeonholeSort(arr);

        System.out.println("Array after sorting: " + Arrays.toString(arr));
    }
}

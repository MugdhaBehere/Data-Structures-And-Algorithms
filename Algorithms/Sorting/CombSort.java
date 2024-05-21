package Algorithms.Sorting;

import java.util.Arrays;

public class CombSort {

    public static void combSort(int[] arr) {
        int n = arr.length;

        // Initialize gap
        int gap = n;

        // Initialize swapped flag
        boolean swapped = true;

        // Reduce gap by a factor of 1.3 in each iteration until gap becomes 1
        while (gap != 1 || swapped) {
            // Find the next gap
            gap = getNextGap(gap);

            // Initialize swapped as false so that we can check if any two elements were
            // swapped
            swapped = false;

            // Compare all elements with current gap
            for (int i = 0; i < n - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    // Swap arr[i] and arr[i+gap]
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    swapped = true;
                }
            }
        }
    }

    public static int getNextGap(int gap) {
        // Shrink gap by a factor of 1.3
        gap = (gap * 10) / 13;
        if (gap < 1) {
            return 1;
        }
        return gap;
    }

    public static void main(String[] args) {
        int[] arr = { 64, 34, 25, 12, 22, 11, 90 };

        System.out.println("Array before sorting: " + Arrays.toString(arr));

        combSort(arr);

        System.out.println("Array after sorting: " + Arrays.toString(arr));
    }
}

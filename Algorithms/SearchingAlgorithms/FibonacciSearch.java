package Algorithms.SearchingAlgorithms;

import java.util.Arrays;

public class FibonacciSearch {

    public static int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;
        int fibMMinus2 = 0; // (m-2)'th Fibonacci number
        int fibMMinus1 = 1; // (m-1)'th Fibonacci number
        int fibM = fibMMinus1 + fibMMinus2; // m'th Fibonacci number

        // Find the smallest Fibonacci number greater than or equal to n
        while (fibM < n) {
            fibMMinus2 = fibMMinus1;
            fibMMinus1 = fibM;
            fibM = fibMMinus1 + fibMMinus2;
        }

        // Marks the eliminated range from front
        int offset = -1;

        // while there are elements to be inspected
        // Note that we compare arr[fibMMinus2] with target.
        // When fibM becomes 1, fibMMinus2 becomes 0
        while (fibM > 1) {
            // Check if fibMMinus2 is a valid location
            int i = Math.min(offset + fibMMinus2, n - 1);

            // If target is greater than the value at index fibMMinus2,
            // cut the subarray array from offset to i
            if (arr[i] < target) {
                fibM = fibMMinus1;
                fibMMinus1 = fibMMinus2;
                fibMMinus2 = fibM - fibMMinus1;
                offset = i;
            }

            // If target is less than the value at index fibMMinus2,
            // cut the subarray after i+1
            else if (arr[i] > target) {
                fibM = fibMMinus2;
                fibMMinus1 = fibMMinus1 - fibMMinus2;
                fibMMinus2 = fibM - fibMMinus1;
            }

            // If target is found
            else {
                return i;
            }
        }

        // Compare the last element with target
        if (fibMMinus1 == 1 && arr[offset + 1] == target) {
            return offset + 1;
        }

        // Target not found
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100 };
        int target = 45;

        int index = fibonacciSearch(arr, target);

        if (index != -1) {
            System.out.println("Target element " + target + " found at index: " + index);
        } else {
            System.out.println("Target element " + target + " not found in the array.");
        }
    }
}

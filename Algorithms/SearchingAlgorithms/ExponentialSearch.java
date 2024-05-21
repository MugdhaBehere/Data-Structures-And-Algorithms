package Algorithms.SearchingAlgorithms;

public class ExponentialSearch {

    public static int exponentialSearch(int[] arr, int target) {
        int n = arr.length;
        if (arr[0] == target) {
            return 0; // Target found at index 0
        }

        // Find range for binary search by doubling the index
        int i = 1;
        while (i < n && arr[i] <= target) {
            i *= 2;
        }

        // Perform binary search in the range [i/2, min(i, n-1)]
        return binarySearch(arr, target, i / 2, Math.min(i, n - 1));
    }

    public static int binarySearch(int[] arr, int target, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                low = mid + 1; // Search in the right half
            } else {
                high = mid - 1; // Search in the left half
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 4, 10, 40, 60, 80, 100 };
        int target = 10;

        int index = exponentialSearch(arr, target);

        if (index != -1) {
            System.out.println("Target element " + target + " found at index: " + index);
        } else {
            System.out.println("Target element " + target + " not found in the array.");
        }
    }
}

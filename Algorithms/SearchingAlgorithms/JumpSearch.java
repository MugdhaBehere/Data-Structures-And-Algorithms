package Algorithms.SearchingAlgorithms;

public class JumpSearch {

    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int blockSize = (int) Math.sqrt(n);
        int step = blockSize;
        int prev = 0;

        // Find the block where the target might lie
        while (arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += blockSize;
            if (prev >= n) {
                return -1; // Target not found if we exceed the array size
            }
        }

        // Perform linear search in the block
        while (arr[prev] < target) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1; // Target not found if we reach the end of the block
            }
        }

        // If the target is found
        if (arr[prev] == target) {
            return prev;
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 };
        int target = 13;

        int index = jumpSearch(arr, target);

        if (index != -1) {
            System.out.println("Target element " + target + " found at index: " + index);
        } else {
            System.out.println("Target element " + target + " not found in the array.");
        }
    }
}


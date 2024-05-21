package Algorithms.SearchingAlgorithms;

public class InterpolationSearch {

    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {
            // Calculate the position using interpolation formula
            int pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low]);

            if (arr[pos] == target) {
                return pos; // Target found
            } else if (arr[pos] < target) {
                low = pos + 1; // Search in the right half
            } else {
                high = pos - 1; // Search in the left half
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        int target = 60;

        int index = interpolationSearch(arr, target);

        if (index != -1) {
            System.out.println("Target element " + target + " found at index: " + index);
        } else {
            System.out.println("Target element " + target + " not found in the array.");
        }
    }
}

package Algorithms.SearchingAlgorithms;


public class LinearSearch {

    public static int linearSearch(int[] arr, int target) {
        // Iterate through the array and compare each element with the target
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return the index of the target element if found
            }
        }
        return -1; // Return -1 if the target element is not found
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 2, 9, 4, 1 };
        int target = 9;

        int index = linearSearch(arr, target);

        if (index != -1) {
            System.out.println("Target element " + target + " found at index: " + index);
        } else {
            System.out.println("Target element " + target + " not found in the array.");
        }
    }
}

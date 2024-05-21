package Algorithms.StringMatching;

import java.util.*;

public class SuffixArray {
    private String text;
    private Integer[] suffixArray;

    public SuffixArray(String text) {
        this.text = text;
        this.suffixArray = buildSuffixArray(text);
    }

    private Integer[] buildSuffixArray(String text) {
        int n = text.length();
        Integer[] suffixArray = new Integer[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = i;
        }

        Arrays.sort(suffixArray, Comparator.comparing(s -> text.substring(s)));

        return suffixArray;
    }

    public List<Integer> search(String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = text.substring(suffixArray[mid], Math.min(suffixArray[mid] + m, n)).compareTo(pattern);
            if (cmp == 0) {
                occurrences.add(suffixArray[mid]);
                break;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return occurrences;
    }

    public static void main(String[] args) {
        String text = "banana";
        SuffixArray suffixArray = new SuffixArray(text);
        String pattern = "ana";
        List<Integer> occurrences = suffixArray.search(pattern);
        System.out.println("Occurrences found at positions: " + occurrences);
    }
}

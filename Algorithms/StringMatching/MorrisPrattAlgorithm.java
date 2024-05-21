package Algorithms.StringMatching;

import java.util.ArrayList;
import java.util.List;

public class MorrisPrattAlgorithm {
    public static List<Integer> search(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int[] prefixArray = computePrefixArray(pattern);
        int m = pattern.length();
        int n = text.length();
        int i = 0, j = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    occurrences.add(i - j);
                    j = prefixArray[j - 1];
                }
            } else {
                if (j != 0) {
                    j = prefixArray[j - 1];
                } else {
                    i++;
                }
            }
        }
        return occurrences;
    }

    private static int[] computePrefixArray(String pattern) {
        int m = pattern.length();
        int[] prefixArray = new int[m];
        int len = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                prefixArray[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = prefixArray[len - 1];
                } else {
                    prefixArray[i] = len;
                    i++;
                }
            }
        }
        return prefixArray;
    }

    public static void main(String[] args) {
        String text = "ababcababcababc";
        String pattern = "ababc";
        List<Integer> occurrences = search(text, pattern);
        System.out.println("Occurrences found at positions: " + occurrences); // Output: Occurrences found at positions:
                                                                              // [0, 5, 10]
    }
}

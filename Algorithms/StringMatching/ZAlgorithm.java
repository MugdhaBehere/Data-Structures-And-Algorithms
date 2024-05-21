package Algorithms.StringMatching;

import java.util.*;

public class ZAlgorithm {
    public static List<Integer> search(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        String concat = pattern + "$" + text;
        int[] zArray = calculateZArray(concat);

        for (int i = 0; i < zArray.length; i++) {
            if (zArray[i] == pattern.length()) {
                occurrences.add(i - pattern.length() - 1);
            }
        }

        return occurrences;
    }

    private static int[] calculateZArray(String str) {
        int n = str.length();
        int[] zArray = new int[n];
        int left = 0, right = 0;

        for (int i = 1; i < n; i++) {
            if (i > right) {
                left = right = i;
                while (right < n && str.charAt(right - left) == str.charAt(right)) {
                    right++;
                }
                zArray[i] = right - left;
                right--;
            } else {
                int k = i - left;
                if (zArray[k] < right - i + 1) {
                    zArray[i] = zArray[k];
                } else {
                    left = i;
                    while (right < n && str.charAt(right - left) == str.charAt(right)) {
                        right++;
                    }
                    zArray[i] = right - left;
                    right--;
                }
            }
        }

        return zArray;
    }

    public static void main(String[] args) {
        String text = "abcbglx";
        String pattern = "bgl";
        List<Integer> occurrences = search(text, pattern);
        System.out.println("Occurrences found at positions: " + occurrences);
    }
}

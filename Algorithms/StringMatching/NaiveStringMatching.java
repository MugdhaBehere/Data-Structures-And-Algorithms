package Algorithms.StringMatching;

import java.util.ArrayList;
import java.util.List;

public class NaiveStringMatching {
    public static List<Integer> naiveStringMatching(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        List<Integer> occurrences = new ArrayList<>();

        for (int i = 0; i <= n - m; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                occurrences.add(i);
            }
        }

        return occurrences;
    }

    public static void main(String[] args) {
        String text = "ababcababcabc";
        String pattern = "abc";
        List<Integer> occurrences = naiveStringMatching(text, pattern);
        System.out.println("Occurrences found at positions: " + occurrences);
    }
}

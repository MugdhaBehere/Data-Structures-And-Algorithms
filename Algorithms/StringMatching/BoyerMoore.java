package Algorithms.StringMatching;

import java.util.ArrayList;
import java.util.List;

public class BoyerMoore {
    private static int NO_OF_CHARS = 256;

    private static int[] badCharHeuristic(String pattern) {
        int[] badChar = new int[NO_OF_CHARS];
        int m = pattern.length();

        for (int i = 0; i < NO_OF_CHARS; i++)
            badChar[i] = -1;

        for (int i = 0; i < m; i++)
            badChar[(int) pattern.charAt(i)] = i;

        return badChar;
    }

    public static List<Integer> boyerMooreSearch(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int[] badChar = badCharHeuristic(pattern);

        int s = 0; // s is shift of the pattern with respect to text
        while (s <= (n - m)) {
            int j = m - 1;
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j))
                j--;

            if (j < 0) { // Pattern found
                occurrences.add(s);
                if (s + m < n)
                    s += m - badChar[text.charAt(s + m)];
                else
                    s += 1;
            } else {
                s += Math.max(1, j - badChar[text.charAt(s + j)]);
            }
        }

        return occurrences;
    }

    public static void main(String[] args) {
        String text = "ababcababcabc";
        String pattern = "abc";
        List<Integer> occurrences = boyerMooreSearch(text, pattern);
        System.out.println("Occurrences found at positions: " + occurrences);
    }
}

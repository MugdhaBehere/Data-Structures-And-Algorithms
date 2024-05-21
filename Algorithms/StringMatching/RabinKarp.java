package Algorithms.StringMatching;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {
    private static final int d = 256; // Number of characters in the input alphabet
    private static final int prime = 101; // A prime number for hashing

    public static List<Integer> rabinKarpSearch(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int i, j;
        int patternHash = 0; // Hash value for pattern
        int textHash = 0; // Hash value for text
        int h = 1;

        // Calculate hash value of pattern and first window of text
        for (i = 0; i < m - 1; i++)
            h = (h * d) % prime;

        for (i = 0; i < m; i++) {
            patternHash = (d * patternHash + pattern.charAt(i)) % prime;
            textHash = (d * textHash + text.charAt(i)) % prime;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= n - m; i++) {
            // Check the hash values of current window of text and pattern.
            // If the hash values match, then only check for characters one by one
            if (patternHash == textHash) {
                // Check for characters one by one
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                // If patternHash == textHash and pattern[0...m-1] = text[i, i+m-1], then match
                // found
                if (j == m)
                    occurrences.add(i);
            }
            // Calculate hash value for next window of text: Remove leading digit, add
            // trailing digit
            if (i < n - m) {
                textHash = (d * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % prime;
                // We might get negative value of t, converting it to positive
                if (textHash < 0)
                    textHash += prime;
            }
        }
        return occurrences;
    }

    public static void main(String[] args) {
        String text = "ababcababcabc";
        String pattern = "abc";
        List<Integer> occurrences = rabinKarpSearch(text, pattern);
        System.out.println("Occurrences found at positions: " + occurrences);
    }
}

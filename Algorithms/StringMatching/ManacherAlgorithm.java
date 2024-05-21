package Algorithms.StringMatching;

import java.util.*;

public class ManacherAlgorithm {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String modifiedString = preprocess(s);
        int[] palindromeLengths = new int[modifiedString.length()];

        int center = 0, right = 0;
        for (int i = 1; i < modifiedString.length() - 1; i++) {
            int mirror = 2 * center - i;
            if (i < right) {
                palindromeLengths[i] = Math.min(right - i, palindromeLengths[mirror]);
            }

            while (modifiedString.charAt(i + (1 + palindromeLengths[i])) == modifiedString
                    .charAt(i - (1 + palindromeLengths[i]))) {
                palindromeLengths[i]++;
            }

            if (i + palindromeLengths[i] > right) {
                center = i;
                right = i + palindromeLengths[i];
            }
        }

        int maxPalindromeLength = 0;
        int centerIndex = 0;
        for (int i = 1; i < modifiedString.length() - 1; i++) {
            if (palindromeLengths[i] > maxPalindromeLength) {
                maxPalindromeLength = palindromeLengths[i];
                centerIndex = i;
            }
        }

        int startIndex = (centerIndex - 1 - maxPalindromeLength) / 2;
        return s.substring(startIndex, startIndex + maxPalindromeLength);
    }

    private static String preprocess(String s) {
        StringBuilder sb = new StringBuilder("^");
        for (int i = 0; i < s.length(); i++) {
            sb.append("#").append(s.charAt(i));
        }
        sb.append("#$");
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println("Longest palindromic substring: " + longestPalindrome(s)); // Output: "bab" or "aba"
    }
}

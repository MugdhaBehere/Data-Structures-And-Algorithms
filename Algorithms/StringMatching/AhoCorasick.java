package Algorithms.StringMatching;

import java.util.*;


class Solution {

    // maximum number os states in the machine = sum of length of all keywords
    static int MAXS = 500;
    // maximum number of characters = 26
    static int MAXC = 26;

    // function to print is implemented using out[]
    static int[] out = new int[MAXS];

    // function to show failure is implmeneted using f[]
    static int[] f = new int[MAXS];

    static int[][] g = new int[MAXS][MAXC];

    static int buildMatchingMachine(String arr[], int k) {

        // initializing all array values as 0
        Arrays.fill(out, 0);

        // initializing all values in goto function as -1
        for (int i = 0; i < MAXS; i++)
            Arrays.fill(g[i], -1);
        int states = 1;
        // Building trie for arr[]
        for (int i = 0; i < k; ++i) {
            String word = arr[i];
            int currentState = 0;
            for (int j = 0; j < word.length(); ++j) {
                int ch = word.charAt(j) - 'a';
                if (g[currentState][ch] == -1)
                    g[currentState][ch] = states++;

                currentState = g[currentState][ch];
            }
            // add current word
            out[currentState] |= (1 << i);
        }
        for (int ch = 0; ch < MAXC; ++ch)
            if (g[0][ch] == -1)
                g[0][ch] = 0;

        // building the failure function
        Arrays.fill(f, -1);
        Queue q = new LinkedList<>();

        for (int ch = 0; ch < MAXC; ++ch) {
            if (g[0][ch] != 0) {
                f[g[0][ch]] = 0;
                q.add(g[0][ch]);
            }
        }
        while (!q.isEmpty()) {
            int state = (int) q.peek();
            q.remove();
            for (int ch = 0; ch < MAXC; ++ch) {
                if (g[state][ch] != -1) {
                    int failure = f[state];
                    while (g[failure][ch] == -1)
                        failure = f[failure];

                    failure = g[failure][ch];
                    f[g[state][ch]] = failure;
                    out[g[state][ch]] |= out[failure];
                    q.add(g[state][ch]);
                }
            }
        }
        return states;
    }

    static int findNextState(int currentState, char nextInput) {
        int answer = currentState;
        int ch = nextInput - 'a';
        while (g[answer][ch] == -1)
            answer = f[answer];

        return g[answer][ch];
    }

    // this function finds the occurrences of given text
    static void searchWords(String arr[], int k,
            String text) {
        buildMatchingMachine(arr, k);
        int currentState = 0;
        for (int i = 0; i < text.length(); ++i) {
            currentState = findNextState(currentState,
                    text.charAt(i));
            // if match not found, then move to next state
            if (out[currentState] == 0)
                continue;
            for (int j = 0; j < k; ++j) {
                if ((out[currentState] & (1 << j)) > 0) {
                    System.out.print("Word " + arr[j] + " appears from " + (i - arr[j].length() + 1) +
                            " to " + i + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        String arr[] = { "he", "she", "hers", "his" };
        String text = "ahishers";
        int k = arr.length;

        searchWords(arr, k, text);
    }
}
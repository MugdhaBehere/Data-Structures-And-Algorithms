package Algorithms.GreedyAlgorithms;

import java.util.Arrays;

public class CoinChange {

    public static int countWays(int[] coins, int amount) {
        Arrays.sort(coins);
        int index = coins.length - 1;
        int count = 0;

        while (amount > 0 && index >= 0) {
            if (coins[index] <= amount) {
                int numCoins = amount / coins[index];
                count += numCoins;
                amount -= numCoins * coins[index];
            }
            index--;
        }

        if (amount == 0) {
            return count;
        } else {
            return -1; // Change not possible
        }
    }

    public static void main(String[] args) {
        int[] coins = { 1, 2, 5 };
        int amount = 11;

        int ways = countWays(coins, amount);
        if (ways != -1) {
            System.out.println("Number of ways to make change for " + amount + " cents: " + ways);
        } else {
            System.out.println("Change not possible for " + amount + " cents with given denominations.");
        }
    }
}

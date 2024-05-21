package Algorithms.DivideAndConquer;

import java.math.BigInteger;

public class KaratsubaMultiplication {

    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        int n = Math.max(x.bitLength(), y.bitLength());

        // Base case: if the number of bits is less than or equal to 32, use regular
        // multiplication
        if (n <= 32) {
            return x.multiply(y);
        }

        // Split the input numbers into halves
        int half = (n + 1) / 2;
        BigInteger high1 = x.shiftRight(half);
        BigInteger low1 = x.subtract(high1.shiftLeft(half));
        BigInteger high2 = y.shiftRight(half);
        BigInteger low2 = y.subtract(high2.shiftLeft(half));

        // Recursively compute the three products
        BigInteger z0 = karatsuba(low1, low2);
        BigInteger z1 = karatsuba(low1.add(high1), low2.add(high2));
        BigInteger z2 = karatsuba(high1, high2);

        // Use the formula: result = (z2 * B^2) + ((z1 - z2 - z0) * B) + z0
        return z2.shiftLeft(2 * half).add(z1.subtract(z2).subtract(z0).shiftLeft(half)).add(z0);
    }

    public static void main(String[] args) {
        BigInteger x = new BigInteger("123456789012345678901234567890");
        BigInteger y = new BigInteger("987654321098765432109876543210");

        BigInteger result = karatsuba(x, y);
        System.out.println("Result: " + result);
    }
}

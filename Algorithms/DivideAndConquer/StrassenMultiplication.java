package Algorithms.DivideAndConquer;

public class StrassenMultiplication {

    public static int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;

        // Base case: if the matrix size is 1x1, do regular multiplication
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        // Divide matrices A and B into four submatrices
        int[][] A11 = new int[n / 2][n / 2];
        int[][] A12 = new int[n / 2][n / 2];
        int[][] A21 = new int[n / 2][n / 2];
        int[][] A22 = new int[n / 2][n / 2];
        int[][] B11 = new int[n / 2][n / 2];
        int[][] B12 = new int[n / 2][n / 2];
        int[][] B21 = new int[n / 2][n / 2];
        int[][] B22 = new int[n / 2][n / 2];

        // Split matrices A and B into submatrices
        split(A, A11, 0, 0);
        split(A, A12, 0, n / 2);
        split(A, A21, n / 2, 0);
        split(A, A22, n / 2, n / 2);
        split(B, B11, 0, 0);
        split(B, B12, 0, n / 2);
        split(B, B21, n / 2, 0);
        split(B, B22, n / 2, n / 2);

        // Calculate the seven products needed for Strassen's algorithm
        int[][] P1 = strassen(add(A11, A22), add(B11, B22));
        int[][] P2 = strassen(add(A21, A22), B11);
        int[][] P3 = strassen(A11, subtract(B12, B22));
        int[][] P4 = strassen(A22, subtract(B21, B11));
        int[][] P5 = strassen(add(A11, A12), B22);
        int[][] P6 = strassen(subtract(A21, A11), add(B11, B12));
        int[][] P7 = strassen(subtract(A12, A22), add(B21, B22));

        // Calculate the resulting submatrices
        int[][] C11 = subtract(add(add(P1, P4), P7), P5);
        int[][] C12 = add(P3, P5);
        int[][] C21 = add(P2, P4);
        int[][] C22 = subtract(add(add(P1, P3), P6), P2);

        // Combine the resulting submatrices into the resulting matrix
        int[][] C = new int[n][n];
        join(C11, C, 0, 0);
        join(C12, C, 0, n / 2);
        join(C21, C, n / 2, 0);
        join(C22, C, n / 2, n / 2);

        return C;
    }

    // Utility methods for matrix manipulation

    public static void split(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    public static void join(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }

    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    public static void main(String[] args) {
        int[][] A = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        int[][] B = { { 17, 18, 19, 20 }, { 21, 22, 23, 24 }, { 25, 26, 27, 28 }, { 29, 30, 31, 32 } };

        int[][] C = strassen(A, B);

        System.out.println("Result:");
        printMatrix(C);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}

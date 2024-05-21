package Trees;

class FenwickTree {
    private int[] tree;
    private int n;

    public FenwickTree(int size) {
        this.n = size;
        tree = new int[n + 1];
    }

    // Function to update the tree with value at index
    public void update(int index, int value) {
        index++;
        while (index <= n) {
            tree[index] += value;
            index += index & -index;
        }
    }

    // Function to get the prefix sum up to index
    public int query(int index) {
        int sum = 0;
        index++;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 7, 3, 0, 7, 8, 3, 2, 6, 2 };
        FenwickTree fenwickTree = new FenwickTree(arr.length);

        for (int i = 0; i < arr.length; i++) {
            fenwickTree.update(i, arr[i]);
        }

        // Query the sum of first 5 elements
        System.out.println(fenwickTree.query(4)); // Outputs: 18 (1 + 7 + 3 + 0 + 7)

        // Update the 3rd element (index 2) to 5
        fenwickTree.update(2, 2); // Adds 2 to the 3rd element (3 -> 5)

        // Query again the sum of first 5 elements
        System.out.println(fenwickTree.query(4)); // Outputs: 20
    }
}

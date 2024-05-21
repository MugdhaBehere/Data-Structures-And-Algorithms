package Miscellaneous;

public class WeightedQuickUnion {
    private int[] parent;
    private int[] size;

    public WeightedQuickUnion(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1; // Initialize the size of each tree to 1
        }
    }

    private int findRoot(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // Path compression
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ)
            return;

        // Attach the smaller tree to the root of the larger tree
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnion uf = new WeightedQuickUnion(10); // Create a WeightedQuickUnion object for 10 elements

        // Example usage
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);

        // Check if elements are connected
        System.out.println("Are 1 and 2 connected? " + uf.connected(1, 2)); // Output: false
        System.out.println("Are 3 and 4 connected? " + uf.connected(3, 4)); // Output: false

        // Perform union operation
        uf.union(1, 9);

        // Check again after union operation
        System.out.println("Are 1 and 9 connected? " + uf.connected(1, 9)); // Output: true
        System.out.println("Are 0 and 9 connected? " + uf.connected(0, 9)); // Output: true
    }
}

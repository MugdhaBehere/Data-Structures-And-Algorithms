package Miscellaneous;

public class WeightedUnionDisjointSet {
    private int[] parent;
    private int[] size;

    public WeightedUnionDisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Each element initially belongs to its own set
            size[i] = 1; // Initialize the size of each set to 1
        }
    }

    private int findRoot(int p) {
        while (p != parent[p]) {
            p = parent[p]; // Path compression
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

        // Union by size: attach the root of the smaller tree to the root of the larger
        // tree
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    public static void main(String[] args) {
        WeightedUnionDisjointSet ds = new WeightedUnionDisjointSet(10); // Create a WeightedUnionDisjointSet object for
                                                                        // 10 elements

        // Example usage
        ds.union(0, 1);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(8, 9);

        // Check if elements are connected
        System.out.println("Are 1 and 2 connected? " + ds.connected(1, 2)); // Output: false
        System.out.println("Are 3 and 4 connected? " + ds.connected(3, 4)); // Output: false

        // Perform union operation
        ds.union(1, 9);

        // Check again after union operation
        System.out.println("Are 1 and 9 connected? " + ds.connected(1, 9)); // Output: true
        System.out.println("Are 0 and 9 connected? " + ds.connected(0, 9)); // Output: true
    }
}

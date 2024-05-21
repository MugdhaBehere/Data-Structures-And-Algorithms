package Miscellaneous;

public class RankCompressedDisjointSet {
    private int[] parent;
    private int[] rank;

    public RankCompressedDisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Each element initially belongs to its own set
            rank[i] = 0; // Initialize the rank of each set to 0
        }
    }

    private int findRoot(int p) {
        if (parent[p] != p) {
            parent[p] = findRoot(parent[p]); // Path compression
        }
        return parent[p];
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ)
            return;

        // Union by rank: attach the root of the smaller tree to the root of the larger
        // tree
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
    }

    public static void main(String[] args) {
        RankCompressedDisjointSet ds = new RankCompressedDisjointSet(10); // Create a RankCompressedDisjointSet object
                                                                          // for 10 elements

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

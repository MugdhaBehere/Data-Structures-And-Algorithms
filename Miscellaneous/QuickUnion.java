package Miscellaneous;

public class QuickUnion {
    private int[] parent;

    public QuickUnion(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i; // Each element is initially its own parent
        }
    }

    private int findRoot(int p) {
        while (p != parent[p]) {
            p = parent[p]; // Move up the tree until reaching the root
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q); // Check if p and q have the same root
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP != rootQ) {
            parent[rootP] = rootQ; // Set the root of p's tree to be the root of q's tree
        }
    }

    public static void main(String[] args) {
        QuickUnion uf = new QuickUnion(10); // Create a QuickUnion object for 10 elements

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

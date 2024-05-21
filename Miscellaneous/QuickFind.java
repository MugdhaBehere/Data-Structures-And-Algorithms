package Miscellaneous;

public class QuickFind {
    private int[] id;

    public QuickFind(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i; // Each element initially belongs to its own group
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q]; // Check if elements p and q belong to the same group
    }

    public void union(int p, int q) {
        int pid = id[p]; // Group id of element p
        int qid = id[q]; // Group id of element q
        if (pid != qid) {
            // Update group id of all elements in group pid to qid
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pid) {
                    id[i] = qid;
                }
            }
        }
    }

    public static void main(String[] args) {
        QuickFind uf = new QuickFind(10); // Create a QuickFind object for 10 elements

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

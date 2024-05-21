package Miscellaneous;

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
    
    public static void main(String[] args) {
        int n = 6; // Number of elements
        UnionFind uf = new UnionFind(n);

        // Example usage
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);

        // Check if two elements are in the same set
        System.out.println("Are 0 and 2 in the same set? " + (uf.find(0) == uf.find(2))); // Output: true
        System.out.println("Are 2 and 4 in the same set? " + (uf.find(2) == uf.find(4))); // Output: false

        // Union two sets
        uf.union(2, 4);

        System.out.println("Are 0 and 4 in the same set? " + (uf.find(0) == uf.find(4))); // Output: true
    }
}


   


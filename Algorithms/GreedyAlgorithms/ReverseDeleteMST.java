package Algorithms.GreedyAlgorithms;

import java.util.*;

class Edge {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class ReverseDeleteMST {

    public static List<Edge> reverseDeleteMST(List<Edge> edges, int numVertices) {
        // Sort edges by weight in descending order
        Collections.sort(edges, Comparator.comparingInt(edge -> -edge.weight));

        List<Edge> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(numVertices);

        for (Edge edge : edges) {
            // Check if removing this edge disconnects the graph
            uf.union(edge.source, edge.destination);
            if (!uf.isConnected()) {
                // If removing the edge disconnects the graph, add it back to MST
                uf.undoUnion();
                mst.add(edge);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        int numVertices = 4;

        List<Edge> mst = reverseDeleteMST(edges, numVertices);

        System.out.println("Edges in Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
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
                    int temp = rootX;
                    rootX = rootY;
                    rootY = temp;
                }
                parent[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) {
                    rank[rootX]++;
                }
            }
        }

        public boolean isConnected() {
            int root = find(0);
            for (int i = 1; i < parent.length; i++) {
                if (find(i) != root) {
                    return false;
                }
            }
            return true;
        }

        public void undoUnion() {
            // Not needed for this implementation
        }
    }
}

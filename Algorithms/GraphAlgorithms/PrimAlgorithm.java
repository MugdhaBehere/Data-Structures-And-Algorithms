package Algorithms.GraphAlgorithms;

import java.util.*;

public class PrimAlgorithm {

    static class Graph {
        private int V;
        private List<List<Edge>> adj;

        Graph(int v) {
            V = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++)
                adj.add(new ArrayList<>());
        }

        static class Edge {
            int dest, weight;

            Edge(int d, int w) {
                dest = d;
                weight = w;
            }
        }

        void addEdge(int src, int dest, int weight) {
            adj.get(src).add(new Edge(dest, weight));
            adj.get(dest).add(new Edge(src, weight));
        }

        List<Edge> primMST() {
            boolean[] inMST = new boolean[V];
            List<Edge> MST = new ArrayList<>();
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

            // Start from vertex 0
            pq.offer(new Edge(0, 0));

            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                int u = edge.dest;

                if (inMST[u])
                    continue;

                inMST[u] = true;

                for (Edge neighbor : adj.get(u)) {
                    int v = neighbor.dest;
                    int weight = neighbor.weight;
                    if (!inMST[v])
                        pq.offer(new Edge(v, weight));
                }

                MST.add(edge);
            }

            return MST;
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(5);

        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        List<Graph.Edge> MST = g.primMST();

        System.out.println("Minimum Spanning Tree Edges:");
        for (Graph.Edge edge : MST) {
            System.out.println(edge.dest + " - " + edge.weight);
        }
    }
}

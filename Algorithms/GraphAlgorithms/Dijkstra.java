package Algorithms.GraphAlgorithms;

import java.util.*;

public class Dijkstra {

    static class Graph {
        private int V;
        private List<List<Node>> adj;

        Graph(int v) {
            V = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; ++i)
                adj.add(new ArrayList<>());
        }

        static class Node {
            int dest;
            int weight;

            Node(int d, int w) {
                dest = d;
                weight = w;
            }
        }

        void addEdge(int src, int dest, int weight) {
            adj.get(src).add(new Node(dest, weight));
        }

        void dijkstra(int src) {
            PriorityQueue<Node> pq = new PriorityQueue<>(V, Comparator.comparingInt(node -> node.weight));
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            pq.add(new Node(src, 0));
            dist[src] = 0;

            while (!pq.isEmpty()) {
                int u = pq.poll().dest;

                for (Node neighbor : adj.get(u)) {
                    int v = neighbor.dest;
                    int weight = neighbor.weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.add(new Node(v, dist[v]));
                    }
                }
            }

            // Print the shortest distances from the source
            System.out.println("Shortest distances from vertex " + src + ":");
            for (int i = 0; i < V; ++i) {
                if (dist[i] != Integer.MAX_VALUE)
                    System.out.println("Vertex " + i + ": " + dist[i]);
                else
                    System.out.println("Vertex " + i + ": Not reachable");
            }
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(6);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 2);
        g.addEdge(1, 2, 5);
        g.addEdge(1, 3, 10);
        g.addEdge(2, 4, 3);
        g.addEdge(4, 3, 4);
        g.addEdge(3, 5, 11);

        int sourceVertex = 0;
        g.dijkstra(sourceVertex);
    }
}

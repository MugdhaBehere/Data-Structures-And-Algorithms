package Algorithms.GraphAlgorithms;

import java.util.*;

public class BellmanFord {

    static class Graph {
        private int V;
        private List<Edge> edges;

        Graph(int v) {
            V = v;
            edges = new ArrayList<>();
        }

        static class Edge {
            int src, dest, weight;

            Edge(int s, int d, int w) {
                src = s;
                dest = d;
                weight = w;
            }
        }

        void addEdge(int src, int dest, int weight) {
            edges.add(new Edge(src, dest, weight));
        }

        void bellmanFord(int src) {
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // Relax all edges V-1 times
            for (int i = 0; i < V - 1; ++i) {
                for (Edge edge : edges) {
                    int u = edge.src;
                    int v = edge.dest;
                    int weight = edge.weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                    }
                }
            }

            // Check for negative weight cycles
            for (Edge edge : edges) {
                int u = edge.src;
                int v = edge.dest;
                int weight = edge.weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
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
        Graph g = new Graph(5);

        g.addEdge(0, 1, 6);
        g.addEdge(0, 3, 7);
        g.addEdge(1, 2, 5);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, -4);
        g.addEdge(2, 1, -2);
        g.addEdge(3, 2, -3);
        g.addEdge(3, 4, 9);
        g.addEdge(4, 0, 2);
        g.addEdge(4, 2, 7);

        int sourceVertex = 0;
        g.bellmanFord(sourceVertex);
    }
}

package Algorithms.GraphAlgorithms;

import java.util.*;

public class JohnsonAlgorithm {

    static class Graph {
        private int V;
        private List<List<Node>> adj;
        
        Graph(int v) {
            V = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++)
                adj.add(new ArrayList<>());
        }
        
        static class Node {
            int dest, weight;
            
            Node(int d, int w) {
                dest = d;
                weight = w;
            }
        }
        
        void addEdge(int src, int dest, int weight) {
            while (src >= adj.size()) {
                adj.add(new ArrayList<>());
            }
            adj.get(src).add(new Node(dest, weight));
        }
        
        void bellmanFord(int src, int[] dist) {
            dist[src] = 0;
            for (int i = 0; i < V - 1; ++i) {
                for (int u = 0; u < V; ++u) {
                    for (Node neighbor : adj.get(u)) {
                        int v = neighbor.dest;
                        int weight = neighbor.weight;
                        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                            dist[v] = dist[u] + weight;
                        }
                    }
                }
            }
        }
        
        void reweight(int[] h) {
            for (int u = 0; u < V; ++u) {
                for (Node neighbor : adj.get(u)) {
                    int v = neighbor.dest;
                    int weight = neighbor.weight;
                    neighbor.weight = weight + h[u] - h[v];
                }
            }
        }
        
        void dijkstra(int src, int[] dist) {
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
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
        }
    }

    static void johnsonsAlgorithm(Graph graph) {
        int V = graph.V;
        
        // Step 1: Add a new vertex and connect it to all existing vertices with zero-weight edges
        for (int i = 0; i < V; ++i) {
            graph.addEdge(V, i, 0);
        }
        V++;
        
        // Step 2: Run Bellman-Ford algorithm to get shortest distances from new vertex to all other vertices
        int[] h = new int[V];
        Arrays.fill(h, 0);
        graph.bellmanFord(V - 1, h);
        
        // Step 3: Reweight the edges of the original graph
        graph.reweight(h);
        
        // Step 4: Run Dijkstra's algorithm from each vertex
        for (int u = 0; u < V - 1; ++u) {
            int[] dist = new int[V];
            graph.dijkstra(u, dist);
            
            // Adjust distances back
            for (int v = 0; v < V - 1; ++v) {
                if (dist[v] != Integer.MAX_VALUE)
                    dist[v] += h[v] - h[u];
            }
            
            // Print the shortest distances from vertex u
            System.out.println("Shortest distances from vertex " + u + ":");
            for (int v = 0; v < V - 1; ++v) {
                if (dist[v] != Integer.MAX_VALUE)
                    System.out.println("To vertex " + v + ": " + dist[v]);
                else
                    System.out.println("To vertex " + v + ": Not reachable");
            }
        }
    }

    public static void main(String[] args) {
        int V = 6; // Number of vertices
        Graph graph = new Graph(V);

        // Add edges
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        johnsonsAlgorithm(graph);
    }
}

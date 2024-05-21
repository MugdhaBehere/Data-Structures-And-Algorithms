package Algorithms.GraphAlgorithms;

import java.util.*;

public class KosarajuAlgorithm {

    static class Graph {
        private int V;
        private List<List<Integer>> adj;
        private List<List<Integer>> SCCs;

        Graph(int v) {
            V = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++)
                adj.add(new ArrayList<>());
            SCCs = new ArrayList<>();
        }

        void addEdge(int src, int dest) {
            adj.get(src).add(dest);
        }

        Graph transpose() {
            Graph transposedGraph = new Graph(V);
            for (int v = 0; v < V; v++) {
                for (int neighbor : adj.get(v)) {
                    transposedGraph.addEdge(neighbor, v);
                }
            }
            return transposedGraph;
        }

        void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;
            for (int neighbor : adj.get(v)) {
                if (!visited[neighbor])
                    fillOrder(neighbor, visited, stack);
            }
            stack.push(v);
        }

        void DFSUtil(int v, boolean[] visited, List<Integer> component) {
            visited[v] = true;
            component.add(v);
            for (int neighbor : adj.get(v)) {
                if (!visited[neighbor])
                    DFSUtil(neighbor, visited, component);
            }
        }

        List<List<Integer>> kosarajuSCC() {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++) {
                if (!visited[i])
                    fillOrder(i, visited, stack);
            }

            Graph transposedGraph = transpose();
            Arrays.fill(visited, false);

            while (!stack.isEmpty()) {
                int v = stack.pop();
                if (!visited[v]) {
                    List<Integer> component = new ArrayList<>();
                    transposedGraph.DFSUtil(v, visited, component);
                    SCCs.add(component);
                }
            }

            return SCCs;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(1, 3);
        g.addEdge(3, 4);

        List<List<Integer>> SCCs = g.kosarajuSCC();

        System.out.println("Strongly Connected Components:");
        for (List<Integer> scc : SCCs) {
            System.out.println(scc);
        }
    }
}

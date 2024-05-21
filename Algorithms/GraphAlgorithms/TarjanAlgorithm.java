package Algorithms.GraphAlgorithms;

import java.util.*;

public class TarjanAlgorithm {

    static class Graph {
        private int V;
        private List<List<Integer>> adj;
        private int time;
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

        List<List<Integer>> tarjanSCC() {
            boolean[] visited = new boolean[V];
            int[] disc = new int[V];
            int[] low = new int[V];
            Stack<Integer> stack = new Stack<>();
            SCCs.clear();

            // Initialize arrays
            Arrays.fill(disc, -1);
            Arrays.fill(low, -1);

            for (int i = 0; i < V; i++) {
                if (!visited[i])
                    tarjanSCCUtil(i, visited, disc, low, stack);
            }

            return SCCs;
        }

        void tarjanSCCUtil(int u, boolean[] visited, int[] disc, int[] low, Stack<Integer> stack) {
            visited[u] = true;
            disc[u] = low[u] = ++time;
            stack.push(u);

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    tarjanSCCUtil(v, visited, disc, low, stack);
                    low[u] = Math.min(low[u], low[v]);
                } else if (stack.contains(v)) {
                    low[u] = Math.min(low[u], disc[v]);
                }
            }

            if (low[u] == disc[u]) {
                List<Integer> scc = new ArrayList<>();
                while (true) {
                    int x = stack.pop();
                    scc.add(x);
                    if (x == u)
                        break;
                }
                SCCs.add(scc);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(3, 4);

        List<List<Integer>> SCCs = g.tarjanSCC();

        System.out.println("Strongly Connected Components:");
        for (List<Integer> scc : SCCs) {
            System.out.println(scc);
        }
    }
}

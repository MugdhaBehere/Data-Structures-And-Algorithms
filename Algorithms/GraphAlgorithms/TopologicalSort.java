package Algorithms.GraphAlgorithms;

import java.util.*;

public class TopologicalSort {

    static class Graph {
        private int V;
        private List<List<Integer>> adj;

        Graph(int v) {
            V = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++)
                adj.add(new ArrayList<>());
        }

        void addEdge(int src, int dest) {
            adj.get(src).add(dest);
        }

        void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;

            for (int neighbor : adj.get(v)) {
                if (!visited[neighbor])
                    topologicalSortUtil(neighbor, visited, stack);
            }

            stack.push(v);
        }

        void topologicalSort() {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[V];

            for (int i = 0; i < V; i++) {
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);
            }

            System.out.println("Topological Sorting:");
            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);

        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        g.topologicalSort();
    }
}

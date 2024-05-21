package Algorithms.GraphAlgorithms;

import java.util.*;

public class AStar {

    static class Node {
        int x, y; // Coordinates of the node
        int f, g, h; // f = g + h (total cost), g (cost from start), h (heuristic cost)
        Node parent; // Parent node

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.parent = null;
        }
    }

    static class Graph {
        private int[][] grid;
        private int rows, cols;

        Graph(int[][] grid) {
            this.grid = grid;
            this.rows = grid.length;
            this.cols = grid[0].length;
        }

        // Heuristic function (Manhattan distance)
        int heuristic(int x, int y, int goalX, int goalY) {
            return Math.abs(x - goalX) + Math.abs(y - goalY);
        }

        List<Node> aStar(int startX, int startY, int goalX, int goalY) {
            List<Node> path = new ArrayList<>();
            PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
            Set<Node> closedSet = new HashSet<>();
            Node[][] nodes = new Node[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    nodes[i][j] = new Node(i, j);
                }
            }

            Node startNode = nodes[startX][startY];
            Node goalNode = nodes[goalX][goalY];

            openSet.add(startNode);

            while (!openSet.isEmpty()) {
                Node current = openSet.poll();

                if (current == goalNode) {
                    while (current != null) {
                        path.add(current);
                        current = current.parent;
                    }
                    Collections.reverse(path);
                    return path;
                }

                closedSet.add(current);

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0)
                            continue;

                        int newX = current.x + i;
                        int newY = current.y + j;

                        if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] != 1) {
                            Node neighbor = nodes[newX][newY];
                            if (!closedSet.contains(neighbor)) {
                                int tentativeG = current.g + 1; // Assuming edge cost of 1
                                if (!openSet.contains(neighbor) || tentativeG < neighbor.g) {
                                    neighbor.parent = current;
                                    neighbor.g = tentativeG;
                                    neighbor.h = heuristic(newX, newY, goalX, goalY);
                                    neighbor.f = neighbor.g + neighbor.h;
                                    if (!openSet.contains(neighbor)) {
                                        openSet.add(neighbor);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return path; // No path found
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0 }
        };

        Graph graph = new Graph(grid);
        List<Node> path = graph.aStar(0, 0, 4, 4);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}

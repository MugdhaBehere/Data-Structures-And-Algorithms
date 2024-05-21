package Heaps;

import java.util.*;

class RankPairingNode {
    int key;
    int rank;
    List<RankPairingNode> children;

    public RankPairingNode(int key) {
        this.key = key;
        this.rank = 0;
        this.children = new ArrayList<>();
    }
}

class RankPairingHeap {
    private RankPairingNode root;

    public RankPairingHeap() {
        root = null;
    }

    public void insert(int key) {
        if (root == null) {
            root = new RankPairingNode(key);
        } else {
            root = merge(root, new RankPairingNode(key));
        }
    }

    private RankPairingNode merge(RankPairingNode node1, RankPairingNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        if (node1.key < node2.key) {
            node2.children.add(node1);
            node2.rank = Math.max(node2.rank, node1.rank + 1);
            return node2;
        } else {
            node1.children.add(node2);
            node1.rank = Math.max(node1.rank, node2.rank + 1);
            return node1;
        }
    }

    public int findMin() {
        if (root == null) {
            throw new NoSuchElementException("Heap is empty");
        }
        return root.key;
    }

    public int extractMin() {
        if (root == null) {
            throw new NoSuchElementException("Heap is empty");
        }
        int min = root.key;
        if (root.children.isEmpty()) {
            root = null;
        } else {
            root = mergePairs(root.children);
        }
        return min;
    }

    private RankPairingNode mergePairs(List<RankPairingNode> nodes) {
        if (nodes.size() == 1) {
            return nodes.get(0);
        }
        List<RankPairingNode> pairs = new ArrayList<>();
        for (int i = 0; i < nodes.size() - 1; i += 2) {
            RankPairingNode merged = merge(nodes.get(i), nodes.get(i + 1));
            pairs.add(merged);
        }
        if (nodes.size() % 2 != 0) {
            pairs.add(nodes.get(nodes.size() - 1));
        }
        return mergePairs(pairs);
    }

    public void printHeap() {
        System.out.println("Rank-Pairing Heap:");
        if (root != null) {
            printNode(root);
        } else {
            System.out.println("Heap is empty");
        }
        System.out.println();
    }

    private void printNode(RankPairingNode node) {
        System.out.print(node.key + " ");
        for (RankPairingNode child : node.children) {
            printNode(child);
        }
    }

    public static void main(String[] args) {
        RankPairingHeap rankPairingHeap = new RankPairingHeap();
        rankPairingHeap.insert(10);
        rankPairingHeap.insert(5);
        rankPairingHeap.insert(20);
        rankPairingHeap.insert(15);

        rankPairingHeap.printHeap();

        System.out.println("Extracted min element: " + rankPairingHeap.extractMin());

        rankPairingHeap.printHeap();
    }
}

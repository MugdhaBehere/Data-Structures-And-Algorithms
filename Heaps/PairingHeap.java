package Heaps;

import java.util.*;

class PairingNode {
    int key;
    List<PairingNode> children;

    public PairingNode(int key) {
        this.key = key;
        this.children = new ArrayList<>();
    }
}

class PairingHeap {
    private PairingNode root;

    public PairingHeap() {
        root = null;
    }

    public void insert(int key) {
        if (root == null) {
            root = new PairingNode(key);
        } else {
            root = merge(root, new PairingNode(key));
        }
    }

    private PairingNode merge(PairingNode node1, PairingNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        if (node1.key < node2.key) {
            node2.children.add(node1);
            return node2;
        } else {
            node1.children.add(node2);
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

    private PairingNode mergePairs(List<PairingNode> nodes) {
        if (nodes.size() == 1) {
            return nodes.get(0);
        }
        List<PairingNode> pairs = new ArrayList<>();
        for (int i = 0; i < nodes.size() - 1; i += 2) {
            PairingNode merged = merge(nodes.get(i), nodes.get(i + 1));
            pairs.add(merged);
        }
        if (nodes.size() % 2 != 0) {
            pairs.add(nodes.get(nodes.size() - 1));
        }
        return mergePairs(pairs);
    }

    public void printHeap() {
        System.out.println("Pairing Heap:");
        if (root != null) {
            printNode(root);
        } else {
            System.out.println("Heap is empty");
        }
        System.out.println();
    }

    private void printNode(PairingNode node) {
        System.out.print(node.key + " ");
        for (PairingNode child : node.children) {
            printNode(child);
        }
    }

    public static void main(String[] args) {
        PairingHeap pairingHeap = new PairingHeap();
        pairingHeap.insert(10);
        pairingHeap.insert(5);
        pairingHeap.insert(20);
        pairingHeap.insert(15);

        pairingHeap.printHeap();

        System.out.println("Extracted min element: " + pairingHeap.extractMin());

        pairingHeap.printHeap();
    }
}

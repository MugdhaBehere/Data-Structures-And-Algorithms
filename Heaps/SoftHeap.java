package Heaps;

import java.util.*;

class SoftHeapNode {
    int key;
    int delta;
    SoftHeapNode left;
    SoftHeapNode right;
    SoftHeapNode child;
    SoftHeapNode parent;

    public SoftHeapNode(int key) {
        this.key = key;
        this.delta = 0;
        this.left = null;
        this.right = null;
        this.child = null;
        this.parent = null;
    }
}

class SoftHeap {
    private SoftHeapNode root;
    private SoftHeapNode min;

    public SoftHeap() {
        this.root = null;
        this.min = null;
    }

    public void insert(int key) {
        SoftHeapNode newNode = new SoftHeapNode(key);
        if (root == null) {
            root = newNode;
            min = newNode;
        } else {
            root = meld(root, newNode);
            if (newNode.key < min.key) {
                min = newNode;
            }
        }
    }

    public int extractMin() {
        if (min == null) {
            throw new NoSuchElementException("Heap is empty");
        }
        int minValue = min.key;
        root = pairwiseCombine(min.child);
        if (root != null) {
            root.parent = null;
        }
        min = findMin(root);
        return minValue;
    }

    public void decreaseKey(SoftHeapNode node, int newKey) {
        node.key = newKey;
        if (node.parent != null && node.key < node.parent.key) {
            if (node == min || node.key < min.key) {
                min = node;
            }
            cut(node);
        }
    }

    private void cut(SoftHeapNode node) {
        if (node.parent == null) {
            return;
        }
        if (node == node.parent.child) {
            node.parent.child = node.right;
        } else {
            node.left.right = node.right;
        }
        if (node.right != null) {
            node.right.left = node.left;
        }
        node.left = null;
        node.right = null;
        node.parent = null;
        root = meld(root, node);
    }

    private SoftHeapNode meld(SoftHeapNode node1, SoftHeapNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        if (node1.key < node2.key) {
            SoftHeapNode temp = node1;
            node1 = node2;
            node2 = temp;
        }
        node2.right = node1.child;
        if (node1.child != null) {
            node1.child.left = node2;
        }
        node1.child = node2;
        node2.left = node1;
        return node1;
    }

    private SoftHeapNode pairwiseCombine(SoftHeapNode start) {
        if (start == null || start.right == null) {
            return start;
        }
        List<SoftHeapNode> pairs = new ArrayList<>();
        SoftHeapNode current = start;
        while (current != null) {
            SoftHeapNode next = current.right;
            current.right = null;
            SoftHeapNode pair = current;
            if (next != null) {
                next.left = null;
                pair = meld(current, next);
                current = next.right;
            } else {
                current = null;
            }
            pairs.add(pair);
        }
        SoftHeapNode newStart = null;
        for (SoftHeapNode pair : pairs) {
            newStart = meld(newStart, pair);
        }
        return pairwiseCombine(newStart);
    }

    private SoftHeapNode findMin(SoftHeapNode node) {
        if (node == null) {
            return null;
        }
        SoftHeapNode minNode = node;
        while (node != null) {
            if (node.key < minNode.key) {
                minNode = node;
            }
            node = node.right;
        }
        return minNode;
    }

    public void printHeap() {
        System.out.println("Soft Heap:");
        printHeap(root);
        System.out.println();
    }

    private void printHeap(SoftHeapNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            printHeap(node.child);
            printHeap(node.right);
        }
    }

    public static void main(String[] args) {
        SoftHeap softHeap = new SoftHeap();
        softHeap.insert(10);
        softHeap.insert(5);
        softHeap.insert(20);
        softHeap.insert(15);

        softHeap.printHeap();

        System.out.println("Extracted min element: " + softHeap.extractMin());

        softHeap.printHeap();
    }
}

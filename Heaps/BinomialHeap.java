package Heaps;

import java.util.ArrayList;
import java.util.List;

class BinomialNode {
    int key;
    int degree;
    BinomialNode parent;
    BinomialNode sibling;
    BinomialNode child;

    public BinomialNode(int key) {
        this.key = key;
        this.degree = 0;
        this.parent = null;
        this.sibling = null;
        this.child = null;
    }
}

class BinomialHeap {
    private BinomialNode head;

    public BinomialHeap() {
        head = null;
    }

    public void insert(int key) {
        BinomialNode newNode = new BinomialNode(key);
        if (head == null) {
            head = newNode;
        } else {
            head = merge(head, newNode);
        }
    }

    private BinomialNode merge(BinomialNode node1, BinomialNode node2) {
        if (node1 == null)
            return node2;
        if (node2 == null)
            return node1;

        if (node1.degree <= node2.degree) {
            node1.sibling = merge(node1.sibling, node2);
            node1.sibling.parent = node1;
            return node1;
        } else {
            node2.sibling = merge(node1, node2.sibling);
            node2.sibling.parent = node2;
            return node2;
        }
    }

    private BinomialNode union(BinomialNode h1, BinomialNode h2) {
        BinomialNode newHead = merge(h1, h2);
        if (newHead == null)
            return null;

        BinomialNode prevX = null;
        BinomialNode x = newHead;
        BinomialNode nextX = x.sibling;

        while (nextX != null) {
            if ((x.degree != nextX.degree) || ((nextX.sibling != null) && (nextX.sibling.degree == x.degree))) {
                prevX = x;
                x = nextX;
            } else {
                if (x.key <= nextX.key) {
                    x.sibling = nextX.sibling;
                    nextX.parent = x;
                    nextX.sibling = x.child;
                    x.child = nextX;
                    x.degree++;
                } else {
                    if (prevX == null) {
                        newHead = nextX;
                    } else {
                        prevX.sibling = nextX;
                    }
                    x.parent = nextX;
                    x.sibling = nextX.child;
                    nextX.child = x;
                    nextX.degree++;
                    x = nextX;
                }
            }
            nextX = x.sibling;
        }
        return newHead;
    }

    public void extractMin() {
        if (head == null) {
            System.out.println("Heap is empty");
            return;
        }

        BinomialNode minPrev = null;
        BinomialNode min = head;
        BinomialNode next = head.sibling;
        BinomialNode nextPrev = head;

        while (next != null) {
            if (next.key < min.key) {
                min = next;
                minPrev = nextPrev;
            }
            nextPrev = next;
            next = next.sibling;
        }

        if (minPrev != null) {
            minPrev.sibling = min.sibling;
        } else {
            head = min.sibling;
        }

        BinomialNode reversedChild = reverseChildren(min.child);
        head = union(head, reversedChild);
    }

    private BinomialNode reverseChildren(BinomialNode node) {
        BinomialNode prev = null;
        BinomialNode current = node;
        BinomialNode next;

        while (current != null) {
            next = current.sibling;
            current.sibling = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public void printHeap() {
        System.out.println("Binomial Heap:");
        if (head == null) {
            System.out.println("Heap is empty");
            return;
        }
        List<BinomialNode> nodes = new ArrayList<>();
        BinomialNode curr = head;
        while (curr != null) {
            nodes.add(curr);
            curr = curr.sibling;
        }
        for (BinomialNode node : nodes) {
            printNode(node);
        }
        System.out.println();
    }

    private void printNode(BinomialNode node) {
        System.out.print("B" + node.degree + ": ");
        while (node != null) {
            System.out.print(node.key + " ");
            node = node.child;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BinomialHeap binomialHeap = new BinomialHeap();
        binomialHeap.insert(10);
        binomialHeap.insert(20);
        binomialHeap.insert(5);
        binomialHeap.insert(15);

        binomialHeap.printHeap();

        binomialHeap.extractMin();

        binomialHeap.printHeap();
    }
}

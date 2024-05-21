package Heaps;

import java.util.*;

class FibonacciNode {
    int key;
    FibonacciNode parent;
    FibonacciNode child;
    FibonacciNode left;
    FibonacciNode right;
    int degree;
    boolean marked;

    public FibonacciNode(int key) {
        this.key = key;
        this.parent = null;
        this.child = null;
        this.left = this;
        this.right = this;
        this.degree = 0;
        this.marked = false;
    }
}

class FibonacciHeap {
    private FibonacciNode min;
    private int size;

    public FibonacciHeap() {
        min = null;
        size = 0;
    }

    public void insert(int key) {
        FibonacciNode node = new FibonacciNode(key);
        if (min == null) {
            min = node;
        } else {
            insertIntoRootList(node);
            if (node.key < min.key) {
                min = node;
            }
        }
        size++;
    }

    private void insertIntoRootList(FibonacciNode node) {
        if (min == null) {
            min = node;
        } else {
            node.right = min.right;
            min.right = node;
            node.left = min;
            node.right.left = node;
        }
    }

    public int getMin() {
        if (min == null) {
            throw new NoSuchElementException("Heap is empty");
        }
        return min.key;
    }

    public int extractMin() {
        if (min == null) {
            throw new NoSuchElementException("Heap is empty");
        }
        FibonacciNode minNode = min;
        if (min == min.right) {
            min = null;
        } else {
            removeNodeFromRootList(min);
            min = min.right;
            consolidate();
        }
        size--;
        return minNode.key;
    }

    private void removeNodeFromRootList(FibonacciNode node) {
        node.left.right = node.right;
        node.right.left = node.left;
    }

    private void consolidate() {
        int maxDegree = (int) Math.ceil(Math.log(size) / Math.log(2));
        List<FibonacciNode> degreeTable = new ArrayList<>(Collections.nCopies(maxDegree + 1, null));
        List<FibonacciNode> toVisit = new ArrayList<>();

        toVisit.add(min);
        while (!toVisit.isEmpty()) {
            FibonacciNode current = toVisit.remove(0);
            while (true) {
                int degree = current.degree;
                while (degreeTable.get(degree) != null) {
                    FibonacciNode other = degreeTable.get(degree);
                    if (current.key > other.key) {
                        FibonacciNode temp = current;
                        current = other;
                        other = temp;
                    }
                    link(other, current);
                    degreeTable.set(degree, null);
                    degree++;
                }
                degreeTable.set(degree, current);
                if (current == current.right) {
                    break;
                }
                current = current.right;
            }
            if (current.key <= min.key) {
                min = current;
            }
        }
    }

    private void link(FibonacciNode child, FibonacciNode parent) {
        removeNodeFromRootList(child);
        child.left = child;
        child.right = child;
        child.parent = parent;
        if (parent.child == null) {
            parent.child = child;
        } else {
            child.right = parent.child.right;
            parent.child.right = child;
            child.right.left = child;
            child.left = parent.child;
        }
        parent.degree++;
        child.marked = false;
    }

    public boolean isEmpty() {
        return min == null;
    }

    public void decreaseKey(FibonacciNode node, int newKey) {
        if (newKey > node.key) {
            throw new IllegalArgumentException("New key is greater than the current key");
        }
        node.key = newKey;
        FibonacciNode parent = node.parent;
        if (parent != null && node.key < parent.key) {
            cut(node, parent);
            cascadingCut(parent);
        }
        if (node.key < min.key) {
            min = node;
        }
    }

    private void cut(FibonacciNode node, FibonacciNode parent) {
        removeNodeFromChildList(parent, node);
        parent.degree--;
        insertIntoRootList(node);
        node.parent = null;
        node.marked = false;
    }

    private void cascadingCut(FibonacciNode node) {
        FibonacciNode parent = node.parent;
        if (parent != null) {
            if (!node.marked) {
                node.marked = true;
            } else {
                cut(node, parent);
                cascadingCut(parent);
            }
        }
    }

    private void removeNodeFromChildList(FibonacciNode parent, FibonacciNode child) {
        if (parent.child == child) {
            parent.child = child.right;
        }
        if (child == child.right) {
            parent.child = null;
        } else {
            child.left.right = child.right;
            child.right.left = child.left;
        }
        child.left = child;
        child.right = child;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        FibonacciHeap fibHeap = new FibonacciHeap();
        fibHeap.insert(10);
        fibHeap.insert(20);
        fibHeap.insert(5);
        fibHeap.insert(15);

        System.out.println("Min value: " + fibHeap.getMin());
        System.out.println("Extracted min value: " + fibHeap.extractMin());
        System.out.println("Min value after extraction: " + fibHeap.getMin());
    }
}

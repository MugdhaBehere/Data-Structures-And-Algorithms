package Trees;

import java.util.ArrayList;

class BTree {
    private int T; // Minimum degree (defines the range for number of keys)

    // B-Tree node
    class BTreeNode {
        ArrayList<Integer> keys; // An array of keys
        ArrayList<BTreeNode> children; // An array of child pointers
        boolean isLeaf; // Is true when node is leaf. Otherwise false

        BTreeNode(boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
        }
    }

    private BTreeNode root; // Pointer to root node

    // Constructor
    public BTree(int t) {
        this.T = t;
        this.root = new BTreeNode(true);
    }

    // Function to traverse the tree
    public void traverse() {
        traverse(root);
        System.out.println();
    }

    // Function to traverse the tree in in-order fashion
    private void traverse(BTreeNode node) {
        int i;
        for (i = 0; i < node.keys.size(); i++) {
            if (!node.isLeaf) {
                traverse(node.children.get(i));
            }
            System.out.print(node.keys.get(i) + " ");
        }
        if (!node.isLeaf) {
            traverse(node.children.get(i));
        }
    }

    // Function to insert a new key in the B-Tree
    public void insert(int key) {
        BTreeNode r = root;
        if (r.keys.size() == 2 * T - 1) {
            BTreeNode s = new BTreeNode(false);
            root = s;
            s.children.add(r);
            splitChild(s, 0, r);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }

    // Function to split the child y of node x at index i
    private void splitChild(BTreeNode x, int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.isLeaf);
        z.keys.addAll(y.keys.subList(T, y.keys.size()));
        y.keys.subList(T, y.keys.size()).clear();

        if (!y.isLeaf) {
            z.children.addAll(y.children.subList(T, y.children.size()));
            y.children.subList(T, y.children.size()).clear();
        }

        x.children.add(i + 1, z);
        x.keys.add(i, y.keys.remove(T - 1));
    }

    // Function to insert a key into a non-full node
    private void insertNonFull(BTreeNode node, int key) {
        int i = node.keys.size() - 1;
        if (node.isLeaf) {
            node.keys.add(0); // Add a dummy value to expand the list
            while (i >= 0 && key < node.keys.get(i)) {
                node.keys.set(i + 1, node.keys.get(i));
                i--;
            }
            node.keys.set(i + 1, key);
        } else {
            while (i >= 0 && key < node.keys.get(i)) {
                i--;
            }
            i++;
            if (node.children.get(i).keys.size() == 2 * T - 1) {
                splitChild(node, i, node.children.get(i));
                if (key > node.keys.get(i)) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key);
        }
    }

    public static void main(String[] args) {
        BTree tree = new BTree(3);

        // Inserting values
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);
        tree.insert(12);
        tree.insert(30);
        tree.insert(7);
        tree.insert(17);

        // Print the B-Tree
        tree.traverse(); // Expected Output: 5 6 7 10 12 17 20 30
    }
}

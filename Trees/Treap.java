package Trees;

import java.util.Random;

class Treap {
    class Node {
        int key, priority;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.priority = new Random().nextInt();
            this.left = this.right = null;
        }
    }

    private Node root;

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        return y;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
            if (root.left.priority > root.priority) {
                root = rightRotate(root);
            }
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
            if (root.right.priority > root.priority) {
                root = leftRotate(root);
            }
        }
        return root;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    public static void main(String[] args) {
        Treap treap = new Treap();
        treap.insert(50);
        treap.insert(30);
        treap.insert(20);
        treap.insert(40);
        treap.insert(70);
        treap.insert(60);
        treap.insert(80);

        // Inorder traversal of the treap
        treap.inorder(); // Expected Output: 20 30 40 50 60 70 80
    }
}

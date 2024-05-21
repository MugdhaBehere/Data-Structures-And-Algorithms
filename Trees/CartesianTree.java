package Trees;

class CartesianTree {
    class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            left = right = null;
        }
    }

    private Node root;

    public CartesianTree(int[] arr) {
        root = buildTree(arr, 0, arr.length - 1);
    }

    private Node buildTree(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        int minIndex = findMinIndex(arr, start, end);
        Node node = new Node(arr[minIndex]);
        node.left = buildTree(arr, start, minIndex - 1);
        node.right = buildTree(arr, minIndex + 1, end);
        return node;
    }

    private int findMinIndex(int[] arr, int start, int end) {
        int minIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
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
        int[] arr = { 5, 10, 40, 30, 28 };
        CartesianTree tree = new CartesianTree(arr);

        // Inorder traversal of the Cartesian Tree
        tree.inorder(); // Expected Output: 5 10 28 30 40
    }
}

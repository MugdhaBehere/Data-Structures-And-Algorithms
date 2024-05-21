package Trees;

class KDTree {
    class Node {
        int[] point;
        Node left, right;

        Node(int[] point) {
            this.point = point;
            this.left = this.right = null;
        }
    }

    private Node root;
    private int k;

    public KDTree(int k) {
        this.k = k;
    }

    public void insert(int[] point) {
        root = insertRec(root, point, 0);
    }

    private Node insertRec(Node root, int[] point, int depth) {
        if (root == null) {
            return new Node(point);
        }

        int cd = depth % k;
        if (point[cd] < root.point[cd]) {
            root.left = insertRec(root.left, point, depth + 1);
        } else {
            root.right = insertRec(root.right, point, depth + 1);
        }

        return root;
    }

    public boolean search(int[] point) {
        return searchRec(root, point, 0);
    }

    private boolean searchRec(Node root, int[] point, int depth) {
        if (root == null) {
            return false;
        }

        if (arePointsEqual(root.point, point)) {
            return true;
        }

        int cd = depth % k;
        if (point[cd] < root.point[cd]) {
            return searchRec(root.left, point, depth + 1);
        } else {
            return searchRec(root.right, point, depth + 1);
        }
    }

    private boolean arePointsEqual(int[] point1, int[] point2) {
        for (int i = 0; i < k; i++) {
            if (point1[i] != point2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int k = 2;
        KDTree tree = new KDTree(k);

        int[][] points = {
                { 3, 6 }, { 17, 15 }, { 13, 15 },
                { 6, 12 }, { 9, 1 }, { 2, 7 }, { 10, 19 }
        };

        for (int[] point : points) {
            tree.insert(point);
        }

        int[] point1 = { 10, 19 };
        System.out.println(tree.search(point1)); // Expected Output: true

        int[] point2 = { 12, 19 };
        System.out.println(tree.search(point2)); // Expected Output: false
    }
}

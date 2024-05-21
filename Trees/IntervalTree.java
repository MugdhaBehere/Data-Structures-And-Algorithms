package Trees;

class IntervalTree {
    class Interval {
        int low, high;

        Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    class Node {
        Interval interval;
        int max;
        Node left, right;

        Node(Interval interval) {
            this.interval = interval;
            this.max = interval.high;
            this.left = this.right = null;
        }
    }

    private Node root;

    public void insert(int low, int high) {
        root = insertRec(root, new Interval(low, high));
    }

    private Node insertRec(Node root, Interval interval) {
        if (root == null) {
            return new Node(interval);
        }

        if (interval.low < root.interval.low) {
            root.left = insertRec(root.left, interval);
        } else {
            root.right = insertRec(root.right, interval);
        }

        if (root.max < interval.high) {
            root.max = interval.high;
        }

        return root;
    }

    public boolean overlapSearch(int low, int high) {
        Interval interval = new Interval(low, high);
        return overlapSearchRec(root, interval);
    }

    private boolean overlapSearchRec(Node root, Interval interval) {
        if (root == null) {
            return false;
        }

        if (root.interval.low <= interval.high && interval.low <= root.interval.high) {
            return true;
        }

        if (root.left != null && root.left.max >= interval.low) {
            return overlapSearchRec(root.left, interval);
        }

        return overlapSearchRec(root.right, interval);
    }

    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();

        // Inserting intervals
        tree.insert(15, 20);
        tree.insert(10, 30);
        tree.insert(17, 19);
        tree.insert(5, 20);
        tree.insert(12, 15);
        tree.insert(30, 40);

        // Search for overlapping intervals
        System.out.println(tree.overlapSearch(14, 16)); // Expected Output: true
        System.out.println(tree.overlapSearch(21, 23)); // Expected Output: false
    }
}

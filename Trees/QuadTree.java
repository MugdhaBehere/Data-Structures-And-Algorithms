package Trees;

class QuadTree {
    class Node {
        int x, y;
        boolean isLeaf;
        Node[] children;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.isLeaf = true;
            this.children = new Node[4];
        }
    }

    private Node root;
    private int capacity;

    public QuadTree(int capacity) {
        this.capacity = capacity;
    }

    public void insert(int x, int y) {
        root = insertRec(root, x, y, 0, 0, capacity);
    }

    private Node insertRec(Node root, int x, int y, int minX, int minY, int size) {
        if (root == null) {
            return new Node(x, y);
        }

        if (root.isLeaf) {
            if (root.x == x && root.y == y) {
                return root;
            }

            Node oldNode = root;
            root = new Node(-1, -1);
            root.isLeaf = false;
            int midX = minX + size / 2;
            int midY = minY + size / 2;

            int index = getIndex(oldNode.x, oldNode.y, minX, minY, midX, midY);
            root.children[index] = oldNode;
        }

        int midX = minX + size / 2;
        int midY = minY + size / 2;
        int index = getIndex(x, y, minX, minY, midX, midY);
        if (index == 0) {
            root.children[index] = insertRec(root.children[index], x, y, minX, minY, size / 2);
        } else if (index == 1) {
            root.children[index] = insertRec(root.children[index], x, y, midX, minY, size / 2);
        } else if (index == 2) {
            root.children[index] = insertRec(root.children[index], x, y, minX, midY, size / 2);
        } else {
            root.children[index] = insertRec(root.children[index], x, y, midX, midY, size / 2);
        }

        return root;
    }

    private int getIndex(int x, int y, int minX, int minY, int midX, int midY) {
        if (x < midX && y < midY)
            return 0;
        if (x >= midX && y < midY)
            return 1;
        if (x < midX && y >= midY)
            return 2;
        return 3;
    }

    public boolean search(int x, int y) {
        return searchRec(root, x, y, 0, 0, capacity);
    }

    private boolean searchRec(Node root, int x, int y, int minX, int minY, int size) {
        if (root == null) {
            return false;
        }

        if (root.isLeaf) {
            return root.x == x && root.y == y;
        }

        int midX = minX + size / 2;
        int midY = minY + size / 2;
        int index = getIndex(x, y, minX, minY, midX, midY);

        if (index == 0) {
            return searchRec(root.children[index], x, y, minX, minY, size / 2);
        } else if (index == 1) {
            return searchRec(root.children[index], x, y, midX, minY, size / 2);
        } else if (index == 2) {
            return searchRec(root.children[index], x, y, minX, midY, size / 2);
        } else {
            return searchRec(root.children[index], x, y, midX, midY, size / 2);
        }
    }

    public static void main(String[] args) {
        QuadTree quadTree = new QuadTree(16);

        // Insert points
        quadTree.insert(1, 1);
        quadTree.insert(5, 5);
        quadTree.insert(13, 13);
        quadTree.insert(7, 7);

        // Search points
        System.out.println(quadTree.search(5, 5)); // Expected Output: true
        System.out.println(quadTree.search(9, 9)); // Expected Output: false
    }
}

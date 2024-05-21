package Trees;

class Octree {
    class Node {
        int x, y, z;
        boolean isLeaf;
        Node[] children;

        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.isLeaf = true;
            this.children = new Node[8];
        }
    }

    private Node root;
    private int capacity;

    public Octree(int capacity) {
        this.capacity = capacity;
    }

    public void insert(int x, int y, int z) {
        root = insertRec(root, x, y, z, 0, 0, 0, capacity);
    }

    private Node insertRec(Node root, int x, int y, int z, int minX, int minY, int minZ, int size) {
        if (root == null) {
            return new Node(x, y, z);
        }

        if (root.isLeaf) {
            if (root.x == x && root.y == y && root.z == z) {
                return root;
            }

            Node oldNode = root;
            root = new Node(-1, -1, -1);
            root.isLeaf = false;
            int midX = minX + size / 2;
            int midY = minY + size / 2;
            int midZ = minZ + size / 2;

            int index = getIndex(oldNode.x, oldNode.y, oldNode.z, minX, minY, minZ, midX, midY, midZ);
            root.children[index] = oldNode;
        }

        int midX = minX + size / 2;
        int midY = minY + size / 2;
        int midZ = minZ + size / 2;
        int index = getIndex(x, y, z, minX, minY, minZ, midX, midY, midZ);

        if (index == 0) {
            root.children[index] = insertRec(root.children[index], x, y, z, minX, minY, minZ, size / 2);
        } else if (index == 1) {
            root.children[index] = insertRec(root.children[index], x, y, z, midX, minY, minZ, size / 2);
        } else if (index == 2) {
            root.children[index] = insertRec(root.children[index], x, y, z, minX, midY, minZ, size / 2);
        } else if (index == 3) {
            root.children[index] = insertRec(root.children[index], x, y, z, midX, midY, minZ, size / 2);
        } else if (index == 4) {
            root.children[index] = insertRec(root.children[index], x, y, z, minX, minY, midZ, size / 2);
        } else if (index == 5) {
            root.children[index] = insertRec(root.children[index], x, y, z, midX, minY, midZ, size / 2);
        } else if (index == 6) {
            root.children[index] = insertRec(root.children[index], x, y, z, minX, midY, midZ, size / 2);
        } else {
            root.children[index] = insertRec(root.children[index], x, y, z, midX, midY, midZ, size / 2);
        }

        return root;
    }

    private int getIndex(int x, int y, int z, int minX, int minY, int minZ, int midX, int midY, int midZ) {
        if (x < midX && y < midY && z < midZ)
            return 0;
        if (x >= midX && y < midY && z < midZ)
            return 1;
        if (x < midX && y >= midY && z < midZ)
            return 2;
        if (x >= midX && y >= midY && z < midZ)
            return 3;
        if (x < midX && y < midY && z >= midZ)
            return 4;
        if (x >= midX && y < midY && z >= midZ)
            return 5;
        if (x < midX && y >= midY && z >= midZ)
            return 6;
        return 7;
    }

    public boolean search(int x, int y, int z) {
        return searchRec(root, x, y, z, 0, 0, 0, capacity);
    }

    private boolean searchRec(Node root, int x, int y, int z, int minX, int minY, int minZ, int size) {
        if (root == null) {
            return false;
        }

        if (root.isLeaf) {
            return root.x == x && root.y == y && root.z == z;
        }

        int midX = minX + size / 2;
        int midY = minY + size / 2;
        int midZ = minZ + size / 2;
        int index = getIndex(x, y, z, minX, minY, minZ, midX, midY, midZ);

        if (index == 0) {
            return searchRec(root.children[index], x, y, z, minX, minY, minZ, size / 2);
        } else if (index == 1) {
            return searchRec(root.children[index], x, y, z, midX, minY, minZ, size / 2);
        } else if (index == 2) {
            return searchRec(root.children[index], x, y, z, minX, midY, minZ, size / 2);
        } else if (index == 3) {
            return searchRec(root.children[index], x, y, z, midX, midY, minZ, size / 2);
        } else if (index == 4) {
            return searchRec(root.children[index], x, y, z, minX, minY, midZ, size / 2);
        } else if (index == 5) {
            return searchRec(root.children[index], x, y, z, midX, minY, midZ, size / 2);
        } else if (index == 6) {
            return searchRec(root.children[index], x, y, z, minX, midY, midZ, size / 2);
        } else {
            return searchRec(root.children[index], x, y, z, midX, midY, midZ, size / 2);
        }
    }

    public static void main(String[] args) {
        Octree octree = new Octree(16);

        // Insert points
        octree.insert(1, 1, 1);
        octree.insert(5, 5, 5);
        octree.insert(13, 13, 13);
        octree.insert(7, 7, 7);

        // Search points
        System.out.println(octree.search(5, 5, 5)); // Expected Output: true
        System.out.println(octree.search(9, 9, 9)); // Expected Output: false
    }
}

package Algorithms.TreeTraversalAlgorithms;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class ZigZagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true; // Flag to alternate the direction of traversal

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Add node value to the current level list based on the direction of traversal
                if (leftToRight) {
                    level.add(node.val);
                } else {
                    level.add(0, node.val); // Insert at the beginning for right to left traversal
                }

                // Add left and right children to the queue
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(level);
            leftToRight = !leftToRight; // Toggle the direction for the next level
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        ZigZagTraversal traversal = new ZigZagTraversal();
        List<List<Integer>> zigzag = traversal.zigzagLevelOrder(root);

        System.out.println("Zigzag Traversal: " + zigzag);
    }
}

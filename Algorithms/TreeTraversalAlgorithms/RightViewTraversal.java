package Algorithms.TreeTraversalAlgorithms;

import java.util.*;



public class RightViewTraversal {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode lastNode = null; // To store the last node value at each level

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                if (current != null) {
                    lastNode = current; // Update last node value at each level

                    if (current.left != null)
                        queue.offer(current.left);
                    if (current.right != null)
                        queue.offer(current.right);
                }
            }

            if (lastNode != null)
                result.add(lastNode.val); // Add last node value to result
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        RightViewTraversal traversal = new RightViewTraversal();
        List<Integer> rightView = traversal.rightSideView(root);

        System.out.println("Right View Traversal: " + rightView);
    }
}

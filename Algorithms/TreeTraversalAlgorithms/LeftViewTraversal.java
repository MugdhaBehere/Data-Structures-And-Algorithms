package Algorithms.TreeTraversalAlgorithms;

import java.util.*;



public class LeftViewTraversal {
    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                if (i == 0)
                    result.add(current.val); // Add the first node at each level to the result

                if (current.left != null)
                    queue.offer(current.left);
                if (current.right != null)
                    queue.offer(current.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.left.left = new TreeNode(6);

        LeftViewTraversal traversal = new LeftViewTraversal();
        List<Integer> leftView = traversal.leftSideView(root);

        System.out.println("Left View Traversal: " + leftView);
    }
}

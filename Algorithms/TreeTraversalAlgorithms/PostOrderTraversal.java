package Algorithms.TreeTraversalAlgorithms;

import java.util.*;

public class PostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null)
            return;

        postorder(node.left, result); // Traverse left subtree
        postorder(node.right, result); // Traverse right subtree
        result.add(node.val); // Visit the current node
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        PostOrderTraversal traversal = new PostOrderTraversal();

        List<Integer> postorderTraversal = traversal.postorderTraversal(root);
        System.out.println("Postorder Traversal: " + postorderTraversal);
    }
}

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

public class InOrderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode node, List<Integer> result) {
        if (node == null)
            return;

        inorder(node.left, result); // Traverse left subtree
        result.add(node.val); // Visit the current node
        inorder(node.right, result); // Traverse right subtree
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        InOrderTraversal traversal = new InOrderTraversal();

        List<Integer> inorderTraversal = traversal.inorderTraversal(root);
        System.out.println("Inorder Traversal: " + inorderTraversal);
    }
}

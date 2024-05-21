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

public class PreOrderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode node, List<Integer> result) {
        if (node == null)
            return;

        result.add(node.val); // Visit the current node
        preorder(node.left, result); // Traverse left subtree
        preorder(node.right, result); // Traverse right subtree
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        PreOrderTraversal traversal = new PreOrderTraversal();

        List<Integer> preorderTraversal = traversal.preorderTraversal(root);
        System.out.println("Preorder Traversal: " + preorderTraversal);
    }
}

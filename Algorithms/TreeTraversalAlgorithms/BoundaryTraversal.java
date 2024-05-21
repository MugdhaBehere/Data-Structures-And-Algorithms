package Algorithms.TreeTraversalAlgorithms;

import java.util.*;

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

public class BoundaryTraversal {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        result.add(root.val); // Add the root node

        // Traverse left boundary nodes (except leaf nodes) in top-down order
        traverseLeftBoundary(root.left, result);

        // Traverse leaf nodes in left-to-right order
        traverseLeaves(root.left, result);
        traverseLeaves(root.right, result);

        // Traverse right boundary nodes (except leaf nodes) in bottom-up order
        traverseRightBoundary(root.right, result);

        return result;
    }

    // Helper function to traverse left boundary nodes
    private void traverseLeftBoundary(TreeNode node, List<Integer> result) {
        if (node == null || (node.left == null && node.right == null))
            return; // Stop at leaf nodes

        result.add(node.val); // Add the current node

        if (node.left != null) {
            traverseLeftBoundary(node.left, result); // Traverse left child
        } else {
            traverseLeftBoundary(node.right, result); // Traverse right child if left child is null
        }
    }

    // Helper function to traverse leaf nodes
    private void traverseLeaves(TreeNode node, List<Integer> result) {
        if (node == null)
            return;

        traverseLeaves(node.left, result); // Traverse left child

        if (node.left == null && node.right == null) {
            result.add(node.val); // Add leaf node
        }

        traverseLeaves(node.right, result); // Traverse right child
    }

    // Helper function to traverse right boundary nodes
    private void traverseRightBoundary(TreeNode node, List<Integer> result) {
        if (node == null || (node.left == null && node.right == null))
            return; // Stop at leaf nodes

        if (node.right != null) {
            traverseRightBoundary(node.right, result); // Traverse right child
        } else {
            traverseRightBoundary(node.left, result); // Traverse left child if right child is null
        }

        result.add(node.val); // Add the current node
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        BoundaryTraversal traversal = new BoundaryTraversal();
        List<Integer> boundary = traversal.boundaryOfBinaryTree(root);

        System.out.println("Boundary Traversal: " + boundary);
    }
}

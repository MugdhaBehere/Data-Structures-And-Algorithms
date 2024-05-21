package Algorithms.TreeTraversalAlgorithms;

import java.util.*;



public class VerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Map<Integer, List<Integer>> map = new HashMap<>();

        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root, 0)); // Root has horizontal distance 0

        int minHd = 0, maxHd = 0; // Track the min and max horizontal distances

        while (!queue.isEmpty()) {
            QueueNode qNode = queue.poll();
            TreeNode node = qNode.node;
            int hd = qNode.hd;

            // Update minHd and maxHd
            minHd = Math.min(minHd, hd);
            maxHd = Math.max(maxHd, hd);

            // Update the map with the node value at the current horizontal distance
            if (!map.containsKey(hd)) {
                map.put(hd, new ArrayList<>());
            }
            map.get(hd).add(node.val);

            if (node.left != null) {
                queue.offer(new QueueNode(node.left, hd - 1)); // Decrease horizontal distance for left child
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, hd + 1)); // Increase horizontal distance for right child
            }
        }

        // Construct the result list by iterating from minHd to maxHd
        for (int i = minHd; i <= maxHd; i++) {
            if (map.containsKey(i)) {
                result.add(map.get(i));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        VerticalOrderTraversal traversal = new VerticalOrderTraversal();
        List<List<Integer>> verticalOrder = traversal.verticalOrder(root);

        System.out.println("Vertical Order Traversal: " + verticalOrder);
    }
}

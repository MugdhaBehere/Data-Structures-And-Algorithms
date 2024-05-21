package Algorithms.TreeTraversalAlgorithms;
import java.util.*;



public class TopViewTraversal {
    public List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Map<Integer, Integer> map = new HashMap<>();

        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root, 0)); // Root has horizontal distance 0

        while (!queue.isEmpty()) {
            QueueNode qNode = queue.poll();
            TreeNode node = qNode.node;
            int hd = qNode.hd;

            if (!map.containsKey(hd)) {
                map.put(hd, node.val);
            }

            if (node.left != null) {
                queue.offer(new QueueNode(node.left, hd - 1)); // Decrease horizontal distance for left child
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, hd + 1)); // Increase horizontal distance for right child
            }
        }

        // Add the values from the map to the result list
        // Using TreeMap to sort the map by horizontal distance
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(map);
        for (int value : sortedMap.values()) {
            result.add(value);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.right.right.right = new TreeNode(6);

        TopViewTraversal traversal = new TopViewTraversal();
        List<Integer> topView = traversal.topView(root);

        System.out.println("Top View Traversal: " + topView);
    }
}

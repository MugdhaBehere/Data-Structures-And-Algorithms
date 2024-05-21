package Algorithms.StringMatching;

import java.util.*;

public class SuffixTree {
    private Node root;

    public SuffixTree(String text) {
        root = new Node();
        for (int i = 0; i < text.length(); i++) {
            insertSuffix(text.substring(i), i);
        }
    }

    private void insertSuffix(String suffix, int index) {
        Node current = root;
        for (char c : suffix.toCharArray()) {
            current = current.children.computeIfAbsent(c, Node::new);
        }
        current.index = index;
    }

    public List<Integer> search(String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        Node node = findNode(pattern);
        collectOccurrences(node, occurrences);
        return occurrences;
    }

    private Node findNode(String pattern) {
        Node current = root;
        for (char c : pattern.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    private void collectOccurrences(Node node, List<Integer> occurrences) {
        if (node == null) {
            return;
        }
        if (node.index != -1) {
            occurrences.add(node.index);
        }
        for (Node child : node.children.values()) {
            collectOccurrences(child, occurrences);
        }
    }

    private static class Node {
        Map<Character, Node> children;
        int index;

        Node() {
            this.children = new HashMap<>();
            this.index = -1;
        }

        Node(char value) {
            this();
            this.index = -1;
        }
    }

    public static void main(String[] args) {
        String text = "banana";
        SuffixTree suffixTree = new SuffixTree(text);
        String pattern = "ana";
        List<Integer> occurrences = suffixTree.search(pattern);
        System.out.println("Occurrences found at positions: " + occurrences);
    }
}

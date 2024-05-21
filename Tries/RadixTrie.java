package Tries;

import java.util.HashMap;
import java.util.Map;

class RadixTrieNode {
    Map<Character, RadixTrieNode> children;
    boolean isEnd;

    public RadixTrieNode() {
        children = new HashMap<>();
        isEnd = false;
    }
}

class RadixTrie {
    private RadixTrieNode root;

    public RadixTrie() {
        root = new RadixTrieNode();
    }

    public void insert(String word) {
        insert(root, word, 0);
    }

    private void insert(RadixTrieNode node, String word, int index) {
        if (index == word.length()) {
            node.isEnd = true;
            return;
        }

        char currentChar = word.charAt(index);
        RadixTrieNode child = node.children.get(currentChar);
        if (child == null) {
            child = new RadixTrieNode();
            node.children.put(currentChar, child);
        }

        insert(child, word, index + 1);
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(RadixTrieNode node, String word, int index) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            return node.isEnd;
        }

        char currentChar = word.charAt(index);
        RadixTrieNode child = node.children.get(currentChar);
        if (child == null) {
            return false;
        }

        return search(child, word, index + 1);
    }

    public static void main(String[] args) {
        RadixTrie trie = new RadixTrie();
        trie.insert("apple");
        trie.insert("banana");

        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'orange': " + trie.search("orange")); // false
    }
}

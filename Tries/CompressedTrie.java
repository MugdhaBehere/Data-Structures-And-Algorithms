package Tries;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    char content;
    boolean isEnd;
    Map<Character, TrieNode> children;

    public TrieNode(char c) {
        content = c;
        isEnd = false;
        children = new HashMap<>();
    }
}

class CompressedTrie {
    private TrieNode root;

    public CompressedTrie() {
        root = new TrieNode((char) 0);
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                current.children.put(c, new TrieNode(c));
            }
            current = current.children.get(c);
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return current.isEnd;
    }

    public static void main(String[] args) {
        CompressedTrie compressedTrie = new CompressedTrie();
        compressedTrie.insert("apple");
        compressedTrie.insert("banana");
        System.out.println("Search 'apple': " + compressedTrie.search("apple")); // true
        System.out.println("Search 'orange': " + compressedTrie.search("orange")); // false
    }
}

package Tries;

import java.util.HashMap;
import java.util.Map;

class BurstTrieNode {
    char content;
    boolean isEnd;
    Map<Character, BurstTrieNode> children;

    public BurstTrieNode(char c) {
        content = c;
        isEnd = false;
        children = new HashMap<>();
    }
}

class BurstTrie {
    private BurstTrieNode root;

    public BurstTrie() {
        root = new BurstTrieNode((char) 0);
    }

    public void insert(String word) {
        BurstTrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                current.children.put(c, new BurstTrieNode(c));
            }
            current = current.children.get(c);
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        BurstTrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return current.isEnd;
    }

    public static void main(String[] args) {
        BurstTrie burstTrie = new BurstTrie();
        burstTrie.insert("apple");
        burstTrie.insert("banana");

        System.out.println("Search 'apple': " + burstTrie.search("apple")); // true
        System.out.println("Search 'orange': " + burstTrie.search("orange")); // false
    }
}

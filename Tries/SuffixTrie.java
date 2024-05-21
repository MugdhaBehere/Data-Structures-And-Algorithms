package Tries;

import java.util.HashMap;
import java.util.Map;

class SuffixTrieNode {
    Map<Character, SuffixTrieNode> children;
    boolean isEnd;

    public SuffixTrieNode() {
        children = new HashMap<>();
        isEnd = false;
    }
}

class SuffixTrie {
    private SuffixTrieNode root;

    public SuffixTrie() {
        root = new SuffixTrieNode();
    }

    public void insert(String word) {
        for (int i = 0; i < word.length(); i++) {
            insertSuffix(word.substring(i));
        }
    }

    private void insertSuffix(String suffix) {
        SuffixTrieNode current = root;
        for (char c : suffix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                current.children.put(c, new SuffixTrieNode());
            }
            current = current.children.get(c);
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        SuffixTrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return current.isEnd;
    }

    public static void main(String[] args) {
        SuffixTrie suffixTrie = new SuffixTrie();
        suffixTrie.insert("banana");
        suffixTrie.insert("apple");
        System.out.println("Search 'ana': " + suffixTrie.search("ana")); // true
        System.out.println("Search 'le': " + suffixTrie.search("le")); // false
    }
}

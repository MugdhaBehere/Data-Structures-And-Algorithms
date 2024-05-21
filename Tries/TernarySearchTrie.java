package Tries;

class TernarySearchTrieNode {
    char data;
    boolean isEnd;
    TernarySearchTrieNode left, middle, right;

    public TernarySearchTrieNode(char data) {
        this.data = data;
        this.isEnd = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }
}

class TernarySearchTrie {
    private TernarySearchTrieNode root;

    public TernarySearchTrie() {
        root = null;
    }

    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }

    private TernarySearchTrieNode insert(TernarySearchTrieNode node, char[] word, int index) {
        if (node == null) {
            node = new TernarySearchTrieNode(word[index]);
        }

        if (word[index] < node.data) {
            node.left = insert(node.left, word, index);
        } else if (word[index] > node.data) {
            node.right = insert(node.right, word, index);
        } else {
            if (index + 1 < word.length) {
                node.middle = insert(node.middle, word, index + 1);
            } else {
                node.isEnd = true;
            }
        }
        return node;
    }

    public boolean search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    private boolean search(TernarySearchTrieNode node, char[] word, int index) {
        if (node == null) {
            return false;
        }

        if (word[index] < node.data) {
            return search(node.left, word, index);
        } else if (word[index] > node.data) {
            return search(node.right, word, index);
        } else {
            if (index == word.length - 1) {
                return node.isEnd;
            } else {
                return search(node.middle, word, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        TernarySearchTrie trie = new TernarySearchTrie();
        trie.insert("apple");
        trie.insert("banana");

        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'orange': " + trie.search("orange")); // false
    }
}

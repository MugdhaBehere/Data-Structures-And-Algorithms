package LinkedList;


import java.util.Random;

import java.util.Random;

class SkipListNode {
    int val;
    SkipListNode[] next;

    public SkipListNode(int val, int level) {
        this.val = val;
        this.next = new SkipListNode[level + 1];
    }
}

class SkipList {
    private static final int MAX_LEVEL = 16; // Maximum level for simplicity
    private int level;
    private SkipListNode head;
    private Random random;

    public SkipList() {
        this.level = 0;
        this.head = new SkipListNode(Integer.MIN_VALUE, MAX_LEVEL);
        this.random = new Random();
    }

    public void insert(int val) {
        int newLevel = randomLevel();
        SkipListNode newNode = new SkipListNode(val, newLevel);
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode current = head;

        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].val < val) {
                current = current.next[i];
            }
            update[i] = current;
        }

        for (int i = 0; i <= newLevel; i++) {
            if (update[i] == null) {
                head.next[i] = newNode;
            } else {
                newNode.next[i] = update[i].next[i];
                update[i].next[i] = newNode;
            }
        }

        if (newLevel > level) {
            level = newLevel;
        }
    }

    public boolean search(int val) {
        SkipListNode current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].val < val) {
                current = current.next[i];
            }
        }
        current = current.next[0];
        return current != null && current.val == val;
    }

    public void delete(int val) {
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].val < val) {
                current = current.next[i];
            }
            update[i] = current;
        }
        current = current.next[0];
        if (current != null && current.val == val) {
            for (int i = 0; i <= level; i++) {
                if (update[i].next[i] != current)
                    break;
                update[i].next[i] = current.next[i];
            }
            while (level > 0 && head.next[level] == null) {
                level--;
            }
        }
    }

    private int randomLevel() {
        int level = 0;
        while (random.nextDouble() < 0.5 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.insert(2);
        skipList.insert(3);
        System.out.println("Is 2 present? " + skipList.search(2)); // Output: true
        skipList.delete(2);
        System.out.println("Is 2 present? " + skipList.search(2)); // Output: false
    }
}

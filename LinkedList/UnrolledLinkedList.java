package LinkedList;

import java.util.ArrayList;
import java.util.List;

class UnrolledListNode {
    List<Integer> values;
    UnrolledListNode next;

    public UnrolledListNode(int capacity) {
        this.values = new ArrayList<>(capacity);
        this.next = null;
    }
}

class UnrolledLinkedList {
    private UnrolledListNode head;
    private int capacity;

    public UnrolledLinkedList(int capacity) {
        this.head = null;
        this.capacity = capacity;
    }

    public void insert(int val) {
        if (head == null) {
            head = new UnrolledListNode(capacity);
            head.values.add(val);
        } else {
            UnrolledListNode current = head;
            while (current.next != null && current.values.size() == capacity) {
                current = current.next;
            }
            if (current.values.size() < capacity) {
                current.values.add(val);
            } else {
                UnrolledListNode newNode = new UnrolledListNode(capacity);
                newNode.values.add(val);
                newNode.next = current.next;
                current.next = newNode;
            }
        }
    }

    public boolean search(int val) {
        UnrolledListNode current = head;
        while (current != null) {
            if (current.values.contains(val)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void delete(int val) {
        UnrolledListNode current = head;
        UnrolledListNode prev = null;
        while (current != null) {
            if (current.values.contains(val)) {
                current.values.remove(Integer.valueOf(val));
                if (current.values.isEmpty()) {
                    if (prev == null) {
                        head = current.next;
                    } else {
                        prev.next = current.next;
                    }
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    // Display the list
    public void display() {
        UnrolledListNode current = head;
        while (current != null) {
            for (int val : current.values) {
                System.out.print(val + " ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        UnrolledLinkedList list = new UnrolledLinkedList(3); // Each node can hold up to 3 elements
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.display(); // Output: 1 2 3
        System.out.println("Is 2 present? " + list.search(2)); // Output: true
        list.delete(2);
        list.display(); // Output: 1 3
    }
}

package LinkedList;

class CircularListNode {
    int val;
    CircularListNode next;

    public CircularListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class CircularLinkedList {
    CircularListNode head;

    public CircularLinkedList() {
        this.head = null;
    }

    // Insertion at the end
    public void insert(int val) {
        CircularListNode newNode = new CircularListNode(val);
        if (head == null) {
            head = newNode;
            head.next = head; // Circular reference
        } else {
            CircularListNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
    }

    // Search operation
    public boolean search(int val) {
        if (head == null) {
            return false;
        }
        CircularListNode temp = head;
        do {
            if (temp.val == val) {
                return true;
            }
            temp = temp.next;
        } while (temp != head);
        return false;
    }

    // Delete operation
    public void delete(int val) {
        if (head == null) {
            return;
        }
        if (head.val == val) {
            if (head.next == head) {
                head = null;
            } else {
                CircularListNode temp = head;
                while (temp.next != head) {
                    temp = temp.next;
                }
                temp.next = head.next;
                head = head.next;
            }
            return;
        }
        CircularListNode prev = head;
        CircularListNode curr = head.next;
        while (curr != head && curr.val != val) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != head) {
            prev.next = curr.next;
        }
    }

    // Display the list
    public void display() {
        if (head == null) {
            return;
        }
        CircularListNode temp = head;
        do {
            System.out.print(temp.val + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.display(); // Output: 1 2 3
        System.out.println("Is 2 present? " + list.search(2)); // Output: true
        list.delete(2);
        list.display(); // Output: 1 3
    }
}

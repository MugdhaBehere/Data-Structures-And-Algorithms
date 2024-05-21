package LinkedList;

class DoublyListNode {
    int val;
    DoublyListNode prev;
    DoublyListNode next;

    public DoublyListNode(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    DoublyListNode head;

    public DoublyLinkedList() {
        this.head = null;
    }

    // Insertion at the end
    public void insert(int val) {
        DoublyListNode newNode = new DoublyListNode(val);
        if (head == null) {
            head = newNode;
            return;
        }
        DoublyListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.prev = temp;
    }

    // Search operation
    public boolean search(int val) {
        DoublyListNode temp = head;
        while (temp != null) {
            if (temp.val == val) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Delete operation
    public void delete(int val) {
        if (head == null) {
            return;
        }
        if (head.val == val) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            return;
        }
        DoublyListNode temp = head;
        while (temp != null && temp.val != val) {
            temp = temp.next;
        }
        if (temp != null) {
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
            if (temp.prev != null) {
                temp.prev.next = temp.next;
            }
        }
    }

    // Display the list
    public void display() {
        DoublyListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.display(); // Output: 1 2 3
        System.out.println("Is 2 present? " + list.search(2)); // Output: true
        list.delete(2);
        list.display(); // Output: 1 3
    }
}

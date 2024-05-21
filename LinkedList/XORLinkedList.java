package LinkedList;

class XORListNode {
    int val;
    XORListNode both; // XOR of previous and next node addresses

    public XORListNode(int val) {
        this.val = val;
        this.both = null;
    }
}

class XORLinkedList {
    private XORListNode head;
    private XORListNode tail;

    public XORLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insert(int val) {
        XORListNode newNode = new XORListNode(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        XORListNode prev = null;
        XORListNode curr = head;
        XORListNode next;
        while (curr != null) {
            next = XOR(prev, curr.both);
            prev = curr;
            curr = next;
        }
        prev.both = XOR(prev.both, newNode);
        newNode.both = prev;
        tail = newNode;
    }

    public boolean search(int val) {
        XORListNode prev = null;
        XORListNode curr = head;
        XORListNode next;
        while (curr != null) {
            if (curr.val == val) {
                return true;
            }
            next = XOR(prev, curr.both);
            prev = curr;
            curr = next;
        }
        return false;
    }

    public void delete(int val) {
        XORListNode prev = null;
        XORListNode curr = head;
        XORListNode next;
        while (curr != null && curr.val != val) {
            next = XOR(prev, curr.both);
            prev = curr;
            curr = next;
        }
        if (curr != null) {
            if (prev != null) {
                prev.both = XOR(XOR(prev.both, curr), curr.both);
                if (curr == tail) {
                    tail = prev;
                }
            } else {
                head = XOR(curr.both, null);
                if (head == null) {
                    tail = null;
                }
            }
        }
    }

    private XORListNode XOR(XORListNode a, XORListNode b) {
        return a != null && b != null
                ? (a == b ? null : (a == null ? b : (b == null ? a : new XORListNode(a.val ^ b.val))))
                : null;
    }

    public void display() {
        XORListNode prev = null;
        XORListNode curr = head;
        XORListNode next;
        while (curr != null) {
            System.out.print(curr.val + " ");
            next = XOR(prev, curr.both);
            prev = curr;
            curr = next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        XORLinkedList list = new XORLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.display(); // Output: 1 2 3
        System.out.println("Is 2 present? " + list.search(2)); // Output: true
        list.delete(2);
        list.display(); // Output: 1 3
    }
}

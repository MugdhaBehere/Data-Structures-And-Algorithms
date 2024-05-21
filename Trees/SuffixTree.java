package Trees;

class SuffixTree {
    class Node {
        Node[] children = new Node[256];
        int start, end;
        Node suffixLink;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private final String text;
    private final Node root;
    private Node activeNode;
    private int activeEdge, activeLength, remainder;
    private int end;
    private Node lastNewNode;

    public SuffixTree(String text) {
        this.text = text;
        this.root = new Node(-1, -1);
        this.activeNode = root;
        this.activeEdge = -1;
        this.activeLength = 0;
        this.remainder = 0;
        this.end = -1;

        build();
    }

    private void build() {
        for (int i = 0; i < text.length(); i++) {
            extend(i);
        }
    }

    private void extend(int pos) {
        lastNewNode = null;
        end = pos;
        remainder++;

        while (remainder > 0) {
            if (activeLength == 0) {
                activeEdge = pos;
            }

            if (activeNode.children[text.charAt(activeEdge)] == null) {
                activeNode.children[text.charAt(activeEdge)] = new Node(pos, text.length());
                if (lastNewNode != null) {
                    lastNewNode.suffixLink = activeNode;
                    lastNewNode = null;
                }
            } else {
                Node next = activeNode.children[text.charAt(activeEdge)];
                int edgeLength = next.end - next.start;

                if (activeLength >= edgeLength) {
                    activeEdge += edgeLength;
                    activeLength -= edgeLength;
                    activeNode = next;
                    continue;
                }

                if (text.charAt(next.start + activeLength) == text.charAt(pos)) {
                    if (lastNewNode != null && activeNode != root) {
                        lastNewNode.suffixLink = activeNode;
                        lastNewNode = null;
                    }
                    activeLength++;
                    break;
                }

                Node split = new Node(next.start, next.start + activeLength);
                activeNode.children[text.charAt(activeEdge)] = split;
                split.children[text.charAt(pos)] = new Node(pos, text.length());
                next.start += activeLength;
                split.children[text.charAt(next.start)] = next;

                if (lastNewNode != null) {
                    lastNewNode.suffixLink = split;
                }
                lastNewNode = split;
            }

            remainder--;

            if (activeNode == root && activeLength > 0) {
                activeLength--;
                activeEdge = pos - remainder + 1;
            } else if (activeNode != root) {
                activeNode = activeNode.suffixLink;
            }
        }
    }

    public void printSuffixTree() {
        printNode(root, 0);
    }

    private void printNode(Node node, int height) {
        if (node == null)
            return;
        for (Node child : node.children) {
            if (child != null) {
                for (int i = 0; i < height; i++) {
                    System.out.print(" ");
                }
                System.out.println(text.substring(child.start, Math.min(child.end, text.length())));
                printNode(child, height + 1);
            }
        }
    }

    public static void main(String[] args) {
        String text = "banana";
        SuffixTree tree = new SuffixTree(text);
        tree.printSuffixTree(); // Prints the suffix tree structure
    }
}

package Algorithms.GreedyAlgorithms;

import java.util.PriorityQueue;
import java.util.HashMap;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = right = null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

public class HuffmanCoding {
    public static HashMap<Character, String> encodeHuffman(String text) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (char c : frequencyMap.keySet()) {
            pq.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(pq.peek(), "", huffmanCodes);
        return huffmanCodes;
    }

    private static void generateCodes(HuffmanNode root, String code, HashMap<Character, String> huffmanCodes) {
        if (root == null)
            return;
        if (root.data != '\0') {
            huffmanCodes.put(root.data, code);
        }
        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    public static void main(String[] args) {
        String text = "hello world";
        HashMap<Character, String> huffmanCodes = encodeHuffman(text);

        System.out.println("Huffman Codes:");
        for (char c : huffmanCodes.keySet()) {
            System.out.println(c + " : " + huffmanCodes.get(c));
        }
    }
}

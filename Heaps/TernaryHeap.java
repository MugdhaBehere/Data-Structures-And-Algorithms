package Heaps;


import java.util.ArrayList;
import java.util.List;

class TernaryHeap {
    private List<Integer> heap;

    public TernaryHeap() {
        heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i - 1) / 3;
    }

    private int child(int i, int k) {
        return 3 * i + k;
    }

    public void insert(int key) {
        heap.add(key);
        int index = heap.size() - 1;
        heapifyUp(index);
    }

    private void heapifyUp(int index) {
        int value = heap.get(index);
        while (index > 0 && value < heap.get(parent(index))) {
            heap.set(index, heap.get(parent(index)));
            index = parent(index);
        }
        heap.set(index, value);
    }

    public int extractMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    private void heapifyDown(int index) {
        int value = heap.get(index);
        int childIndex;
        while (child(index, 1) < heap.size()) {
            childIndex = findSmallestChildIndex(index);
            if (heap.get(childIndex) < value) {
                heap.set(index, heap.get(childIndex));
                index = childIndex;
            } else {
                break;
            }
        }
        heap.set(index, value);
    }

    private int findSmallestChildIndex(int index) {
        int smallestChildIndex = child(index, 1);
        for (int i = 2; i <= 3; i++) {
            int currentChildIndex = child(index, i);
            if (currentChildIndex < heap.size() && heap.get(currentChildIndex) < heap.get(smallestChildIndex)) {
                smallestChildIndex = currentChildIndex;
            }
        }
        return smallestChildIndex;
    }

    public void printHeap() {
        System.out.println("Ternary Heap:");
        for (int key : heap) {
            System.out.print(key + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TernaryHeap ternaryHeap = new TernaryHeap();
        ternaryHeap.insert(10);
        ternaryHeap.insert(5);
        ternaryHeap.insert(20);
        ternaryHeap.insert(15);

        ternaryHeap.printHeap();

        System.out.println("Extracted min element: " + ternaryHeap.extractMin());

        ternaryHeap.printHeap();
    }
}

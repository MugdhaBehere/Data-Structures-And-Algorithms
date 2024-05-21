package Heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class DaryHeap {
    private List<Integer> heap;
    private int d;

    public DaryHeap(int d) {
        if (d < 2) {
            throw new IllegalArgumentException("d must be greater than or equal to 2");
        }
        this.d = d;
        heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i - 1) / d;
    }

    private int child(int i, int k) {
        return d * i + k;
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
            throw new NoSuchElementException("Heap is empty");
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
        int minChildIndex;
        while (child(index, 1) < heap.size()) {
            minChildIndex = findSmallestChildIndex(index);
            if (heap.get(minChildIndex) < value) {
                heap.set(index, heap.get(minChildIndex));
                index = minChildIndex;
            } else {
                break;
            }
        }
        heap.set(index, value);
    }

    private int findSmallestChildIndex(int index) {
        int smallestChildIndex = child(index, 1);
        for (int i = 2; i <= d; i++) {
            int currentChildIndex = child(index, i);
            if (currentChildIndex < heap.size() && heap.get(currentChildIndex) < heap.get(smallestChildIndex)) {
                smallestChildIndex = currentChildIndex;
            }
        }
        return smallestChildIndex;
    }

    public void printHeap() {
        System.out.println("D-ary Heap with d = " + d + ":");
        for (int key : heap) {
            System.out.print(key + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DaryHeap daryHeap = new DaryHeap(3);
        daryHeap.insert(10);
        daryHeap.insert(5);
        daryHeap.insert(20);
        daryHeap.insert(15);

        daryHeap.printHeap();

        System.out.println("Extracted min element: " + daryHeap.extractMin());

        daryHeap.printHeap();
    }
}

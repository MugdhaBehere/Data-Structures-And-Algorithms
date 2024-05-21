package Heaps;

class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        heap = new int[capacity + 1];
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int i) {
        return 2 * i;
    }

    private int rightChild(int i) {
        return 2 * i + 1;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void heapifyUp(int i) {
        while (i > 1 && heap[parent(i)] < heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int maxIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left <= size && heap[left] > heap[maxIndex]) {
            maxIndex = left;
        }

        if (right <= size && heap[right] > heap[maxIndex]) {
            maxIndex = right;
        }

        if (i != maxIndex) {
            swap(i, maxIndex);
            heapifyDown(maxIndex);
        }
    }

    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }

        size++;
        heap[size] = value;
        heapifyUp(size);
    }

    public int extractMax() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }

        int max = heap[1];
        heap[1] = heap[size];
        size--;
        heapifyDown(1);
        return max;
    }

    public void printHeap() {
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(4);
        maxHeap.insert(2);
        maxHeap.insert(6);
        maxHeap.insert(1);

        System.out.println("Max heap:");
        maxHeap.printHeap();

        System.out.println("Extracted max element: " + maxHeap.extractMax());
        System.out.println("Max heap after extraction:");
        maxHeap.printHeap();
    }
}

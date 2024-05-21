package Heaps;

class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
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
        while (i > 1 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int minIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left <= size && heap[left] < heap[minIndex]) {
            minIndex = left;
        }

        if (right <= size && heap[right] < heap[minIndex]) {
            minIndex = right;
        }

        if (i != minIndex) {
            swap(i, minIndex);
            heapifyDown(minIndex);
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

    public int extractMin() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }

        int min = heap[1];
        heap[1] = heap[size];
        size--;
        heapifyDown(1);
        return min;
    }

    public void printHeap() {
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(4);
        minHeap.insert(2);
        minHeap.insert(6);
        minHeap.insert(1);

        System.out.println("Min heap:");
        minHeap.printHeap();

        System.out.println("Extracted min element: " + minHeap.extractMin());
        System.out.println("Min heap after extraction:");
        minHeap.printHeap();
    }
}

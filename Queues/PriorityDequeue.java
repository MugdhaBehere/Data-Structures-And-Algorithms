package Queues;

import java.util.*;

class PriorityDeque {
    private PriorityQueue<Integer> minQueue;
    private PriorityQueue<Integer> maxQueue;

    public PriorityDeque() {
        minQueue = new PriorityQueue<>();
        maxQueue = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void insertMin(int item) {
        minQueue.offer(item);
        maxQueue.offer(item);
    }

    public void insertMax(int item) {
        maxQueue.offer(item);
        minQueue.offer(item);
    }

    public int removeMin() {
        if (minQueue.isEmpty()) {
            System.out.println("Deque is empty");
            return Integer.MIN_VALUE;
        }
        int item = minQueue.poll();
        maxQueue.remove(item);
        return item;
    }

    public int removeMax() {
        if (maxQueue.isEmpty()) {
            System.out.println("Deque is empty");
            return Integer.MIN_VALUE;
        }
        int item = maxQueue.poll();
        minQueue.remove(item);
        return item;
    }

    public int peekMin() {
        if (minQueue.isEmpty()) {
            System.out.println("Deque is empty");
            return Integer.MIN_VALUE;
        }
        return minQueue.peek();
    }

    public int peekMax() {
        if (maxQueue.isEmpty()) {
            System.out.println("Deque is empty");
            return Integer.MIN_VALUE;
        }
        return maxQueue.peek();
    }

    public boolean isEmpty() {
        return minQueue.isEmpty();
    }

    public static void main(String[] args) {
        PriorityDeque deque = new PriorityDeque();
        deque.insertMin(10);
        deque.insertMax(20);
        deque.insertMin(5);
        deque.insertMax(15);

        System.out.println(deque.removeMin()); // Expected Output: 5
        System.out.println(deque.removeMax()); // Expected Output: 20
        System.out.println(deque.peekMin()); // Expected Output: 10
        System.out.println(deque.peekMax()); // Expected Output: 15
    }
}

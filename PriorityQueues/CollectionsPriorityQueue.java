package PriorityQueues;
import java.util.PriorityQueue;
public class CollectionsPriorityQueue {
    private PriorityQueue<Integer>priorityQueue;
    public CollectionsPriorityQueue(){
        this.priorityQueue=new PriorityQueue<>();
    }
    public void enqueue(int item){
        priorityQueue.offer(item);
    }
    public int dequeue(){
        if(!isEmpty()){
            return priorityQueue.poll();
        }else{
            System.out.println("Priority Queue Underflow");
            return -1;
        }
    }
    public int peek(){
        if(!isEmpty()){
            return priorityQueue.peek();
        }else{
            System.out.println("Priority Queue is empty");
            return -1;
        }
        
    }
    public boolean isEmpty(){
        return priorityQueue.isEmpty();
    }
    public int size(){
        return priorityQueue.size();
    }
    
    public static void main(String args[]) {
        CollectionsPriorityQueue priorityQueue = new CollectionsPriorityQueue();
        priorityQueue.enqueue(4);
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(7);
        priorityQueue.enqueue(1);
        priorityQueue.enqueue(9);
        System.out.println("Priority Queue Size" + priorityQueue.size());
        System.out.println("Dequeue:" + priorityQueue.dequeue());
        System.out.println("Peek" + priorityQueue.peek());
        System.out.println("PriorityQueue size:" + priorityQueue.size());
    }
}

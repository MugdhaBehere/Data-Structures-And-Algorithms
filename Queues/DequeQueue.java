package Queues;
import java.util.ArrayDeque;
public class DequeQueue {
    private ArrayDeque<Integer>deque;
    public DequeQueue(){
        this.deque=new ArrayDeque<>();
    }
    public void enqueue(int item){
        deque.offerLast(item);
    
    }
    public int dequeue(){
        if(!isEmpty()){
            return deque.pollFirst();
        }else{
            System.out.println("Queue Underflow");
            return -1;
        }
    }
    public void enqueueFront(int item){
        deque.offerFirst(item);
    }
    public int dequeRear(){
        if(!isEmpty()){
            return deque.pollLast();
        }else{
            System.out.println("Queue Underflow");
            return -1;
        }
    }
    public int peek(){
        if(!isEmpty()){
            return deque.peekFirst();
        }else{
            System.out.println("Queue is Empty");
            return -1;
        }
    }
    public boolean isEmpty(){
        return deque.isEmpty();
    }
    public int size(){
        return deque.size();
    }
    
    public static void main(String args[]) {
        DequeQueue aq = new DequeQueue();
        aq.enqueue(2);
        aq.enqueue(3);
        aq.enqueue(4);
        aq.dequeue();
        System.out.println(aq.size());
    }
}

package Queues;
import java.util.ArrayDeque;
public class ArrayDequeQueue {
    private ArrayDeque<Integer>queue;
    private ArrayDequeQueue(){
        this.queue=new ArrayDeque<>();
    }
    public void enqueue(int item){
        queue.offerLast(item);
    }
    public int dequeue(){
        if(!isEmpty()){
            return queue.pollFirst();
        }else{
            System.out.println("Queue Underflow");
            return -1;
        }
    }
    public int peek(){
        if(!isEmpty()){
            return queue.peekFirst();
        }else{
            System.out.println("Queue is empty");
            return -1;
        }
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    public int size(){
        return queue.size();
    }
    public static void main(String args[]){
        ArrayDequeQueue aq = new ArrayDequeQueue();
        aq.enqueue(2);
        aq.enqueue(3);
        aq.enqueue(4);
        aq.dequeue();
        System.out.println(aq.size());
    }
}

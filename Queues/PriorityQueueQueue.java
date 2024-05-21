package Queues;
import java.util.PriorityQueue;
public class PriorityQueueQueue {
    private PriorityQueue<Integer> queue;
    public PriorityQueueQueue(){
        this.queue=new PriorityQueue<Integer>();
    }
    public void enqueue(int item){
        queue.offer(item);
    }
    public int dequeue(){
        if(!isEmpty()){
            return queue.poll();
        } else{
            System.out.println("Queue Underflow");
            return -1;
        }
    }
    public int peek(){
        if(!isEmpty()){
            return queue.peek();
        }else{
            System.out.println("Queue is Empty");
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
    PriorityQueueQueue aq = new PriorityQueueQueue();
        aq.enqueue(2);
        aq.enqueue(3);
        aq.enqueue(4);
        aq.dequeue();
        System.out.println(aq.size());
    }
}

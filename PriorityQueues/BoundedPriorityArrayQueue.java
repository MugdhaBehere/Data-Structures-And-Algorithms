package PriorityQueues;
import java.util.*;
public class BoundedPriorityArrayQueue {
    private LinkedList<Integer>[]priorityQueues;
    public BoundedPriorityArrayQueue(int maxPriority){
        this.priorityQueues=new LinkedList[maxPriority+1];
        for(int j=0; j<=maxPriority; j++){
            priorityQueues[j]=new LinkedList<>();
            
        }
        
    }
    public void enqueue(int item, int priority){
        if(priority>=0 && priority< priorityQueues.length){
            priorityQueues[priority].add(item);
        }
        else{
            System.out.println("Invalid Priority");
        }
    }
    public int dequeue(){
        for(int j= priorityQueues.length-1; j>=0; j--){
            if(!priorityQueues[j].isEmpty()){
                return priorityQueues[j].removeFirst();
            }
        }
        System.out.println("Priority Queue Underflow");
        return -1;
    }
    public int peek(){
        for(int j= priorityQueues.length-1; j>=0; j--){
            if(!priorityQueues[j].isEmpty()){
                return priorityQueues[j].getFirst();
            }
        }
        System.out.println("Priority Queue is Empty");
        return -1;
    }
    public boolean isEmpty(){
        for(int j=0; j<priorityQueues.length; j++){
            if(!priorityQueues[j].isEmpty()){
                return false;
            }
            
        }
        return true;
    }
    public int size(){
        int count=0;
        for(int j=0; j<priorityQueues.length; j++){
            count+=priorityQueues[j].size();
        }
        return count;
    }
    
    public static void main(String args[]) {
        BoundedPriorityArrayQueue priorityQueue = new BoundedPriorityArrayQueue(10);
        priorityQueue.enqueue(4, 2);
        priorityQueue.enqueue(2, 1);
        priorityQueue.enqueue(7, 5);
        priorityQueue.enqueue(1, 3);
        priorityQueue.enqueue(9, 4);
        System.out.println("Priority Queue Size" + priorityQueue.size());
        System.out.println("Dequeue:" + priorityQueue.dequeue());
        System.out.println("Peek" + priorityQueue.peek());
        System.out.println("PriorityQueue size:" + priorityQueue.size());
    }
}

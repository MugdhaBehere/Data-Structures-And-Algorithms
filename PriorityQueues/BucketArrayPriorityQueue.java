package PriorityQueues;
import java.util.LinkedList;
public class BucketArrayPriorityQueue {
    private LinkedList<Integer>[] buckets;
    public BucketArrayPriorityQueue(int maxPriority){
        this.buckets=new LinkedList[maxPriority+1];
        for(int j=0;j<=maxPriority;j++){
            buckets[j]=new LinkedList<>();
        }
        
    }
    public void enqueue(int item, int priority){
        if(priority>=0 && priority<buckets.length){
            buckets[priority].add(item);
        }
        else{
            System.out.println("Invalid Priority");
        }
    }
    public int dequeue(){
        for(int j=buckets.length-1; j>=0; j--){
            if(!buckets[j].isEmpty()){
            return buckets[j].removeFirst();
            }
        }
        System.out.print("Priority Queue Underflow");
        return -1;
    }
    public int peek(){
        for(int j=buckets.length-1; j>=0; j--){
            if(!buckets[j].isEmpty()){
                return buckets[j].getFirst();
            }
        }
        System.out.println("Priority Queue is Empty");
        return -1;
    }
    public boolean isEmpty(){
        for(int j=0; j<buckets.length; j++){
            if(!buckets[j].isEmpty()){
                return false;
            }
        }
        return true;
    }
    public int size(){
        int count=0;
        for(int j=0; j<buckets.length;j++){
            count+=buckets[j].size();
        }
        return count;
    }
    
    public static void main(String args[]) {
        BucketArrayPriorityQueue priorityQueue = new BucketArrayPriorityQueue(10);
        priorityQueue.enqueue(4,2);
        priorityQueue.enqueue(2,1);
        priorityQueue.enqueue(7,5);
        priorityQueue.enqueue(1,3);
        priorityQueue.enqueue(9,4);
        System.out.println("Priority Queue Size" + priorityQueue.size());
        System.out.println("Dequeue:" + priorityQueue.dequeue());
        System.out.println("Peek" + priorityQueue.peek());
        System.out.println("PriorityQueue size:" + priorityQueue.size());
    }
}

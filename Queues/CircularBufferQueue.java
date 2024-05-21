package Queues;

public class CircularBufferQueue {
    private int[]queueArray;
    private int front, rear, size;
    private static final int MAX_SIZE=2000;
    public CircularBufferQueue(){
        this.queueArray=new int[MAX_SIZE];
        this.front=this.rear=this.size=0;
    }
    public void enqueue(int item){
        if(size<MAX_SIZE){
            queueArray[rear]=item;
            rear=(rear+1)%MAX_SIZE;
            size++;
        }
        else{
            System.out.println("Queue Overflow");
        }
    }
    public int dequeue(){
        if(!isEmpty()){
            int item=queueArray[front];
            front=(front+1)%MAX_SIZE;
            size--;
            return item;
        }else{
            System.out.println("Queue Underflow");
            return -1;
        }
    }
    public int peek(){
        if(!isEmpty()){
            return queueArray[front];
        }else{
            System.out.println("Queue is empty");
            return -1;
        }
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public static void main(String args[]){
        CircularBufferQueue aq=new CircularBufferQueue();
        aq.enqueue(2);
        aq.enqueue(3);
        aq.enqueue(4);
        aq.dequeue();
        System.out.println(aq.size());
    }
}

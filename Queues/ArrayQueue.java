package Queues;

public class ArrayQueue {
    private static final int MAX_SIZE=2000;
    private int front, rear, size;
    private int[] queueArray;
    public ArrayQueue(){
        this.front=0;
        this.rear=-1;
        this.size=0;
        this.queueArray=new int[MAX_SIZE];
    }
    public void enqueue(int item){
        if(size <MAX_SIZE){
            rear=(rear+1)%MAX_SIZE;
            queueArray[rear]=item;
            size++;
        }
        else{
            System.out.println("Queue Overflow");
          
        }
        
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int deque(){
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
    public int size(){
        return size;
    }
    public static void main(String args[]){
        ArrayQueue aq=new ArrayQueue();
        aq.enqueue(2);
        aq.enqueue(3);
        aq.enqueue(4);
        aq.deque();
        System.out.println(aq.size());
    }
}

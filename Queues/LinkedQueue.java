package Queues;

public class LinkedQueue {
    private Node front, rear;
    private int size;
    private class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public LinkedQueue(){
        this.front=this.rear=null;
        this.size=0;
    }
    public void enqueue(int item){
        Node newNode=new Node(item);
        if(isEmpty()){
            front=rear=newNode;
        }else{
            rear.next=newNode;
            rear=newNode;
        }
        size++;
    }
    public int deque(){
        if(!isEmpty()){
            int item = front.data;
            front=front.next;
            size--;
            if(isEmpty()){
                rear=null;
            }
            return item;
        }else{
            System.out.println("Queue Underflow");
            return -1;
        }
    }
    public int peek(){
        if(!isEmpty()){
            return front.data;
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
        LinkedQueue aq = new LinkedQueue();
        aq.enqueue(2);
        aq.enqueue(3);
        aq.enqueue(4);
        aq.deque();
        System.out.println(aq.size());
    }
}
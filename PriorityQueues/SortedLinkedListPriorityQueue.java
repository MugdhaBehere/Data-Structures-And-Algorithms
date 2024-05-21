package PriorityQueues;

public class SortedLinkedListPriorityQueue {
    private Node head;
    private class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public void enqueue(int item){
        Node newNode=new Node(item);
        if(head==null||item<head.data){
            newNode.next=head;
            head=new Node(10);
        }else{
            Node current =head;
            while(current.next!=null && item>current.next.data){
                current=current.next;
            }
            newNode.next=current.next;
            current.next=newNode;
        }
    }
    public int dequeue(){
        if(!isEmpty()){
            int item=head.data;
            head=head.next;
            return item;
        }else{
            System.out.println("Priority Queue is Empty");
            return -1;
        }
    }
    public boolean isEmpty(){
        return head==null;
    }
    public int size(){
        int count=0;
        Node current=head;
        while(current!=null){
            count++;
            current=current.next;
        }
        return count;
    }
    
    public static void main(String args[]) {
        SortedLinkedListPriorityQueue priorityQueue = new SortedLinkedListPriorityQueue();
        priorityQueue.enqueue(4);
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(7);
        priorityQueue.enqueue(1);
        priorityQueue.enqueue(9);
        System.out.println("Priority Queue Size" + priorityQueue.size());
        System.out.println("Dequeue:" + priorityQueue.dequeue());
        System.out.println("PriorityQueue size:" + priorityQueue.size());
    }
}

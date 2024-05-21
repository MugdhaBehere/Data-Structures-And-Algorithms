package Stacks;
public class LinkedListStack {
    private Node top;
    private class Node {
        int data;
        Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public void push(int item){
        Node newNode=new Node(item);
        newNode.next=top;
        top=newNode;
    }
    public boolean isEmpty(){
        return top==null;
    }
    
    
    public int pop(){
        if(isEmpty()){
            System.out.println("Stack Underflow");
            return -1;
        }
        int item=top.data;
        top=top.next;
        return item;
    }
    
    public int peek(){
        if(isEmpty()){
            System.out.println("Stack is Empty");
            return -1;
        }
        return top.data;
 
    }
    public int size(){
        int count=0;
        Node current=top;
        while(current !=null){
            count++;
            current=current.next;
            
        }
        return count;
    }
    public static void main(String args[]){
        LinkedListStack st=new LinkedListStack();
        st.push(10);
        st.push(20);
        st.push(5);
        st.push(7);
        int p = st.pop();
        System.out.println("Popped Out" + " " + p);
        int r = st.peek();
        System.out.println(r);
        System.out.println(st.isEmpty());
        int s = st.size();
        System.out.println(s);
    }
}
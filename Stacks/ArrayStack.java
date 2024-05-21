package Stacks;

public class ArrayStack {
    private static final int MAX_SIZE=100;
    private int top;
    private int [] stackArray;
    public ArrayStack(){
        this.top=-1;
        this.stackArray=new int[MAX_SIZE];
        
    }
    public void push(int item){
        if(top<MAX_SIZE-1){
            stackArray[++top]=item;
            
        }
        else{
            System.out.println("Stack Overflow");
            
        }
    }
    public int pop(){
        if(top>=0){
            return stackArray[top--];
        }
        else{
            System.out.println("Stack Underflow");
            return -1;
        }
    }
    public int peek(){
        if(top>=0){
            return stackArray[top];
        }
        else{
            System.out.println("Stack is empty");
            return -1;
        }
    }
    public boolean isEmpty(){
        return top<0;
        
    }
    public int size(){
        return top+1;
    }
    public static void main(String args[]){
        ArrayStack st=new ArrayStack();
        st.push(10);
        st.push(20);
        st.push(5);
        st.push(7);
       int p= st.pop();
        System.out.println("Popped Out"+ " "+ p);
        int r=st.peek();
        System.out.println(r);
        System.out.println(st.isEmpty());
        int s=st.size();
        System.out.println(s);        
    }
}

package Stacks;
import java.util.ArrayList;
public class ArrayListStack {
    private ArrayList<Integer> stack;
    public ArrayListStack(){
        this.stack=new ArrayList<>();
    }
    public void push(int item){
        stack.add(item);
    }
    public boolean isEmpty(){
        return stack.isEmpty();
    }
    public int pop(){
        if(!isEmpty()){
            int item=stack.remove(stack.size()-1);
            return item;
        }
        else {
            System.out.println("Stack Underflow");
            return -1;
        }
    }
    public int peek(){
        if(!isEmpty()){
            return stack.get(stack.size()-1);
        } else {
            System.out.println("Stack is empty");
            return -1;
        }
        }
        public int size(){
            return stack.size();
        }
        public static void main(String args[]){
            ArrayListStack st=new ArrayListStack();
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

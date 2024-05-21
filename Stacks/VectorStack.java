package Stacks;
import java.util.Vector;
public class VectorStack {
    private Vector<Integer> stack;
    public VectorStack(){
        this.stack=new Vector<>();
    }
    public void push (int item){
        stack.add(item);
    }
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    public int pop(){
        if(!isEmpty()){
            int item = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return item;
        }else{
            System.out.println("Stack Underflow");
            return -1;
        }
    }
    public int peek(){
        if(!isEmpty()){
            return stack.get(stack.size()-1);
        }else{
            System.out.println("Stack is empty");
            return -1;
        }
    }
    public int size(){
        return stack.size();
    }
    public static void main(String args[]){
        VectorStack vs= new VectorStack();
        vs.push(1);
        vs.push(2);
        vs.push(3);
        vs.pop();
        vs.peek();
        int s=vs.size();
        System.out.println("Size of stack:" + s);
        
    }
}

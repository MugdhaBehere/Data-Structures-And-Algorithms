package Stacks;
import java.util.ArrayDeque;
public class DequeStack {
    
        private ArrayDeque<Integer>stack;
        public DequeStack(){
            this.stack=new ArrayDeque<>();
        }
        public void push(int item){
            stack.push(item);
        }
        public boolean isEmpty(){
            return stack.isEmpty();
        }
        public int pop(){
            if(!isEmpty()){
                return stack.pop();
            }
            else {
                System.out.println("Stack Underflow");
                return -1;
            }
        }
        public int peek(){
            if(isEmpty()){
                return stack.peek();
            }else{
                System.out.println("Stack is empty");
                return -1;
            }
            
        }
        public int size(){
            return stack.size();
        }
        
        public static void main(String args[]){
            DequeStack st= new DequeStack();
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


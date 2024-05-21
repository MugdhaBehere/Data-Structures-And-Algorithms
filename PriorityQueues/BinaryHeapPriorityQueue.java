package PriorityQueues;
public class BinaryHeapPriorityQueue {
    private int capacity;
    private int size;
    private int[] heapArray;
    public BinaryHeapPriorityQueue(int capacity){
        this.capacity=capacity;
        this.size=0;
        this.heapArray=new int[capacity];
    }
    public void enqueue(int item){
        if(size==capacity){
            System.out.println("Priority Queue Overflow");
            return;
        }
        size++;
        int index=size-1;
        heapArray[index]=item;
        while(index>0 && heapArray[parent(index)]>heapArray[index]){
            swap(index, parent(index));
            index=parent(index);
        }
    }
    public int deque(){
        if(isEmpty()){
            System.out.println("Priority Queue Undeflow");
            return -1;
        }
        if(size==1){
            size--;
            return heapArray[0];
        }
        int root=heapArray[0];
        heapArray[0]=heapArray[size-1];
        size--;
        heapifyDown(0);
        return root;
    }
    public int peek(){
        if(isEmpty()){
            return heapArray[0];
        }else{
            System.out.println("Priority Queue is Empty");
            return -1;
        }
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void heapifyDown(int index){
        int leftChild=leftChild(index);
        int rightChild=rightChild(index);
        int smallest=index;
        if(leftChild<size && heapArray[leftChild]<heapArray[index]){
            smallest=leftChild;
        }
    
    if(rightChild<size && heapArray[rightChild]<heapArray[smallest]){
            smallest=rightChild;    
    }
    if(smallest!=index){
        swap(index, smallest);
        heapifyDown(smallest);
    }
}
    private void swap(int i, int j){
        int temp=heapArray[i];
        heapArray[i]=heapArray[j];
        heapArray[j]=temp;
    }
    private int parent(int i){
        return (i-1)/2;
    }
    private int leftChild(int i){
        return 2*i+1;
    }
    private int rightChild(int i){
        return 2*i+2;
    }
    public static void main(String args[]){
        BinaryHeapPriorityQueue priorityQueue = new BinaryHeapPriorityQueue(10);
        priorityQueue.enqueue(4);
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(7);
        priorityQueue.enqueue(1);
        priorityQueue.enqueue(9);
        System.out.println("Priority Queue Size" + priorityQueue.size());
        System.out.println("Dequeue:"+ priorityQueue.deque());
        System.out.println("Peek" + priorityQueue.peek());
        System.out.println("PriorityQueue size:" + priorityQueue.size());
    }
}

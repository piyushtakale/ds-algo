package queues;

public class Queue <T>{
    private int capacity;
    private T[] queue;
    private int front;
    private int rear;
    public Queue(){this(10);}
    public Queue(int capacity){
        this.queue = (T[]) new Object[capacity];
        this.front = this.rear = -1;
        this.capacity = capacity;
    }
    public boolean isEmpty(){
        return this.front == -1;
    }
    public boolean isFull(){
        return rear+1 == capacity;
    }
    public boolean enqueue(T element){
        if(isFull()){
            return false;
        }
        else if(isEmpty()){
            queue[++front] = element;
            rear++;
            return true;
        }
        else{
            queue[++rear] = element;
            return true;
        }
    }
    public T front(){
        if(isEmpty()){return null;}
        else{
            return queue[front];
        }
    }
    public T dequeue(){
        if(isEmpty()){return null;}
        else{
            T temp = queue[front];
            queue[front++] = null;
            if(front == rear){
                front = rear = -1;
            }
            return temp;
        }
    }
    public void printer(){
        if(isEmpty()){
            System.out.println("NULL");
        }
        else{
            for(T ele: queue){
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
    public static void main(String args[]){
        Queue<String> q = new Queue<>();
        System.out.println(q.enqueue("piyush"));
        System.out.println(q.enqueue("takakle"));
        System.out.println(q.enqueue("this"));
        q.printer();
        System.out.println("\n" + q.isFull());
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.dequeue());
        q.printer();

        System.out.println(q.enqueue("piyush"));
        System.out.println(q.enqueue("takakle"));
        System.out.println(q.enqueue("this"));
        System.out.println(q.enqueue("piyush"));
        System.out.println(q.enqueue("takakle"));
        System.out.println(q.enqueue("this"));
        System.out.println(q.enqueue("piyush"));
        System.out.println(q.enqueue("takakle"));
        System.out.println(q.enqueue("this"));
        q.printer();
        System.out.println(q.isFull());
        System.out.println(q.enqueue("takakle"));
        System.out.println(q.enqueue("this"));
        q.printer();
        System.out.println(q.isFull());
    }
}

package queues;

public class CircularQueue <T>{
    private int capacity;
    private T queue[];
    private int front;
    private int rear;

    CircularQueue(){ this(10); }
    CircularQueue(int capacity){
        this.capacity = capacity;
        this.front = this.rear = -1;
        queue = (T[]) new Object[capacity];
    }

    public boolean isEmpty(){ return front == -1; }

    public boolean isFull(){ return front == (rear+1) % capacity; }

    public boolean enqueue(T element){
        if (isFull()){ return false; }
        else if (isEmpty()){
            front = rear = 0;
            queue[0] = element;
        }
        else{
            rear = (rear + 1) % capacity;
            queue[rear] = element;
        }
        return true;
    }
    public T dequeue(){
        if (isEmpty()){return null;}
        else{
            T temp = queue[front];
            queue[front] = null;
            front = (front+1) % capacity;
            if(isFull()){
                front = rear = -1;
            }
            return temp;
        }
    }
    public void printer(){
        if(isEmpty()){
            System.out.println("NULL | front : "+ front+ " | rear : " + rear);
        }
        else{
            for(T ele: queue){
                System.out.print(ele + " ");
            }
            System.out.print("| front : "+ front+ " | rear : " + rear);
            System.out.println();
        }
    }
    public static void main(String args[]){
        CircularQueue<Integer> q = new CircularQueue(5);
        System.out.println(q.enqueue(5));
        System.out.println(q.enqueue(10));
        q.printer();
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.enqueue(5));
        System.out.println(q.enqueue(10));
        System.out.println(q.enqueue(7));
        q.printer();
        System.out.println(q.enqueue(23));
        System.out.println(q.enqueue(21));
        q.printer();
        System.out.println(q.enqueue(10));
        q.printer();
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.enqueue(17));
        q.printer();
        System.out.println(q.enqueue(19));
        q.printer();
        System.out.println(q.enqueue(13));
        q.printer();
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.dequeue());
        q.printer();
        System.out.println(q.enqueue(27));
        q.printer();

    }
}


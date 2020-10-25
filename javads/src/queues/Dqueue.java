package queues;

public class Dqueue <T>{
    private int size;
    private int capacity;
    private T[] dqueue;
    private int front, rear;

    public Dqueue(){this(5);}
    public Dqueue(int capacity){
        this.capacity = capacity;
        this.front = this.rear = -1;
        this.dqueue = (T[]) new Object[capacity];
    }

    public boolean isEmpty(){ return front == -1; }

    public boolean isFull(){ return (rear + 1) % capacity == front; }

    public T front(){ return front != -1 ? dqueue[front] : null; }

    public T rear(){ return rear != -1 ? dqueue[rear] : null; }

    public boolean enqueueEnd(T element){
        if(!isFull()){
            if(isEmpty())
                front = rear = 0;
            else
                rear = (rear + 1) % capacity;
            dqueue[rear] = element;
            return true;
        }
        return false;
    }

    public T dequeueFront(){
        if(!isEmpty()){
            T temp = dqueue[front];
            dqueue[front] = null;
            front = (front + 1) % capacity;
            if(isFull()) front = rear = -1;
            return (T)temp;
        }
        return null;
    }

    public boolean enqueueFront(T element){
        if(!isFull()){
            if(isEmpty()){
                front = rear = 0;
            }else{
                if(front == 0){
                    front = capacity-1;
                } else{
                    front--;
                }
            }
            dqueue[front] = element;
            return true;
        }
        return false;
    }

    public T dequeueEnd(){
        if(!isEmpty()){
            T temp = dqueue[rear];
            dqueue[rear] = null;
            if(rear == 0) rear = capacity-1;
            else rear--;
            if (isFull()) front = rear = -1;
            return (T)temp;
        }
        return null;
    }

    public void printer(){
        if(isEmpty()){
            System.out.println("NULL | front : "+ front+ " | rear : " + rear);
        }
        else{
            for(T ele: dqueue){
                System.out.print(ele + " ");
            }
            System.out.print("| front : "+ front+ " | rear : " + rear);
            System.out.println();
        }
    }

    public static void main(String args[]){
        Dqueue<Integer> d = new Dqueue<>();
        System.out.println(d.enqueueEnd(5));
        System.out.println(d.enqueueEnd(11));
        System.out.println(d.enqueueEnd(7));
        d.printer();
        System.out.println(d.dequeueFront());
        d.printer();
        System.out.println(d.dequeueFront());
        d.printer();
        System.out.println(d.dequeueFront());
        d.printer();
        System.out.println(d.enqueueEnd(5));
        System.out.println(d.enqueueEnd(11));
        System.out.println(d.enqueueEnd(7));
        System.out.println(d.enqueueEnd(31));
        System.out.println(d.enqueueEnd(23));
        System.out.println(d.enqueueEnd(77));
        d.printer();
        System.out.println(d.dequeueFront());
        d.printer();
        System.out.println(d.enqueueEnd(19));
        d.printer();
        System.out.println(d.enqueueEnd(55));
        d.printer();
        System.out.println(d.dequeueFront());
        d.printer();
        System.out.println(d.dequeueFront());
        d.printer();
        System.out.println(d.dequeueFront());
        d.printer();
        System.out.println(d.dequeueFront());
        d.printer();
        System.out.println(d.dequeueFront());
        d.printer();

        // dequeueEnd & enqueueFront
        System.out.println(d.enqueueFront(5));
        System.out.println(d.enqueueFront(7));
        d.printer();
        System.out.println(d.dequeueEnd());
        d.printer();
        System.out.println(d.enqueueFront(23));
        System.out.println(d.enqueueFront(19));
        d.printer();
        System.out.println(d.enqueueEnd(53));
        System.out.println(d.enqueueEnd(21));
        d.printer();
        System.out.println(d.enqueueFront(5));
        d.printer();
        System.out.println(d.dequeueEnd());
        d.printer();
        System.out.println(d.dequeueEnd());
        d.printer();
        System.out.println(d.dequeueEnd());
        d.printer();
        System.out.println(d.dequeueEnd());
        System.out.println(d.dequeueEnd());
        d.printer();
        System.out.println(d.enqueueEnd(55));
        System.out.println(d.front() + " " + d.rear());
        System.out.println(d.enqueueEnd(21));
        System.out.println(d.front() + " " + d.rear());
        d.printer();
        System.out.println(d.dequeueFront());
        System.out.println(d.front() + " " + d.rear());
        d.printer();
        System.out.println(d.dequeueEnd());
        d.printer();
        System.out.println(d.front() + " " + d.rear());


    }
}

package queues;

import linked_list.SinglyList;

import java.util.LinkedList;

public class QueueLL <T> extends SinglyList {
    private int size;
    private int capacity;
    public QueueLL(){ this(5); }
    public QueueLL(int capacity){
        super();
        this.size = 0;
        this.capacity = capacity;
    }
    public int size(){return size;}

    public boolean enqueue(T element){
        if (size < capacity) {
            if (insertEnd((Integer) element)) {
                size++;
                return true;
            }
        }
        return false;
    }

    public T dequeue(){
        T temp = (T)deleteFront();
        if (temp != null) size--;
        return temp;
    }

    public String printer(){
        String s = super.printer();
        s = s.replace("head", "front");
        s = s.replace("tail", "rear");
        return s;
    }

    public static void main(String args[]){
        QueueLL<Integer> q = new QueueLL<>();
        System.out.println(q.enqueue(5));
        System.out.println(q.enqueue(7));
        System.out.println(q.printer());
        System.out.println(q.size());
        System.out.println(q.dequeue());
        System.out.println(q.printer());
        System.out.println(q.enqueue(11));
        System.out.println(q.enqueue(13));
        System.out.println(q.printer());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.printer());
        System.out.println(q.dequeue());
        System.out.println(q.size());
    }

}

package linked_list;

public class CircularLinkedList <T>{
    private class Node <T>{
        T data ;
        Node next;
        Node(T data){
            this.data = data;
        }
    }
    private Node head;
    private int size;
    private Node tail;

    public boolean isEmpty(){ return head == null; }

    public boolean insertFront(T element){
        if(isEmpty()){
            head = new Node(element);
            head.next = head;
            tail = head;
        }
        else{
            Node n = new Node(element);
            n.next = head;
            head = n;
            tail.next = head;
        }
        return true;
    }

    public boolean insertEnd(T element){
        if(isEmpty()){
            return insertFront(element);
        }
        else{
            Node n = new Node(element);
            n.next = head;
            tail.next = n;
            tail = n;
            return true;
        }
    }

    public T deleteFront(){
        if(isEmpty()) { return null; }
        else{
            T n = (T) head.data;
            if (head == tail) {
                head = tail = null;
                return n;
            }
            head = head.next;
            tail.next = head;
            return n;
        }
    }

    public T deleteEnd(){
        if(isEmpty()){
            return null;
        }
        else{
            T t;
            Node n = head;
            if(head == tail){
                t = (T)head.data;
                head = tail = null;
                return t;
            }
            while(n.next != tail){
                n = n.next;
            }
            t = (T) n.next.data;
            n.next = head;
            tail = n;
            return t;
        }
    }

    public T getTail(){
        if(isEmpty()){ return null; }
        else { return (T)tail.data; }
    }

    public T getHead(){
        if(isEmpty()){ return null; }
        else { return (T)head.data; }
    }
    public  String printer(){
        String s = "head -> ";
        if(isEmpty()){ return s+" None <- tail"; }
        Node j = head;
        while (j.next != head){
            s += j.data + " -> ";
            j = j.next;
        }
        s += tail.data + " <- tail";
        return s;
    }


    public static void main(String args[]){
        CircularLinkedList<Integer> l = new CircularLinkedList();
        System.out.println(l.insertFront(5));
        System.out.println(l.insertEnd(10));
        System.out.println(l.insertFront(11));
        System.out.println(l.insertEnd(13));
        System.out.println(l.insertEnd(17));
        System.out.println(l.insertEnd(23));
        System.out.println(l.printer());
        System.out.println("head : " + l.getHead() + " | type : "+l.getHead().getClass().getName());
        System.out.println("tail : " + l.getTail() + " | type : "+l.getTail().getClass().getName());
        System.out.println("deleteFront type : " + l.deleteFront().getClass().getName());
        System.out.println("deleteFront : " + l.deleteFront());
        System.out.println(l.printer());
        System.out.println("deleteEnd : "+ l.deleteEnd());
        System.out.println(l.printer());
        System.out.println("deleteEnd : "+ l.deleteEnd());
        System.out.println(l.printer());
        System.out.println("deleteFront : " + l.deleteFront());
        System.out.println(l.printer());
        System.out.println("deleteFront : " + l.deleteFront());
        System.out.println(l.printer());
        System.out.println(l.insertEnd(43));
        System.out.println(l.printer());
        System.out.println("deleteFront : " + l.deleteEnd());
        System.out.println(l.printer());
        System.out.println("deleteFront : " + l.deleteEnd());
        System.out.println(l.printer());
    }


}

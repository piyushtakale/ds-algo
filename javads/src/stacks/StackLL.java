package stacks;

import linked_list.SinglyList;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

//class StackIterator<T> implements Iterable{
//    Node<T> current;
//
//}

public class StackLL <T> {

    class Node <T>{
        private T data;
        private Node next;
        public Node(T element){
            this.data = element;
            this.next = null;
        }
    }

    private Node head;
    private int capacity;
    private int size;
    public StackLL(){this(5);}
    public StackLL(int capacity){
        this.head = null;
        this.capacity = capacity;
        size = 0;
    }

    public boolean isEmpty(){ return size == 0; }

    public boolean isFull(){ return size == capacity; }

    public boolean push(T element){
        if(isFull()){ return false; }

        else if(isEmpty()){
            head = new Node(element);
            size++;
        }

        else{
            Node n = new Node(element);
            n.next = head;
            head = n;
            size++;
        }
        return true;
    }
    public T pop(){
        if(isEmpty()){return null;}
        else{
            T temp = (T)head.data;
            head = head.next;
            size--;
            return temp;
        }
    }

    public T top(){
        if (isEmpty()) {return null;}
        else{ return (T)head.data; }
    }




    public void clear(){
        if(!isEmpty()){
            while (head != null){ pop();}
        }
    }

    public int size(){return this.size;}

    public  void printer(){
        String s = "top -> ";
        if(isEmpty()){
            System.out.println(s+"| EMPTY | "); }
        else{
            Node n = head;
            System.out.println(s +"| "+ head.data + " |");
            n = n.next;
            while(n != null){
                System.out.println("       | "+ n.data + " |");
                n = n.next;
            }
        }

    }

    public static void main(String args[]){
        StackLL<Integer> s = new StackLL();
        System.out.println(s.push(41));
        s.printer();
        System.out.println(s.push(59));
        System.out.println(s.push(31));
        System.out.println(s.push(17));
        s.printer();
        System.out.println("top : "+ s.top());
        System.out.println("popped : "+ s.pop());
        s.printer();
        System.out.println(s.push(17));
        System.out.println(s.push(23));
        s.printer();
        System.out.println(s.push(19));
        System.out.println("popped : "+ s.pop());
        s.printer();
        System.out.println("popped : "+ s.pop());
        System.out.println("popped : "+ s.pop());
        System.out.println("popped : "+ s.pop());
        s.printer();
        System.out.println("popped : "+ s.pop());
        s.printer();
        System.out.println("popped : "+ s.pop());

        StackLL<String> st = new StackLL<>();
        System.out.println("is empty : "+ st.isEmpty());
        System.out.println(st.push("piyush takale"));
        System.out.println(st.push("mast challay"));
        System.out.println(st.push("coding is not easy"));
        System.out.println(st.push("are bhai bhai bhai"));
        System.out.println(st.push("dil dooba dil dooba"));
        System.out.println("is full : "+st.isFull());
        st.printer();
        System.out.println(st.push("trying to push while full"));
        System.out.println("popped : " + st.pop());
        System.out.println("top : "+ st.top());
        System.out.println("before clear : size : "+st.size());
        st.printer();

        st.clear();
        System.out.println("after clear : size : "+st.size());
        st.printer();
        System.out.println("popped : " + st.pop());
        System.out.println("size : "+st.size());


    }

}

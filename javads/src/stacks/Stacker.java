package stacks;

import linked_list.DoubleList;

class Stack<T>{
    private int top = -1;
    private T[] arr;
    private int capacity = 0;
    public Stack (int capacity){
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
        System.out.println("type");
    }
    public boolean isEmpty(){
        return this.top == -1;
    }
    public boolean isFull(){
        return this.top + 1 == this.capacity;
    }
    public int size(){
        return this.top + 1;
    }
    public boolean push(T element){
        if( !isFull()){
            this.arr[++top] = element;
            return true;
        }
        return false;
    }
    public T pop(){
        return !isEmpty() ? this.arr[top--] : null;
    }
    public T peek(){
        return !isEmpty() ? this.arr[top] : null;
    }

    // optional
    public void clear(){
        for(int i = 0; i <=top;i ++){
            this.arr[i] = null;
        }
        this.top = -1;
    }

    // optional
    public T[] toArray(){
        return this.arr.clone();
    }
    //optional
    public String toString(){
        if (isEmpty())
            return "[]";
        String s = "[";
        for(Object o: this.arr){
            s += ""+o+", ";
        }
        return s + "\b\b]";
    }

}

public class Stacker {
    public static void main(String args[]){
        Stack<Integer> st = new Stack<Integer>(5);

        for (int a: new int[]{1,6,4,6,92}){
            st.push(a);
        }
        System.out.println(st.toString());
        System.out.println("pushing while full: "+ st.push(56));

        System.out.println("pop: "+ st.pop().getClass().getName());
        System.out.println("size: " + st.size());
        System.out.println("peek: " + st.peek());
        st.clear();
        System.out.println("isempty : "+ st.isEmpty());
        System.out.println("tostring: " +st.toString());
        for (int a: new int[]{1,6,4,6,92}){
            st.push(a);
        }

        System.out.println("type of array : "+ st.toArray());
        Object[] a = st.toArray();
        int[] in = new int[st.size()];
        for(int i = 0 ; i < st.size(); i++){
            in[i] = (int) a[i];
        }
        in[2] = 564564;
        System.out.println("og array : "+st.toString());
        System.out.println("getted array : ");
        for (int i: in){
            System.out.print(i+" ");
        }


    }
}


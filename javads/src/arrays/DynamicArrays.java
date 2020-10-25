package arrays;
import java.util.*;
class DArray <T>{
    private T[] arr;
    private int len = 0;
    private int capacity = 0;
    public DArray(){
        this(16);
    }
    public DArray (int capacity){
        this.capacity = capacity;
        this.arr = (T[])new Object[capacity];
    }
    public int size(){
        return len;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public T get(int index){
        return arr[index];
    }
    public void set(int index, T ele){
        arr[index] = ele;
    }
    public void clear(){
        for (int i = 0;i < capacity; i++){
            arr[i] = null;
        }
        len = 0;
    }
    public void add(T element){
        if (len + 1 >= capacity){
            if(capacity == 0) capacity = 2;
            else capacity *= 2;

            T[] newarr = (T[])new Object[capacity];
            for (int i = 0; i< len; i++ ){
                newarr[i] = arr[i];
            }
            arr = newarr;
        }
        arr[len++] = element;
        System.out.println("after adding capacity: "+capacity);
    }
    public T removeAt(int index){
        T data = arr[index];
        capacity = capacity / 2 >= len-1 ? len : capacity;
        if(index >= len || index < 0)throw new IndexOutOfBoundsException();
        T[] newarr = (T[])new Object[capacity];
        for (int i = 0, j = 0; i < len; i++, j++){
            if(i == index) j--;
            else newarr[j] = arr[i];
        }
        arr = newarr;
        len--;
        return data;

    }
    public void insertAt(int index, T ele){
        T data = arr[index];
        System.out.println("length : "+len);
        capacity = len + 1 > capacity ? capacity * 2: capacity;
        System.out.println("capacity will be: "+capacity);
        if(index >= len || index < 0)throw new IndexOutOfBoundsException();
        System.out.println("yetoy ka nay");
        T[] newarr = (T[])new Object[capacity];
        for (int i = 0, j = 0; i < len; i++, j++){
            if(i == index) {
                newarr[j++] = ele;
                newarr[j] = arr[i];
            }
            else newarr[j] = arr[i];
        }
        arr = newarr;
        len++;

    }
    public boolean remove(Object obj){
        for(int i = 0; i< len ;i ++){
            if(arr[i].equals(obj)){
                removeAt(i);
                System.out.println("capacity after removing one element"+capacity);
                return true;
            }
        }

        return false;
    }

    public int indexOf(Object obj){
        for(int i= 0 ; i < len ; i++){
            if (arr[i].equals(obj)){
                return i;
            }
        }
        return -1;
    }
    public boolean contains(int obj){
        return indexOf(obj) != -1;
    }
    public T pop(){
        return removeAt(len-1);

    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder(len).append("[");
        for (int i= 0;i < len-1; i++){
            s.append(arr[i] + ", ");
        }
        s.append(arr[len-1] + "]");
        return s.toString();
    }

}

public class DynamicArrays {
    public static void main(String args[]){
        DArray da = new DArray();
        da.add(20);
        da.add(30);
        da.add(40);
        System.out.println(da.toString());
        da.removeAt(1);
        System.out.println(da.toString());
        da.insertAt(1, 56);
        System.out.println(da.toString());
        da.add("piyush");
        System.out.println(da.toString());
        System.out.println(da.remove("piyush"));
        System.out.println(da.toString());
        da.add('s');
        da.add(56454.465654);

        DArray<Integer> arr = new DArray<>();
        arr.add(65);
        arr.add(20);
        arr.add(30);
        arr.add(40);
        // arr.add("piysh"); // this code will generate error
        // because we are using generics the array can be now only T
        // type we created Integer array now we cannot insert String into integer array
        System.out.println(arr.toString());
        arr.removeAt(1);
        arr.remove(30);
        System.out.println(arr.toString());
        arr.add(69);
        arr.add(59);
        arr.insertAt(1, 56);
        System.out.println(arr.toString());
        System.out.println(arr.size());
        arr.add(60);
        System.out.println(arr.toString());
        System.out.println(arr.size());
        arr.remove(60);
        System.out.println(arr.toString());
        System.out.println(arr.size());
        System.out.println(arr.isEmpty());
        System.out.println(arr.get(2));
        System.out.println(arr.indexOf(40));
        System.out.println(arr.contains(69));
        System.out.println(arr.toString());
        System.out.println(arr.pop());
        System.out.println(arr.toString());

        DArray <String> ds = new DArray<>();
        ds.add("piyush");
        ds.add("takale");
        ds.add("prashant");
        // ds.add(5); // this will give error
        System.out.println(ds.toString());
        ds.add("rekha");
        ds.add("kartiki");
        ds.insertAt(3, "ok");
        System.out.println(ds.toString());
        ds.remove("ok");
        System.out.println(ds.toString());

    }
}

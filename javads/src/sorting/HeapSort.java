package sorting;

public class HeapSort {
    private int arr[];
    private int size;
    private int rcalls;
    public HeapSort(int[] arr){
        this.arr = arr;
        this.size = arr.length;
        sort();
    }

    private void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void sort(){
        // heapify
        for(int i = size / 2 - 1; i >= 0; i--){ adjustDown(i); }

        //extract-max till size becomes 1
        int len = size;
        if(size > 1){
            while(size > 1){
                swap(size-1, 0);
                size--;
                adjustDown(0);
            }
        }
        size = len;
    }

    public void adjustDown(int parent){
        rcalls++;
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        int max;
        if(right <= size-1){
            if(arr[left] > arr[right]) max = left;
            else max = right;
            if (arr[max] > arr[parent]) {
                swap(parent, max);
                if(max * 2 + 2 <= size) adjustDown(max);
            }
        }
        else{
            if(arr[left] > arr[parent]){
                swap(parent, left);
            }
        }
    }

    public void printer(){
        System.out.println("merge sorted with " + rcalls + " recursive calls");
        for(int i = 0;i < size; i++){
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    public static void main(String args[]){
        int[] arr = {20,4,3,82,4,6,89,100,6,1, 56, 77, 12, 89, 44, 23, 75, 1, 97};
        HeapSort b = new HeapSort(arr);
        b.printer();
        //new SelectionSort(arr); // just this will also change the original array

        for(int i = 0;i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
    }
}

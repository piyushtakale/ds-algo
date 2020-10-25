package sorting;

import org.w3c.dom.ls.LSOutput;

public class SelectionSort {
    private int arr[];
    private int size;

    public SelectionSort(int[] arr){
        this.arr = arr;
        size = arr.length;
        sort();
    }

    public void sort(){
        for(int i = 0; i < size-1; i++){
            int min = i;
            for(int j = i; j < size; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i){
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public void printer(){
        for(int i = 0;i < size; i++){
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    public static void main(String args[]){
        int[] arr = {20,4,3,82,4,6,89,6,1};
        SelectionSort b = new SelectionSort(arr);
        b.printer();
        //new SelectionSort(arr); // just this will also change the original array



        for(int i = 0;i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
    }

}

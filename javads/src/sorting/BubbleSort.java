package sorting;

import org.w3c.dom.ls.LSOutput;

public class BubbleSort {
    private int arr[];
    private int size;

    public BubbleSort(int[] arr){
        this.arr = arr;
        size = arr.length;
        sort();
    }

    public void sort(){
        for(int i = 0; i < size-1; i++){
            for (int j =0 ; j < size - i-1; j++){
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
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
        BubbleSort b = new BubbleSort(arr);
        b.printer();
        //new BubbleSort(arr); // just this will also change the original array

        for(int i = 0;i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
    }

}

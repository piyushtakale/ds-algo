package sorting;

import java.lang.reflect.Array;

public class MergeSort {
    private int arr[];
    private int temp[];
    private int size;
    private int rcalls;
    public MergeSort(int[] arr){
        this.arr = arr;
        this.temp = new int[arr.length];
        this.size = arr.length;
        rcalls = -1;
        sort(0, size-1);

    }

    private void sort(int left_start, int right_end){
        rcalls++;
        if(right_end - left_start == 1){
            if(arr[right_end] < arr[left_start]){
                int temp = arr[right_end];
                arr[right_end] = arr[left_start];
                arr[left_start] = temp;
            }
            return;
        }
        if(left_start < right_end){
            int mid = (left_start + right_end) / 2;

            sort(left_start, mid);
            sort(mid+1, right_end);

            merge(left_start, mid, mid+1, right_end);
        }
    }

    private void merge(int left_start, int left_end, int right_start, int right_end){
        int i = left_start, j = right_start, index = left_start;

        while(i <= left_end && j <= right_end){
            if(arr[i] <= arr[j]){
                temp[index] = arr[i];
                i++;
            }
            else{
                temp[index] = arr[j];
                j++;
            }
            index++;
        }

        while(i <= left_end){ temp[index++] = arr[i++]; }
        while(j <= right_end){ temp[index++] = arr[j++]; }

        while(left_start <= right_end){ arr[left_start] = temp[left_start++]; }
    }

    public void printer(){
        System.out.println("merge sorted with " + rcalls + " recursive calls");
        for(int i = 0;i < size; i++){
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    public static void main(String args[]){
        int[] arr = {20,4,3,82,4,6,89,6,1, 56, 77, 12, 89, 44, 23, 75, 1, 97};
        MergeSort b = new MergeSort(arr);
        b.printer();
        //new SelectionSort(arr); // just this will also change the original array

        for(int i = 0;i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
    }
}


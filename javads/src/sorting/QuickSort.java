package sorting;

public class QuickSort {
    private int arr[];
    private int size;
    private int rcalls;

    public QuickSort(int[] arr){
        this.arr = arr;
        this.size = arr.length;
        sort(0, size-1);
        rcalls = -1;
    }

    public void sort(int low, int high){
        if(low < high){
            int mid = partiton(low, high);
            sort(low, mid-1);
            sort(mid+1, high);
        }
    }
    private void swap(int a, int b){
        int temp = arr[a] ;
        arr[a] = arr[b];
        arr[b] = temp;
    }
    private int partiton(int low, int high){
        // generating random pivote which will avoid first or last always max min
        // swapping with the high to avoid more complicated structuring
        if(high - low > 2) swap((int) ((Math.random() * (high - low)) + low), high);
        // we will only do this step if the number of accessible elements
        // is more that 2 or a certain threshold to reduce computations

        int pivote = arr[high];
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(arr[j] < pivote){
                i++;
                swap(i, j);
            }
        }
        if(i+1 != high){
            swap(i+1, high);
        }
        return i+1;
    }

    public void printer(){
        for(int i = 0;i < size; i++){
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    public static void main(String args[]){
        int[] arr = {20,4,3,82,4,6,89,6,1, 56, 77, 12, 89, 44, 23, 75, 1, 97};
        QuickSort b = new QuickSort(arr);
        b.printer();
        //new SelectionSort(arr); // just this will also change the original array

        for(int i = 0;i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
    }
}

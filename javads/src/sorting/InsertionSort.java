package sorting;

public class InsertionSort {
    private int[] arr;
    private int size;
    private int counter;

    public InsertionSort(int arr[]){
        this.arr = arr;
        this.size = arr.length;
        this.counter = 0;
        sort();
    }

    private void sort(){
        int key;
        int j;
        for(int i = 0; i < size ;i++){
            key = arr[i];
            j = i - 1;

            while(j >= 0 && arr[j] > key){
                counter++;
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public void printer(){
        System.out.println("count sorted with " + counter + " iterations");
        for(int i = 0;i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    public static void main(String args[]){
        int[] arr = {20,4,3,82,4,6,89,100,6,1, 56, 77, 12, 89, 44, 23, 75, 1, 97};
        InsertionSort b = new InsertionSort(arr);
        b.printer();
        //new SelectionSort(arr); // just this will also change the original array

        for(int i = 0;i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
    }
}

package sorting;

public class RadixSort {

    private int[] arr;
    private int size;
    private int counter;

    public RadixSort(int[] arr){
        this.arr = arr;
        this.size = arr.length;
        this.counter = 0;
        sort();
    }

    private void sort(){
        int max = arr[0];
        for(int element : arr){ if(element > max) max = element; }

        for(int ex = 1; max / ex > 0; ex *= 10){
            countSort(ex);
        }
    }

    private void countSort(int ex){
        int i = 0;
        int[] count = new int[10];
        int[] temp = arr.clone();

        for(i = 0; i < size; i++){
            count[(arr[i] / ex) % 10]++;
        }

        for(i = 1; i < 10; i++){
            count[i] += count[i - 1];
        }

        for(i = size-1; i >= 0; i--){
            arr[ --count[ (temp[i] / ex) % 10 ] ] = temp[i];
        }

        temp = null;
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
        RadixSort b = new RadixSort(arr);
        b.printer();
        //new SelectionSort(arr); // just this will also change the original array

        for(int i = 0;i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
    }
}

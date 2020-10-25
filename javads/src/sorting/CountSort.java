package sorting;

public class CountSort {
    private int[] count;
    private char[] arr;
    private int counter;
    public CountSort(char[] iarr){
        this.count = new int[256];
        this.arr = iarr;
        countsort(iarr);
    }

    private void countsort(char[] iarr){
        int n = arr.length;
        for(int i = 0; i < n; i++){
            ++count[arr[i]];
        }
        int index = 0;
        for(int i = 0; i < 256; i++){
            counter++;
            for(int j = 0; j < count[i]; j++, index++){
                counter++;
                arr[index] = (char) i;
            }
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
        char[] arr = {'A', 'b', 'd', 'f', 'c', '0', 't', '5', 'C', 'b', 'n', 'N', 'Q', 'T', 'r', 'p', 'a'};
        CountSort b = new CountSort(arr);
        b.printer();
        //new SelectionSort(arr); // just this will also change the original array

        for(int i = 0;i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
    }
}

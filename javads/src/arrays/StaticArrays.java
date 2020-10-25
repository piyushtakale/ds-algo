package arrays;
import java.util.Arrays;

public class StaticArrays {
    public static void arrayprinter(int[] arr){
        for (int i: arr
        ) {
            System.out.print(i+"  ");
        }
    }
    public static int[] squarer(int[] brr){
        for (int i = 0; i< brr.length; i++){
            brr[i] = brr[i] * brr[i];
        }
        return brr;
    }
    public static void main(String args[]){
        int[] ar = {};
//        ar[0] = 10;
        System.out.println(ar);
//        Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
        // array elements are initialized with zero 0
        int[] arr = new int[100];
        System.out.println("accesing 4th element of empty array: "+arr[4]);
        System.out.println("empty array: "+arr);

        int[] brr = {10,20,4,1,3,5,9,8,8,895,4,5,6,6};
        int maxindex = 0;
        for (int i = 0; i< brr.length; i++){
            if(brr[i] > brr[maxindex])
                maxindex = i;
        }
        System.out.println("\nwith foreach:");
        arrayprinter(brr);
        System.out.println("array\t"+brr+"max indx: "+maxindex+ "\t max number: "+brr[maxindex]);


        System.out.println("\narrays in function");
        arrayprinter(new int[]{654,9,64,5,8,495,65,799,3});

        System.out.println("\n returning arrays form functions");
        int[] middle = squarer(brr);
        arrayprinter(middle);

        Arrays[] a1 = new Arrays[10];

        int findex = Arrays.binarySearch(new int[]{10,22,45,56,78,89,125,231,475,859,955}, 231);
        System.out.println("\nfounded using Arrays.binaryseach : "+findex);

        System.out.println("\narrays.equals");
        arr = new int[]{10, 10, 20, 20, 30, 55, 45, 6566};
        brr = arr.clone(); // if we write brr = arr te they will point tp the same object
        System.out.println("if arrays are euqal");
        arrayprinter(arr);
        arrayprinter(brr);
        System.out.println(Arrays.equals(arr, brr));
        brr[6] = 456645;
        arrayprinter(arr);
        arrayprinter(brr);
        System.out.println(Arrays.equals(arr, brr));

        int[] arrr = new int[10];
        Arrays.fill(arrr, 50);
        System.out.println("\nfilling array by 50");
        arrayprinter(arrr);

        brr = new int[]{10,20,4,1,3,5,9,8,8,895,4,5,6,6};
        Arrays.sort(brr);
        System.out.println("\narray after sorting\n");
        arrayprinter(brr);

    }
}


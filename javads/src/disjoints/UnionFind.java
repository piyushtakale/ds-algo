package disjoints;


import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// rank heuristics
public class UnionFind<T>{
    private HashMap<T, Integer> sets;
    private int[] index;

    public UnionFind(T[] arr){
        this.index = new int[arr.length];
        this.sets = new HashMap<>();
        for (int i = 0; i < arr.length ; i++){
            sets.put(arr[i], i);
            this.index[i] = i;
        }
    }

    public int find(T element){
        int ind = sets.get(element);
        while (index[ind] != ind){
            ind = index[ind];
        }
        return ind;
    }

    public void union(T one, T two){
        int root1 = find(one);
        int root2 = find(two);
        if (root1 != root2){
            int temp = sets.get(two);
            while (index[temp] !=root1){
                int parent = index[temp];
                index[temp] = root1;
                temp = parent;
            }
        }
    }
    public void printer(){
        System.out.println("\nsets are");
        System.out.print("baap : ");
        for(int i = 0; i< index.length ; i++){
            System.out.print("  "+ index[i]);
        }
        System.out.println();
        System.out.print("por  : ");
        for(int i = 0; i < index.length; i++){
            System.out.print("  "+i );
        }


    }

    public static void main(String args[]){
        UnionFind s = new UnionFind(new String[]{"a", "b", "c", "d", "e", "f", "g", "h"});
        s.union("d", "f");
        s.union("h", "b");
        s.union("g", "a");
        s.printer();
        System.out.println();
        s.union("b","a");
        s.printer();
        System.out.println();
        s.union("f","g");
        s.printer();
        System.out.println();
        s.union("e","b");
        s.printer();
        System.out.println(s.find("a"));
        System.out.println(s.find("f"));
        System.out.println(s.find("d"));
        System.out.println(s.find("c"));

    }
}


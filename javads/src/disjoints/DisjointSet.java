package disjoints;

import java.util.function.Function;


// rank heuristics
public class DisjointSet{
    private int[] sets;
    private int size;
    private int[] rank;

    public DisjointSet(int capacity){
        this.sets = new int[capacity];
        this.rank = new int[capacity];
        for( int i = 0 ; i < capacity; i++){
            sets[i] = i;
            rank[i] = 0;
        }
    }
    public DisjointSet(int capacity, int[] arr){
        this.sets = arr.clone();
        this.size = sets.length;
    }

    public int find(int element){
        int temp = element;
        while (element != sets[element]) {
            element = sets[element];
        }
        while(temp != sets[temp]){
            int t = sets[temp];
            sets[temp] = element;
            temp = t;
        }
        return element;
    }

    public void union(int one, int two){
        int first = find(one);
        int second = find(two);
        if(first != second){
            if(rank[first] > rank[second]){
                sets[second] = first;
            }
            else if(rank[first] < rank[second]){
                sets[first] = second;
            }
            else{
                sets[second] = first;
                rank[first]++;
            }
        }
    }
    public void printer(){
        System.out.println("\nsets are");
        System.out.print("baap : ");
        for(int i = 0; i < sets.length; i++){
            System.out.print("  "+sets[i] );
        }
        System.out.println();
        System.out.print("por  : ");
        for(int i = 0; i < sets.length; i++){
            System.out.print("  "+i );
        }


        System.out.println("\nranks are");
        System.out.print("mans : ");
        for(int i = 0; i < sets.length; i++){
            System.out.print("  "+i );
        }
        System.out.println();
        System.out.print("rank : ");
        for(int i = 0; i < sets.length; i++){
            System.out.print("  "+rank[i] );
        }

    }

    public static void main(String args[]){
        DisjointSet s = new DisjointSet(8);
        s.union(3, 5);
        s.union(7, 1);
        s.union(6, 0);
        s.printer();
        System.out.println();
        s.union(1,0);
        s.printer();
        System.out.println();
        s.union(5,6);
        s.printer();
        System.out.println();
        s.union(4,1);
        s.printer();
        System.out.println(s.find(0));
        System.out.println(s.find(5));
        System.out.println(s.find(3));
        System.out.println(s.find(2));
        s.printer();
    }
}

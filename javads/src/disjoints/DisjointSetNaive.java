package disjoints;

public class DisjointSetNaive {
    private int[] sets;
    private int size;
    public DisjointSetNaive(int capacity){
        this.sets = new int[capacity];
        for( int i = 0 ; i < capacity; i++){
            sets[i] = i;
        }
    }
    public DisjointSetNaive(int capacity, int[] arr){
        this.sets = arr.clone();
        this.size = sets.length;
    }

    public int find(int element){
        while (element != sets[element])
            element = sets[element];
        return element;
    }

    public void union(int one, int two){
        int first = find(one);
        int second = find(two);
        if (first <= second)
            sets[second] = first;
        else
            sets[first] = second;
    }

    public static void main(String args[]){
        DisjointSetNaive s = new DisjointSetNaive(5);
        System.out.println(s.find(2));
        s.union(2,4);
        System.out.println("after union 2 and 4");
        System.out.println("f 2 : "+s.find(2));
        System.out.println("f 4 : "+s.find(4));
        s.union(1, 3);
        System.out.println("after union 1 and 3");
        System.out.println("f 1 : "+s.find(1));
        System.out.println("f 3 : "+s.find(3));
        s.union(0, 1);
        System.out.println("after union 0 and 1");
        System.out.println("f 0 : "+s.find(0));
        System.out.println("f 1 : "+s.find(1));
        System.out.println("f 3 : "+s.find(3));
        s.union(2, 3);
        System.out.println("after union 2 and 3");
        System.out.println("f 0 : "+s.find(0));
        System.out.println("f 1 : "+s.find(1));
        System.out.println("f 2 : "+s.find(2));
        System.out.println("f 3 : "+s.find(3));
        System.out.println("f 4 : "+s.find(4));

    }
}

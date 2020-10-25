package hashmap;

import java.util.LinkedList;

public class HashMapOpenAdressingBase <K, V>{
    private static final int DEFAULT_CAPACITY = 7;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int LINEAR_CONSTANT = 1;

    private int capacity, threshold, size = 0;
    private double factor;

    private final K TOMBSTONE = (K) new Object();

    private K[] keys;
    private V[] values;

    public HashMapOpenAdressingBase(){ this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR); }
    public HashMapOpenAdressingBase(int capacity){ this(capacity, DEFAULT_LOAD_FACTOR); }
    public HashMapOpenAdressingBase(int capacity, double factor){
        if (capacity <= 0) throw new IllegalArgumentException("capacity cannot be 0 or less than 0");
        if (factor <= 0 || Double.isNaN(factor) || Double.isInfinite(factor)) throw new IllegalArgumentException("invalid factor");

        this.factor = factor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        this.threshold = (int) (this.factor * this.capacity);
        System.out.println("threshold " + threshold);
        this.keys = (K[]) new Object[this.capacity];
        this.values = (V[]) new Object[this.capacity];
    }

    private int probe(int x){ return LINEAR_CONSTANT * x; }

    public boolean isEmpty(){ return size == 0; }

    public int size(){return size;}

    private int normalizeIndex(int hash){ return (hash & 0x7FFFFFFF) % capacity; }

    private int GCD(int a, int b){
        while(b != 0){
            if (b == 1) return 1;
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public V insert(K key, V value){
        if(key == null) throw new IllegalArgumentException("null key");
        if (size >= threshold) resizeTable();
        int offset = normalizeIndex(key.hashCode());

        for(int i = offset, j = -1, x = 1; ;i = normalizeIndex(offset + probe(x++))){
            if(keys[i] == TOMBSTONE){
                if (j == -1) j = i;
            }
            else if(keys[i] != null){
                if(keys[i].equals(key)){
                    V oldval = values[i];
                    if(j == -1){
                        values[i] = value;
                    }
                    else{
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                        keys[j] = key;
                        values[j] = value;
                    }
                    return oldval;
                }
            }
            else{
                if(j == -1){
                    keys[i] = key;
                    values[i] = value;
                }
                else{
                    keys[j] = key;
                    values[j] = value;
                }
                size++;
                return null;
            }
        }
    }

    public boolean hasKey(K key){
        if(key == null) throw new IllegalArgumentException("null keys");

        int offset = normalizeIndex(key.hashCode());

        for(int i = offset, x = 1, j=-1 ;; i = normalizeIndex(offset + probe(x))){
            if(keys[i] == null) {
                return false;
            }
            else if( keys[i] == TOMBSTONE){
                if(j == -1) j = i;
            }
            else{
                if(key.equals(keys[i])){
                    if(j != -1){
                        values[j] = values[i];
                        keys[j] = keys[i];
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                    }
                    return true;
                }
            }
        }
    }

    public V remove(K key){
        if (key == null) throw new IllegalArgumentException("null key");

        int offset = normalizeIndex(key.hashCode());

        for(int i = offset, x = 1 ;; i = normalizeIndex((offset + probe(x)))){
            if(keys[i]== TOMBSTONE) continue;
            if(keys[i] == null) return null;

            if(keys[i].equals(key)){
                size--;
                V oldvalue = values[i];
                keys[i] = TOMBSTONE;
                values[i] = null;
                return oldvalue;
            }
        }
    }

    public V get(K key){
        if(key == null) throw new IllegalArgumentException("null key");

        int offset = normalizeIndex(key.hashCode());
        for(int i = offset, j = -1, x = 1;; i = normalizeIndex(offset + probe(x++))){
            if(keys[i] == TOMBSTONE){
                if (j == -1) j = i;
            }
            else if(keys[i] != null){
                if(key.equals(keys[i])){
                    if(j != -1){
                        values[j] = values[i];
                        keys[j] = keys[i];
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                        return values[j];
                    }else{
                        return values[i];
                    }
                }
            }
            else return null;
        }
    }

    private void increaseCapacity(){ capacity = (capacity * 2) + 1; }

    private void adjustCapacity(){ while(GCD(capacity, LINEAR_CONSTANT) != 1) capacity++; }

    public void resizeTable(){
        increaseCapacity();
        adjustCapacity();

        threshold = (int)(capacity * factor);

        K[] oldkeys = (K[]) new Object[capacity];
        V[] oldvals = (V[]) new Object[capacity];

        K[] tempk = keys;
        keys = oldkeys;
        oldkeys = tempk;

        V[] tempv = values;
        values = oldvals;
        oldvals = tempv;

        size = 0 ;

        for(int i = 0; i < oldkeys.length ; i++){
            if(oldkeys[i] != null && oldkeys[i] != TOMBSTONE)
                insert(oldkeys[i], oldvals[i]);
            oldvals[i] = null;
            oldkeys[i] = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < capacity; i++)
            if (keys[i] != null && keys[i] != TOMBSTONE) sb.append(keys[i] + " => " + values[i] + ", ");
        sb.append("}");
        return sb.toString();
    }

    public static void main(String args[]){
        HashMapOpenAdressingBase<Integer, String> h = new HashMapOpenAdressingBase();
        System.out.println(h.insert(5, "five"));
        System.out.println(h.size());
        System.out.println("capacity a 5: " + h.capacity);
        System.out.println(h.insert(6, "six"));
        System.out.println(h.size());
        System.out.println("capacity a 6:" + h.capacity);
        System.out.println(h.get(6));
        System.out.println(h.toString());
        System.out.println(h.remove(5));
        System.out.println(h.get(5));
        System.out.println(h.size());
        System.out.println(h.toString());
        System.out.println(h.insert(6, "SIX"));
        System.out.println(h.insert(7, "seven"));
        System.out.println("capacity a 7:" + h.capacity);
        System.out.println(h.insert(10, "ten"));
        System.out.println("capacity a 10:" + h.capacity);
        System.out.println(h.insert(20, "twenty"));
        System.out.println(h.size());
        System.out.println("capacity a 20:" + h.capacity);
        System.out.println(h.toString());
        System.out.println(h.insert(8, "eight"));
        System.out.println(h.size());
        System.out.println("capacity a 8:" + h.capacity);
        System.out.println(h.insert(9, "nine"));
        System.out.println(h.size());
        System.out.println("capacity a 9:" + h.capacity);
        System.out.println(h.toString());
        System.out.println(h.size());
        System.out.println(h.get(10));
        System.out.println(h.remove(10));
        System.out.println(h.capacity);

        System.out.println(h.hasKey(9));
    }
}

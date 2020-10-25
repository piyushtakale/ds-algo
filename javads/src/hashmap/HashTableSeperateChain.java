package hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Entry<K, V>{
    int hash;
    K key; V value;

    public Entry(K key, V value){
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> other){
        if(hash != other.hashCode()) return false;
        return key.equals(other.key);
    }

    public String toString(){
        return key + " => " + value;
    }
}

public class HashTableSeperateChain<K, V> {
    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int capacity, thresold, size = 0;
    private double factor;
    private LinkedList<Entry<K, V>>[] table;


    public HashTableSeperateChain(){this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);}
    public HashTableSeperateChain(int capacity){this(capacity, DEFAULT_LOAD_FACTOR);}
    public HashTableSeperateChain(int capacity, double factor){
        if (capacity <= 0) throw new IllegalArgumentException("capacity cannot be 0 or less than 0");
        if (factor <= 0 || Double.isNaN(factor) || Double.isInfinite(factor)) throw new IllegalArgumentException("invalid factor");

        this.factor = factor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        this.thresold = (int) this.factor * this.capacity;
        this.table = new LinkedList[this.capacity];
    }

    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}

    public int normalizeIndex(int keyhash){
        return (keyhash & 0x7FFFFFFF) % capacity;
    }

    public void clear(){
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean contains(K key){
        return haskey(key);
    }

    public boolean haskey(K key){
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key){
        if(key == null) return null;
        LinkedList<Entry<K,V>> bucket = table[bucketIndex];
        if(bucket == null) return null;
        for (Entry<K, V> e: bucket) if(key.equals(e.key)) return e;
        return null;
    }

    public V insert(K key, V value){
        if (key == null) throw new IllegalArgumentException("null key");
        Entry<K, V> entry = new Entry(key, value);
        int bucketIndex = normalizeIndex(entry.hash);
        return bucketInsertEntry(bucketIndex, entry);
    }

    private V bucketInsertEntry(int bucketIndex, Entry<K, V> new_entry){
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if(bucket == null) table[bucketIndex] = bucket = new LinkedList<>();

        Entry<K, V> old_entry = bucketSeekEntry(bucketIndex, new_entry.key);
        if(old_entry == null){
            bucket.add(new_entry);
            if(++size > thresold) resizeTable();
            return null;
        }else{
            V old_value = old_entry.value;
            old_entry.value = new_entry.value;
            return old_value;
        }
    }

    public V remove(K key){
        if(key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    private V bucketRemoveEntry(int bucketIndex, K key){
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if(entry != null){
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            bucket.remove(entry);
            --size;
            return entry.value;
        }return null;
    }

    private void resizeTable(){
        System.out.println("resizing table");
        capacity *= 2;
        thresold = (int) (capacity * factor);

        LinkedList<Entry<K, V>> [] new_table = new LinkedList[capacity];

        for( int i = 0 ; i < table.length ; i++){
            if(table[i] != null){
                for (Entry<K, V> entry : table[i]){
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K, V>> bucket = new_table[bucketIndex];
                    if(bucket == null) new_table[i] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }
                table[i].clear();
                table[i] = null;
            }
        }
        System.out.println(capacity);
        table = new_table;
    }

    public List<K> keys(){
        List<K> keys = new ArrayList<>(size);
        for(LinkedList<Entry<K, V>> bucket : table)
            if(bucket != null) for(Entry<K, V> entry : bucket) keys.add(entry.key);
        return keys;
    }

    public List<V> values(){
        List<V> values = new ArrayList<>(size);
        for(LinkedList<Entry<K, V>> bucket : table)
            if(bucket != null) for(Entry<K, V> entry : bucket) values.add(entry.value);
        return values;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) continue;
            for (Entry<K, V> entry : table[i]) sb.append(entry + ", ");
        }
        sb.append("}");
        return sb.toString();
    }


    public static void main(String args[]){
        HashTableSeperateChain<Integer, String> h = new HashTableSeperateChain<>(5);
        System.out.println(h.toString());
        h.insert(5, "five");
        System.out.println(h.toString());
        h.insert(6, "six");
        System.out.println(h.toString());
        h.insert(1, "one");
        System.out.println(h.toString());
        h.insert(2, "two");
        System.out.println(h.toString());
        h.insert(3, "three");
        System.out.println(h.toString());

        System.out.println(h.insert(3, "Three"));
        System.out.println(h.insert(4, "four"));

        System.out.println(h.toString());

        System.out.println(h.remove(56));
        System.out.println(h.remove(1));
        System.out.println(h.toString());

        System.out.println(h.contains(5));
        System.out.println(h.contains(66));

        List<Integer> keys = h.keys();
        System.out.println(keys.toString());

        List<String> values = h.values();
        System.out.println(values.toString());
    }
}

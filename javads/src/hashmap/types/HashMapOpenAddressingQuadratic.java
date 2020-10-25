package hashmap.types;

public class HashMapOpenAddressingQuadratic <K, V>{
    private static final int DEFAULT_CAPACITY = 7;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int capacity, threshold, size = 0;
    private double factor;

    private final K TOMBSTONE = (K) new Object();

    private K[] keys;
    private V[] values;

    public HashMapOpenAddressingQuadratic(){ this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR); }
    public HashMapOpenAddressingQuadratic(int capacity){ this(capacity, DEFAULT_LOAD_FACTOR); }
    public HashMapOpenAddressingQuadratic(int capacity, double load_factor){
        if(capacity <= 0) throw new IllegalArgumentException("capacity cannot be 0 or negative");
        if(load_factor <= 0 || Double.isNaN(load_factor) || Double.isInfinite(load_factor)) throw new IllegalArgumentException("invalid load factor");

        this.factor = load_factor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        adjustCapacity();
        this.threshold = (int) (this.capacity * this.factor);
        this.keys = (K[]) new Object[this.capacity];
        this.values = (V[]) new Object[this.capacity];

    }

    public int size (){ return size; }

    private int probe(int x){ return (x * x + x) >> 1; }

    private static int nextPowerOfTwo(int n){ return Integer.highestOneBit(n) << 1; }

    private void increaseCapacity(){ capacity = nextPowerOfTwo(capacity); }

    private int normalizeIndex(int hash){ return (hash & 0x7FFFFFFF) % capacity; }

    private void adjustCapacity(){
        int pow2 = Integer.highestOneBit(capacity);
        if(capacity == pow2) return;
        increaseCapacity();
    }

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

    public V insert(K key, V value){
        if(key == null) throw new IllegalArgumentException("null key");
        if(size >= threshold) resizeTable();

        int offset = normalizeIndex(key.hashCode());

        for(int i = offset, x = 1, j = -1 ;; i = normalizeIndex(offset + probe(x))){
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
                        keys[j] = key;
                        values[j] = value;
                        values[i] = null;
                        keys[i] = TOMBSTONE;
                    }
                    return oldval;
                }
            }
            else{
                if(j == -1){
                    values[i] = value;
                    keys[i] = key;
                }
                else{
                    values[j] = value;
                    keys[j] = key;
                }
                size++;
                return null;
            }
        }
    }

    public boolean hasKey(K key){
        if (key == null) throw new IllegalArgumentException("null key");

        int offset = normalizeIndex(key.hashCode());

        for(int i = offset, x = 1, j = -1 ;; i = normalizeIndex(offset + probe(x))){
            if(keys[i] == TOMBSTONE){
                if (j == -1) j = i;
            }
            else if(keys[i] != null){
                if(keys[i].equals(key)){
                    if(j != -1){
                        values[j] = values[i];
                        keys[j] = keys[i];
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                    }
                    return true;
                }
            }
            else{
                return false;
            }
        }
    }

    public V remove(K key){
        if (key == null) throw new IllegalArgumentException("null key");

        int offset = normalizeIndex(key.hashCode());

        for(int i = offset, x = 1 ;; i = normalizeIndex(offset + probe(x))){
            if (keys[i] == TOMBSTONE) continue;;
            if(keys[i] == null) return null;
            if (keys[i].equals(key)){
                size--;
                V oldval = values[i];
                keys[i] = TOMBSTONE;
                values[i] = null;
                return oldval;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < capacity; i++)
            if (keys[i] != null && keys[i] != TOMBSTONE) sb.append(keys[i] + " => " + values[i] + ", ");
        sb.append("}");
        return sb.toString();
    }

    public static void main(String args[]){
        HashMapOpenAddressingQuadratic<Integer, String> h = new HashMapOpenAddressingQuadratic<>();
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

        System.out.println("size : " + h.size());
        System.out.println("capacity : " + h.capacity);
        System.out.println(h.insert(51, "five one"));
        System.out.println("size : " + h.size());
        System.out.println("capacity : " + h.capacity);
        System.out.println(h.insert(61, "six one"));
        System.out.println("size : " + h.size());
        System.out.println("capacity : " + h.capacity);

        System.out.println(h.toString());

    }
}

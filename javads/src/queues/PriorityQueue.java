package queues;

public class PriorityQueue{
    private Integer[] queue;
    private int capacity;
    private int size;

    public PriorityQueue(){ this(2); }
    public PriorityQueue(int capacity){
        this.capacity = capacity;
        this.queue = new Integer[capacity];
        this.size = 0;
    }
    public PriorityQueue (int capacity, Integer[] arr){
        this(capacity);
        for(int i = 0;i < capacity && arr[i] != null; i++){
            this.queue[i] = arr[i];
            this.size++;
        }
        makeHeap();
    }

    public boolean isFull(){ return size == capacity; }
    public boolean isEmpty(){ return size == 0; }
    public Integer peek(){ return !isEmpty() ? queue[0] : null; }

    private void makeHeap(){
        System.out.println("making heap");
        for(int i = (int)Math.floor(size/2) - 1; i >= 0 ; i--){
            adjustDown(i);
        }
    }

    private void shiftUp(int index){
        int parent = (int)Math.floor(index / 2) - 1;
        index = index-1;
        while(parent >= 0 && queue[index] < queue[parent]){
            int temp = queue[index];
            queue[index] = queue[parent];
            queue[parent] = temp;
            index = parent;
            parent = (int)Math.floor(index+1 / 2) - 1;
        }
    }

    private void adjustDown(int index){
        int i = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if(right < size && queue[right] < queue[index] && queue[right] < queue[left]){
            i = right;
        }else if(right < size && queue[left] < queue[index]){
            i = left;
        }
        if(i != index){
            int temp = queue[index];
            queue[index] = queue[i];
            queue[i] = temp;
            adjustDown(i);
        }
    }

    public boolean add(Integer element){
        if(!isFull()){
            queue[size] = element;
            size++;
            shiftUp(size);
            return true;
        }
        return false;
    }

    public Integer extract(){
        if(!isEmpty()){
            Integer max = queue[0];
            queue[0] = queue[--size];
            queue[size] = null;
            if (size > 1){
                adjustDown(0);
            }
            return max;
        }
        return null;
    }

    public boolean extract(Integer element){
        if(!isEmpty()){
            if(queue[0] == element) {
                extract();
                return true;
            }
            else{
                for(int i = 0; i < size; i++){
                    if(queue[i] == element){
                        queue[i] = queue[--size];
                        queue[size] = null;
                        if (size > 1){
                            adjustDown(i);
                        }
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public void printer(){
        if(!isEmpty()){
            for(int i = 0; i< size ; i++){
                System.out.print(queue[i] + " ");
            }
            System.out.println();
        }
        else
            System.out.println("NULL");
    }

    public static void main(String args[]){
        Integer[] i = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        PriorityQueue p = new PriorityQueue(7, i);
        p.printer();
        System.out.println("extracted top priority: " + p.extract());
        p.printer();
        System.out.println("\npeek : " + p.peek());
        System.out.println(p.add(8));
        p.printer();
        System.out.println(p.add(56));
        System.out.println(p.extract());
        System.out.println(p.add(4));
        p.printer();


        System.out.println("\n\nnew example");
        PriorityQueue p1 = new PriorityQueue(7);

        //adding the elements/priorities
        System.out.println(p1.add(6));
        p1.printer();
        System.out.println(p1.add(5));
        p1.printer();
        System.out.println(p1.add(4));
        p1.printer();
        System.out.println(p1.add(3));
        p1.printer();
        System.out.println(p1.add(2));
        p1.printer();
        System.out.println(p1.add(1));
        p1.printer();


        //extracting max
        System.out.println(p1.extract());
        p1.printer();
        System.out.println(p1.extract());
        p1.printer();
        System.out.println(p1.extract());
        p1.printer();
        System.out.println(p1.extract());
        p1.printer();
        System.out.println(p1.extract());
        p1.printer();
        System.out.println(p1.extract());
        p1.printer();
        System.out.println(p1.add(5));
        p1.printer();
        System.out.println(p1.add(6));
        p1.printer();
        System.out.println(p1.add(2));
        p1.printer();
        System.out.println(p1.add(1));
        p1.printer();
        System.out.println(p1.add(12));
        p1.printer();
        System.out.println(p1.add(6));
        p1.printer();
        System.out.println(p1.add(7));
        p1.printer();
        System.out.println(p1.add(8));
        p1.printer();

        //extracting the specified priority
        System.out.println(p1.extract(7));
        p1.printer();
        System.out.println(p1.extract(12));
        p1.printer();
        System.out.println(p1.add(12));
        System.out.println(p1.add(7));
        p1.printer();
        System.out.println(p1.extract(2));
        p1.printer();

    }
}

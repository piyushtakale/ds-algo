package linked_list;

public class DoubleList {
    public Node head;
    public Node tail;

    class Node{
        int data;
        Node next;
        Node prev;
        public Node(int data){
            this.prev = null;
            this.data = data;
            this.next = null;
        }

        public boolean isLast(){ return this.next == null; }

    }
    public DoubleList(){
        this.head = null;
        this.tail = null;
    }
    public boolean isEmpty(){ return head == null; }

    public void clear(){
        head = tail = null;
    }
    public Integer front(){
        if (!isEmpty())
            return head.data;
        return null;
    }
    public Integer end(){
        if (!isEmpty())
            return tail.data;
        return null;
    }

    public boolean insertFront(int element){
        if (isEmpty()){
            head = tail = new Node(element);
            return true;
        }
        Node a = new Node(element);
        a.next = head;
        head.prev = a;
        head = a;
        return true;
    }
    public boolean insertEnd(int element){
        if (isEmpty()){
            head = tail = new Node(element);
            return true;
        }
        Node a = new Node(element);
        tail.next = a;
        a.prev = tail;
        tail = a;
        return true;
    }
    public boolean insertAt(int element, int index){
        if (index == 0){
            return insertFront(element);
        }
        if( isEmpty() && index >= 1){
            return false;
        }
        int i = 1;
        Node j = head;
        while (j.next != null){
            if(i == index){
                Node a = new Node(element);
                a.next = j.next;
                j.next.prev = a;
                j.next = a;
                a.prev = j;
                return true;
            }
            i++;
            j = j.next;
        }
        if(index == i && j.isLast()){
            return insertEnd(element);
        }
        return false;
    }

    public Integer deleteFront(){
        Integer a = front();
        if (isEmpty()){ }
        else if(head.next == null){ clear(); }
        else { head = head.next; head.prev = null; }
        return a;
    }

    public Integer deleteEnd(){
        Integer a = end();
        if (isEmpty()){  }
        else if(tail.prev == null){ clear(); }
        else { tail = tail.prev; tail.next = null; }
        return a;
    }

    public Integer deleteAt(int index){
        Integer a = null;
        if(isEmpty()){ }

        else if( index == 0) {
            a = deleteFront();
        }
        else{
            Node j = head.next;
            int i = 1;
            while (j != null){
                if (i == index){
                    if(j.isLast()){ a = deleteEnd(); break; }
                    a = j.data;
                    j.prev.next = j.next;
                    j.next.prev = j.prev;
                    break;
                }
                i++;
                j = j.next;
            }
        }
        return a;
    }
    public boolean deleteElement(int element){
        boolean a = false;
        if(isEmpty()){ }
        else if(element == front()){
            if (deleteFront() != null){
                a = true;
            }
        }
        else{
            Node j = head;
            while (j != null){
                if(j.data == element){
                    if(j.isLast()){ a = deleteEnd() != null; break; }
                    a = true;
                    j.prev.next = j.next;
                    j.next.prev = j.prev;
                    break;
                }
                j = j.next;
            }
        }
        return a;
    }

    public Integer get(int index){
        int i = 0;
        Node j = head;
        while(j != null){
            if(i == index){
                return j.data;
            }
            j = j.next;
            i++;
        }
        return null;
    }
    public  String toString(){
        String s = "head ->";
        if(isEmpty()){ return s+" None <- tail"; }
        Node j = head;
        while (j.next != null){
            s += j.data + " <-> ";
            j = j.next;
        }
        s += tail.data + " <- tail\n tail ->";
        if(isEmpty()){ return s+" None <- head"; }
        j = tail;
        while (j != null){
            s += j.data + " <-> ";
            j = j.prev;
        }s += " head";
        return s;
    }


    public static void main(String args[]){
        DoubleList l = new DoubleList();

        // insertFront and end
        l.insertFront(10);
        l.insertFront(20);
        l.insertFront(5);
        l.insertEnd(6);
        System.out.println(l.toString());
        l.insertEnd(30);
        System.out.println(l.toString());
        l.insertFront(90);
        System.out.println(l.toString());

        // insertAt
        l.insertAt(50, 0);
        System.out.println(l.toString());
        l.insertAt(55, 2);
        System.out.println(l.toString());
        l.insertAt(56, 6);
        System.out.println(l.toString());
        l.insertAt(49, 9);
        System.out.println(l.toString());
        System.out.println(l.insertAt(565,56));
        System.out.println(l.toString());

        // delete front and end
        System.out.println(l.deleteFront());
        System.out.println(l.toString());
        System.out.println(l.deleteEnd());
        System.out.println(l.toString());

        // deleteAt
        System.out.println(l.deleteAt(7));
        System.out.println(l.toString());
        System.out.println(l.deleteAt(0));
        System.out.println(l.toString());
        System.out.println(l.deleteAt(3));
        System.out.println(l.toString());

        // deleteElement()
        System.out.println(l.deleteElement(5));
        System.out.println(l.toString());
        System.out.println(l.deleteElement(6));
        System.out.println(l.toString());
        System.out.println(l.deleteElement(55));
        System.out.println(l.toString());
        System.out.println(l.deleteElement(565));
        System.out.println(l.toString());
        System.out.println(l.deleteElement(56));
        System.out.println(l.deleteElement(20));
        System.out.println(l.toString());
        System.out.println(l.deleteElement(55));

        l.insertFront(10);
        l.insertFront(20);
        l.insertFront(5);
        l.insertEnd(6);
        l.insertEnd(30);
        l.insertFront(90);
        System.out.println(l.toString());

        // get
        System.out.println(l.get(0));
        System.out.println(l.get(10));
        System.out.println(l.get(2));
        System.out.println(l.get(5));
        System.out.println(l.get(3));
        System.out.println(l.deleteAt(3));
        System.out.println(l.toString());
    }
}


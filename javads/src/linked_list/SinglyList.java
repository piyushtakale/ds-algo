package linked_list;

public class SinglyList {
    public Node head;
    public Node tail;

    class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
        public boolean isLast(){ return this.next == null; }

    }
    public SinglyList(){
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
                j.next = a;
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
        else { head = head.next; }
        return a;
    }

    public Integer deleteEnd(){
        Integer a = end();
        if (isEmpty()){  }
        else if(head.next == null){ clear(); }
        else {
            Node j = head;
            while (true){
                if(j.next.isLast()){
                    j.next = null;
                    tail = j;
                    break;
                }
                j = j.next;
            }
        }
        return a;
    }

    public Integer deleteAt(int index){
        Integer a = null;
        if(isEmpty()){ }

        else if( index == 0) {
            a = deleteFront();
        }
        else{
            Node j = head;
            int i = 1;
            while (!j.isLast()){
                if (i == index){
                    a = j.next.data;
                    j.next = j.next.next;
                    if (j.isLast()){
                        tail = j;
                    }
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
            while (!j.isLast()){
                if (j.next.data == element){
                    a = true;
                    j.next = j.next.next;
                    if(j.isLast()){
                        tail = j;
                    }
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
    public  String printer(){
        String s = "head ->";
        if(isEmpty()){ return s+" None <- tail"; }
        Node j = head;
        while (j.next != null){
            s += j.data + " -> ";
            j = j.next;
        }
        s += tail.data + " <- tail";
        return s;
    }


    public static void main(String args[]){
        SinglyList l = new SinglyList();

        // insertFront and end
        l.insertFront(10);
        l.insertFront(20);
        l.insertFront(5);
        l.insertEnd(6);
        System.out.println(l.printer());
        l.insertEnd(30);
        System.out.println(l.printer());
        l.insertFront(90);
        System.out.println(l.printer());

        // insertAt
        l.insertAt(50, 0);
        System.out.println(l.printer());
        l.insertAt(55, 2);
        System.out.println(l.printer());
        l.insertAt(56, 6);
        System.out.println(l.printer());
        l.insertAt(49, 9);
        System.out.println(l.printer());
        System.out.println(l.insertAt(565,56));
        System.out.println(l.printer());

        // delete front and end
        System.out.println(l.deleteFront());
        System.out.println(l.printer());
        System.out.println(l.deleteEnd());
        System.out.println(l.printer());

        // deleteAt
        System.out.println(l.deleteAt(7));
        System.out.println(l.printer());
        System.out.println(l.deleteAt(0));
        System.out.println(l.printer());
        System.out.println(l.deleteAt(3));
        System.out.println(l.printer());

        // deleteElement()
        System.out.println(l.deleteElement(5));
        System.out.println(l.printer());
        System.out.println(l.deleteElement(6));
        System.out.println(l.printer());
        System.out.println(l.deleteElement(55));
        System.out.println(l.printer());
        System.out.println(l.deleteElement(565));
        System.out.println(l.printer());
        System.out.println(l.deleteElement(56));
        System.out.println(l.deleteElement(20));
        System.out.println(l.printer());
        System.out.println(l.deleteElement(55));


        l.insertFront(10);
        l.insertFront(20);
        l.insertFront(5);
        l.insertEnd(6);
        l.insertEnd(30);
        l.insertFront(90);
        System.out.println(l.printer());

        // get
        System.out.println(l.get(0));
        System.out.println(l.get(10));
        System.out.println(l.get(2));
        System.out.println(l.get(5));
    }
}

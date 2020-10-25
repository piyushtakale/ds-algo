package binarytree;
import java.util.*;

class BinarySearchTree{
    private static class Node{
        int data;
        Node left;
        Node right;
        Node(int element){
            this.data = element;
            left = null;
            right = null;
        }
    }
    Node root;
    int nodes;
    BinarySearchTree(){
        root = null;nodes = 0;
    }
    public boolean insert(int element){
        if(root == null){ root = new Node(element); return true;}
        else{
            Node temp = root;
            while (true){
                if(element <= temp.data){
                    if(temp.left == null){
                        temp.left = new Node(element);
                        nodes++;
                        return true;
                    }
                    temp = temp.left;
                }
                else{
                    if(temp.right == null){
                        temp.right = new Node(element);
                        nodes++;
                        return true;
                    }
                    temp = temp.right;
                }
            }
        }
    }

    private Node removeNode(Node node, Integer element){
        if(node == null) return null;
        if(element < node.data){
            node.left = removeNode(node.left, element);
        }
        else if(element > node.data){
            node.right = removeNode(node.right, element);

        }
        else{
            System.out.println("node : "+ node.data + "left:" + node.left + " right "  + node.right);
            if(node.left == null){
                Node rightChild = node.right;
                return rightChild;
            }
            else if(node.right == null){
                Node leftChild = node.left;
                return leftChild;
            }
            else{
                Node rightmin = findMin(node.right);
                node.data = rightmin.data;
                System.out.println(node.data);
                node.right = removeNode(node.right, rightmin.data);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Helper method to find the rightmost node (which has the largest value)
    private Node findMax(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }

    public boolean remove(Integer element){
        if(search(element)){
            root = removeNode(root, element);
            nodes--;
            return true;
        }
        return false;
    }

    public boolean search(int element){
        if(root == null){ return false; }
        Node temp = root;
        while(temp != null){
            if(temp.data == element){ return true; }
            else if(element < temp.data){ temp = temp.left; }
            else{ temp = temp.right; }
        }
        return false;
    }

    public void printInOrder(Node root){
        if (root != null){
            printInOrder(root.left);
            System.out.print(" "+root.data);
            printInOrder(root.right);
        }
    }
    public java.util.Iterator<Integer> inOrder(){
        final int count = nodes;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);
        return new java.util.Iterator<Integer>() {
            Node trav = root;

            public boolean hasNext() {
                if (count != nodes) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            public Integer next() {
                if (count != nodes) throw new ConcurrentModificationException();

                while(trav != null && trav.left != null){
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();
                if(node.right != null){
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.data;
            }
        };
    }

    public java.util.Iterator<Integer> preOrder(){
        final int count = nodes;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);
        return new java.util.Iterator<Integer>() {
            public boolean hasNext() {
                if (count != nodes) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            public Integer next() {
                if (count != nodes) throw new ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }
        };
    }

    public void printPreOrder(Node root){
        if (root != null){
            System.out.print(" "+root.data);
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }


    public void printPostOrder(Node root){
        if (root != null){
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.print(" "+root.data);
        }
    }
    public java.util.Iterator<Integer> postOrder(){
        final int count = nodes;
        final java.util.Stack<Node> stack0 = new java.util.Stack<>();
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack0.push(root);
        while(!stack0.isEmpty()){
            Node node = stack0.pop();
            if(node != null){
                stack.push(node);
                if(node.left != null) stack0.push(node.left);
                if(node.right != null) stack0.push(node.right);
            }
        }
        return new java.util.Iterator<Integer>() {
            public boolean hasNext() {
                if (count != nodes) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            public Integer next() {
                if (count != nodes) throw new ConcurrentModificationException();
                return stack.pop().data;
            }
        };
    }

    public void printInOrder(){System.out.println(); printInOrder(root); }
    public void printPreOrder(){System.out.println(); printPreOrder(root); }
    public void printPostOrder(){System.out.println(); printPostOrder(root); }

    public Integer getMax(){
        if(root == null){ return null; }
        else{
            Node temp = root;
            while(temp.right != null){
                temp = temp.right;
            }
            return temp.data;
        }
    }
    public Integer getMin(){
        if(root == null){ return null; }
        else{
            Node temp = root;
            while(temp.left != null){
                temp = temp.left;
            }
            return temp.data;
        }
    }
    public int height(Node root){
        if(root.left != null && root.right != null){
            return 1 + Math.max(height(root.left), height(root.right));
        }
        else if(root.left != null){
            return 1 + height(root.left);
        }
        else if (root.right != null){
            return 1 + height(root.right);
        }
        else{
            return 0;
        }
    }
    static void levelOrder(Node root){
        Queue<Node> arr = new LinkedList<>();
        ArrayList<String> outer = new ArrayList<>();
        int i = 0;
        arr.add(root);
        while(arr.size() != 0){
            Node n = arr.remove();
            outer.add(Integer.toString(n.data));
            if(n.left != null) arr.add(n.left);
            if(n.right != null) arr.add(n.right);
        }
        System.out.print(String.join(" ", outer));

    }
    public int height(){
        return height(root);
    }

    public static void main(String args[]){
        BinarySearchTree bt = new BinarySearchTree();
        bt.insert(3);
        bt.insert(5);
        bt.insert(2);
        bt.insert(1);
        bt.insert(4);
        bt.insert(6);
        bt.insert(7);
        bt.insert(9);
        System.out.println("\nlevel order ");
        levelOrder(bt.root);
        System.out.println("\nprintInOrder");
        bt.printInOrder();
        System.out.println("\nsearching 9: "+ bt.search(9));
        System.out.println("height : "+bt.height());
        System.out.println("min : "+bt.getMin());
        System.out.println("max : "+bt.getMax());

//        String a = "abc";
//        String b = "dfg";
//        System.out.println(a.compareTo(b));

        bt.printInOrder();
        System.out.println();
        bt.printPreOrder();
        System.out.println();
        bt.printPostOrder();
        System.out.println("\n new astart");

        BinarySearchTree b = new BinarySearchTree();
        for (int a: new int[]{50, 20, 70,80, 10, 30, 60, 5, 15, 33, 40, 55, 65, 75, 77,76,79, 85}){
            b.insert(a);
        }
        b.printInOrder();
        b.printPreOrder();
        b.printPostOrder();

        System.out.println("removing 70");
        b.remove(70);
        b.printInOrder();
        b.printPreOrder();
        b.printPostOrder();

        Iterator<Integer> it = b.preOrder();
        System.out.println("\nprinting preorder using iterator");
        while(it.hasNext()){
            System.out.print(" "+ it.next());
        }

        it = b.inOrder();
        System.out.println("\nprinting inorder using iterator");
        while(it.hasNext()){
            System.out.print(" "+ it.next());
        }

        it = b.postOrder();
        System.out.println("\nprinting postorder using iterator");
        while(it.hasNext()){
            System.out.print(" "+ it.next());
        }

        BST<String> bs = new BST<>();
        bs.insert("piyush");
        bs.insert("piyust");
        bs.insert("piyusd");
        bs.insert("piyuso");
        bs.insert("piyusw");
        bs.insert("piyusa");
        bs.insert("piyusf");
        System.out.println();
        bs.printInOrder();


    }
}



class BST <T extends Comparable<T>> {
    private class Node {
        T data;
        Node left;
        Node right;
        Node(T element){
            this.data = element;
            left = null;
            right = null;
        }


    }
    Node root;
    BST(){
        root = null;
    }
    public boolean insert(T element){
        if(root == null){ root = new Node(element); return true;}
        else{
            Node temp = root;
            while (true){
                if(element.compareTo(temp.data) < 0){
                    if(temp.left == null){
                        temp.left = new Node(element);
                        return true;
                    }
                    temp = temp.left;
                }
                else{
                    if(temp.right == null){
                        temp.right = new Node(element);
                        return true;
                    }
                    temp = temp.right;
                }
            }
        }
    }

    public boolean search(T ele){
        if(root == null){ return false; }
        Node temp = root;
        T element = ele;
        while(temp != null){
            if(temp.data == element){ return true; }
            else if(element.compareTo(temp.data) < 0){ temp = temp.left; }
            else{ temp = temp.right; }
        }
        return false;
    }

    public void printInOrder(Node root){
        if (root != null){
            printInOrder(root.left);
            System.out.print(" "+root.data);
            printInOrder(root.right);
        }
    }
    public void printPreOrder(Node root){
        if (root != null){
            System.out.print(" "+root.data);
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }
    public void printPostOrder(Node root){
        if (root != null){
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.print(" "+root.data);
        }
    }

    public void printInOrder(){ printInOrder(root); }
    public void printPreOrder(){ printPreOrder(root); }
    public void printPostOrder(){ printPostOrder(root); }

    public T getMax(){
        if(root == null){ return null; }
        else{
            Node temp = root;
            while(temp.right != null){
                temp = temp.right;
            }
            return temp.data;
        }
    }
    public T getMin(){
        if(root == null){ return null; }
        else{
            Node temp = root;
            while(temp.left != null){
                temp = temp.left;
            }
            return temp.data;
        }
    }
    public int height(Node root){
        if(root.left != null && root.right != null){
            return 1 + Math.max(height(root.left), height(root.right));
        }
        else if(root.left != null){
            return 1 + height(root.left);
        }
        else if (root.right != null){
            return 1 + height(root.right);
        }
        else{
            return 0;
        }
    }
//    static void levelOrder(Node root){
//        Queue<Node> arr = new LinkedList<>();
//        ArrayList<String> outer = new ArrayList<>();
//        int i = 0;
//        arr.add(root);
//        while(arr.size() != 0){
//            Node n = arr.remove();
//            outer.add(Integer.toString(n.data));
//            if(n.left != null) arr.add(n.left);
//            if(n.right != null) arr.add(n.right);
//        }
//        System.out.print(String.join(" ", outer));
//
//    }
    public int height(){
        return height(root);
    }
}




package binarytree;

import java.util.Scanner;

public class BinaryTree {
    private class Node{
        private Integer data;
        private Node left;
        private Node right;

        Node(Integer data){
            this.data = data;
            this.left = this.right = null;
        }
        public Node right(){
            return this.right;
        }
        public Node left(){
            return this.right;
        }
        public void insertRight(Node n){
            this.right = n;
        }
        public void insertLeft(Node n){
            this.left = n;
        }
    }

    private Node root;

    public boolean insert(Integer element){
        Scanner sc = new Scanner(System.in);
        Node roo = root;
        do{
            if(roo == null){
                root = new Node(element);
                break;
            }
            else if(roo.left != null && roo.right != null){
                System.out.println("enter your choice\n1. move left\n2. move right\n3. cancel");
                System.out.println("current : \n   " + roo.data+"\n /   \\ \n"+ roo.left.data + "     "+ roo.right.data);
                int choice = sc.nextInt();
                switch (choice){
                    case 1 : roo = roo.left;
                        break;
                    case 2 : roo = roo.right;
                        break;
                    case 3 : return false;
                }
            }
            else if(roo.left != null && roo.right == null){
                System.out.println("enter your choice\n1. move left\n2. insert right\n3. cancel");
                System.out.println("current : \n   " + roo.data+"\n /   \\ \n"+ roo.left.data + "     null");
                int choice = sc.nextInt();
                switch (choice){
                    case 1 : roo = roo.left;
                        break;
                    case 2 : roo.right = new Node(element);
                        return true;
                    case 3 : return false;
                }
            }
            else if(roo.right != null && roo.left == null){
                System.out.println("enter your choice\n1. insert left\n2. move right\n3. cancel");
                System.out.println("current : \n   " + roo.data+"\n /   \\ \nnull   "+ roo.right.data);
                int choice = sc.nextInt();
                switch (choice){
                    case 1 : roo.left = new Node(element);
                        return true;
                    case 2 : roo = roo.right;
                        break;
                    case 3 : return false;
                    default: break;
                }
            }
            else{
                System.out.println("enter your choice\n1. insert left\n2. insert right\n3. cancel");
                System.out.println("current : \n   " + roo.data+"\n /   \\ \nnull null");
                int choice = sc.nextInt();
                switch (choice){
                    case 1 : roo.left = new Node(element);
                        return true;
                    case 2 : roo.right = new Node(element);
                        return true;
                    case 3 : return false;
                    default: break;
                }
            }
        }while(true);
        return true;
    }

    public static void main(String args[]){
        BinaryTree b = new BinaryTree();
        System.out.println(b.insert(5));
        System.out.println(b.insert(6));
        System.out.println(b.insert(7));
        System.out.println(b.insert(56));
        System.out.println(b.insert(4));
    }

}

package avltree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AVLtree {
    
    Node root;
    Node temp = new Node();
    int count = 0; //count for traversal
    int calls = 0;
    
    public void eraseTemp() {
        temp.key = 0;
        temp.left = null;
        temp.right = null;
    }
    
    public void eraseCalls() {
        calls = 0;
    }
    
    public int getCount() {
        return count;
    }
    
    public void eraseCount() {
        count = 0;
    }
    
    public static class Node {
        int key, size, position;
        Node left;
        Node right;
        Node parent;
        
        public Node() {
            this.key = 0;
            this.left = null;
            this.right = null;
        }
    
        public Node(int k){
            this.key = k;
            this.left = null;
            this.right = null;
        }
        
        public Node(int k, Node left, Node right){
            this.key = k;
            this.left = left;
            this.right = right;
            
        }
        
        public int getSize(Node n) {
            size = getSize(n.left) + getSize(n.right) + 1;
            return size;
        }
        
    }    

    public AVLtree(Node root){
        this.root = root;
    }
    
    public Node rotateRight(Node n){
        Node r = n;
        Node z = r.left;
        Node l = r.right;
        Node m = z.left;
        Node e = z.right;
        
        r = new Node(r.key, e, l);
        z = new Node(z.key, m, r);
        
        return z; //new root
    }
    
    public Node rotateLeft(Node n){
        Node r = n;
        Node z = r.right;
        Node l = r.left;
        Node m = z.left;
        Node e = z.right;
        
        r = new Node(r.key, l, m);
        z = new Node(z.key, r, e);
        
        return z; //new root
    }
    
    public Node rotateLR(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }
    
    public Node rotateRL(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }
    
    public void insertion(Node n){
        Node temp = n;
        if(root == null)
            root = temp;
        else 
            root = insertNode(root, new Node(n.key));
        if(checkBalance(root) == 1)
            root = rotateLeft(root);
        else  //(checkBalance(root) == -1)
            root = rotateRight(root);
    }
    
    public Node insertNode(Node root, Node n){
        if(root == null)
            return n;
        if(n.key > root.key)
            return new Node(root.key,root.left,insertNode(root.right,n));
        else //(n.key < root.key)
            return new Node(root.key,insertNode(root.left,n),root.right);
    }
    
    public Node minimum(){
        Node current = root;
        while(current.left != null){
                current = current.left;
        }
        return current;
    }
    
    public Node maximum(){
        Node current = root;
        while(current != null){
                current = current.right;
        }
        return current;
    }
    
    public void inorderTraversal(Node n) {
        if (calls == 0)
            temp.key = n.key;
        Node current = n;
        if(current.left != null) {
            calls++;
            inorderTraversal(current.left);
        }
        System.out.print(current.key+" ");
        if(current.key < temp.key)
            count++;
        if(current.right != null)
            inorderTraversal(current.right);
        System.out.println();
        eraseTemp();
        eraseCalls();
    }
    
    public boolean search(int value){
        Node node = root;
        while(node != null){
            if(node.key == value)
                return true;
            else if(node.key < value)
                node = node.right;
            else
                node = node.left;
        }
        return false;
    }
    
    public Node minInTree(Node n) {
        Node current = n;
        while(current.left != null)
            current = current.left;
        return current;
    }
    
    public Node maxInTree(Node n) {
        Node current = n;
        while(current.right!= null)
            current = current.right;
        return current;
    }
    
    public Node successor(Node n) {
        Node node = n;
        if(node.right != null)
            return minInTree(node);
        Node p = node.parent;
        while(p != null && node == p.right) {
            node = p;
            p = node.parent;
        }
        return p;
    }
    
    public Node predecessor(Node n) {
        Node node = n;
        if(node.left != null)
            return maxInTree(node.left);
        Node y = node.parent;
        while(y != null && node == y.left) {
            node = y;
            y = y.parent;
        }
        return y;   
    }
    
    public int height(Node n){
        if(n == null)
            return 0;
        else
            return Math.max(height(n.left),height(n.right))+1;
    }
    
    public int checkBalance(Node n){
        if(height(n.right)-height(n.left)>= 2)
            return 1;
        else if(height(n.left)-height(n.right)>= 2)
            return -1;
        else
            return 0;
    }
    
    public int rank(Node x) {
        eraseCount();
        inorderTraversal(x);
        return (count + 1);
    }
    
    public Node select(Node r, int i) {
        if(r == null)
            return null;
        if(r.left.getSize(r.left) >= i)
            return select(r.left, i);
        if(r.left.getSize(r.left) + 1 == i)
            return r;
        return select(r.right, i - 1 - (r.left.getSize(r.left)));
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        long tempStart, tempEnd;
        long insert, min, max, inorder, search, successor, predec, select, rank;
        File file = new File("AVLtree-test.txt");
        String fileName = "AVLtree-testOutput.txt";
        PrintWriter outputStream = new PrintWriter(fileName);
        Scanner in = new Scanner(file);
        int number = 0;
        int b;
        if(in.hasNext())
            number = in.nextInt();
        AVLtree avl = new AVLtree(new Node(number));
        while(in.hasNext()) {
            String next = in.next();
            if(next.equals("IN")) {
                b = in.nextInt();
                avl.insertion(new Node(b));
            }
            else if (next.equals("min")) {
                Node mini;
                mini = avl.minimum();
                outputStream.println(mini.key);
            }
            else if (next.equals("max")) {
                Node maxi;
                maxi = avl.maximum();
                outputStream.println(maxi.key);
            }
            else if (next.equals("TR")) {
                avl.inorderTraversal(avl.root);
                //output to file
            }
            else if (next.equals("SR")) {
                b = in.nextInt();
                boolean searching = false;
                searching = avl.search(b);
            }
            else if (next.equals("SC")) {
                b = in.nextInt();
                Node success;
                success = avl.successor(new Node(b));
                outputStream.println(success.key);
                
            }
            else if (next.equals("PR")) {
                b = in.nextInt();
                Node pred;
                pred = avl.predecessor(new Node(b));
                outputStream.println(pred.key);
            }
            else if (next.equals("SE")) {
                b = in.nextInt();
                Node selec;
                selec = avl.select(avl.root, b);
                outputStream.println(selec.key);
            }
            else //(next.equals("RA"))
            {    
                b = in.nextInt();
                int ran;
                ran = avl.rank(new Node(b));
                outputStream.println(ran);
            }
        }
        outputStream.close();
    }
}
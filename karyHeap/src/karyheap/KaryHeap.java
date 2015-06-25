package karyheap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class KaryHeap {
    
    public ArrayList<HeapNode> heap;
    public ArrayList<Integer> extracted;
    public int kids; //kids represents kids of each node.
    
    public KaryHeap(int k) {
        this.kids = k;
        this.heap = new ArrayList<HeapNode>();
        this.extracted = new ArrayList<Integer>();
    }
    
    public static class HeapNode {
        public Object data;
        public int key;
        public HeapNode() {
            key = 0;
            data = null;
        }
        public HeapNode(int k) {
            key = k;
            data = null;
        }
        public Object getData() {
            return data;
        }
        public int getKey() {
            return key;
        }
        public void setKey(int k){
            key = k;
        }
        public void setData(Object d) {
            data = d;
        }
    }

    public static void main(String[] args) throws FileNotFoundException { 
        File file = new File("karyHeap-test.txt");
        String fileName = "karyHeap-testOutput.txt";
        PrintWriter outputStream = new PrintWriter(fileName);
        Scanner inp;
        for(int u = 2; u <= 10; u = u + 2) {
            long start = System.currentTimeMillis();
            KaryHeap kh = new KaryHeap(2);
            inp = new Scanner(file);
            while(inp.hasNext()) {
                String next = inp.next();
                if(next.equals("IN")) {
                    int b = inp.nextInt();
                    if (kh.heap.isEmpty())
                        kh.heap.add(new HeapNode(b));
                    else 
                        kh.insert(b);
                }
                else if (next.equals("EX")) 
                    kh.extracted.add(kh.extractMin());
            }
            inp.close();
            long end = System.currentTimeMillis();
            if( u == 2) {
                for(int w = 0; w < kh.extracted.size(); w++)
                    outputStream.println(kh.extracted.get(w));
            }
            outputStream.println();
            outputStream.println("Kids = "+u+": "+(end-start)*1000+" micro-sec");
        }
        outputStream.close();
    }

    public void insert(int x) {
        int i = heap.size()+1; //heap index of inserted number.
        heap.add(new HeapNode(x));
        int a = (i + kids - 2) % kids; //Constant calculation for parent.
        int parent = (i + kids - 2 - a) / kids; //Parent heap index calculation.
        while(i>1) {
            if ( x < heap.get(parent - 1).getKey()) {
                HeapNode temp = new HeapNode(heap.get(parent-1).getKey());
                heap.set(parent - 1, new HeapNode(x));
                heap.set(i - 1, temp);
                i = parent;
                a = (i + kids - 2) % kids;
                parent = (i + kids - 2 - a) / kids;
            }
            else
                i = 1;
        }
    }
    
    public int extractMin() {
        int extract, i; //exctract is the extracted number.
                        //i is the index for organizing heap.
        extract = heap.get(0).getKey();
        i = 1;
        heap.set(0, new HeapNode(heap.get(heap.size()-1).getKey()));
        heap.remove(heap.size() - 1);
        while(2 - kids + (kids*i) <= heap.size()) {
            int minimum = i - 1;
            int child;
            for(int j = 0; j < kids; j++) {
                child = 2 - kids + (kids*i) + j;
                if (child < heap.size()) {
                    if (heap.get(child-1).getKey() < heap.get(minimum).getKey())
                        minimum = child - 1;
                }
            }
            if (minimum != i - 1) {
                int temp = heap.get(i - 1).getKey();
                heap.set(i - 1, new HeapNode(heap.get(minimum).getKey()));
                heap.set(minimum, new HeapNode(temp));
                i = minimum + 1;
            }
            else break;
        }
        return extract;
    }
}

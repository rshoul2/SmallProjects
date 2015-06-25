package sortheap;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class SortHeap
{
    public static int[] array;
    public static int x, left, right, big;
  
    public static void buildheap(int []a){
        x = a.length - 1;
        for(int i = x / 2; i >= 0; i--)
            maxheap(a, i);
    }
    
    public static void maxheap(int[] a, int i){ 
        left = 2 * i;
        right = 2* i + 1;
        if(left <= x && a[left] > a[i])
            big = left;
        else
            big = i;
        if(right <= x && a[right] > a[big])
            big = right;
        if(big != i){
            exchange(i,big);
            maxheap(a, big);
        }
    }
    
    public static void exchange(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp; 
    }
    
    public static void heapSortArray(int []b){
        array = b;
        buildheap(array);
        for(int i = x; i > 0; i--){
            exchange(0, i);
            x--;
            maxheap(array, 0);
        }
    }
    
    public static void main(String[] args) { 
        int k;
        System.out.println("Comparison in nano-seconds: ");
        System.out.println();
        for(int r = 1; r <= 128; r=r*2) {
            long start = System.nanoTime();
            int size = 1000;
            int[] heap = new int[size];
            Random random = new Random();
            for (int i=0; i<size; i++)
                heap[i] = random.nextInt(128000);    
            heapSortArray(heap);
            long end = System.nanoTime();
            System.out.println(" r = "+r+": "+(end-start));
        }
    }
}

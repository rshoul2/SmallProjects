package sorting;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Sorting {
    
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
    
    public static void heapSort(int []b){
        array = b;
        buildheap(array);
        for(int i = x; i > 0; i--){
            exchange(0, i);
            x--;
            maxheap(array, 0);
        }
    }    

    public static final int minListSize  =  100;
 
    public static void mergeSort(int[] array, int start, int end) {
        int[] temp = new int[end-start];
        int i, ii, tempor, middle;
        
        if ((end-start) < minListSize) {
            for (int f = start; f < end; f++) {
                int j, v = array[f];
                for (j = f - 1; j >= start && array[j] > v; j--)
                    array[j + 1] = array[j];
                array[j + 1] = v;
            }
            return;
        }  
        
        middle = start + (end-start)/2;
        mergeSort(array, start, middle);
        mergeSort(array, middle, end);
        
        i = start;
        ii = middle;
        tempor = 0;
        while (i < middle && ii < end){
            if (array[i] <= array[ii]) {
                temp[tempor] = array[i];
                tempor++;
                i++;
            }
            else {
                temp[tempor] = array[ii];
                tempor++;
                ii++;
            }
        }
        while (i < middle) {
            temp[tempor] = array[i];
            tempor++;
            i++;
        }
        while (ii < end) {
            temp[tempor] = array[ii];
            tempor++;
            ii++;
        }
        for (int y = 0; y < end-start; y++)
            array[y] = temp[y];
    }    
    
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "sortingOutputs.txt";
        PrintWriter outputStream = new PrintWriter(fileName);
        outputStream.println("     Comparison: Merge, Heap (in Nano-Seconds)");
        outputStream.println("---------------------------------------------------");
        outputStream.println(" -Both using the same integers (Range: 0-128000)");
        outputStream.println("  and size of array.");
        outputStream.println();
        for(int p = 1; p < 4; p++) {
            outputStream.println("Trial "+p+":");
            outputStream.println("--------");
            for(int r = 1; r <= 128; r=r*2) {
                int size = r*1000;
                int[] heap = new int[size];
                int[] merge = new int[size];
                Random rand = new Random();
                for(int i = 0; i < size; i++){
                    int random = rand.nextInt(128000);
                    heap[i] = random;
                    merge[i] = random;
                }
                
                long start1 = System.nanoTime();
                heapSort(heap);              
                long end1 = System.nanoTime();
                long start2 = System.nanoTime();
                mergeSort(merge, 0, size);
                long end2 = System.nanoTime(); 

                outputStream.println("Size = "+r*1000+": "+(end2-start2)+", "+(end1-start1));
            }
            outputStream.println();
        }
        outputStream.println();
        outputStream.println("-As seen in these three trials of increasing size");
        outputStream.println("the mergeSort algorithm seems to be the better one");
        outputStream.println("for sorting arrays such as these.");
        outputStream.close();
    }
}
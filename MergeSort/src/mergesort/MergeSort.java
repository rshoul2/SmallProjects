package mergesort;

import java.util.Random;

public class MergeSort {
    
    public static final int minListSize  =  100;
 
    public static void mergeSortArray(int[] array, int start, int end) {
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
        mergeSortArray(array, start, middle);
        mergeSortArray(array, middle, end);
        
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
     
    public static void main(String[] args) { 
        System.out.println("Comparison in nano-seconds:");
        System.out.println();
        for(int r = 1; r <= 128; r = r*2) {
            long start = System.nanoTime();
            int size = r*1000;
            int[] ar = new int[size];
            Random random = new Random();
            for(int i=0; i<size; i++)
                ar[i] = random.nextInt(128000);
            mergeSortArray(ar, 0, size);
            long end = System.nanoTime();
            System.out.println(" r = "+r+": "+(end-start));
        }
    }
}

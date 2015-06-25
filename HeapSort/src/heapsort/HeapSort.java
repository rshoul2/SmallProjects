package heapsort;

import java.util.Random;

public class HeapSort {
    public int[] array;
    public int[] heap;
    public int numItems;
    public Random random;
    
    public HeapSort(int i) {
        numItems = i;
        array = new int[numItems];
        heap = new int[numItems];
        random = new Random(); 
        for (int j=0; j<numItems; j++)
            array[j] = random.nextInt(128000);
    }
    
    public void AddtoHeap(int itemNum, int numItems) {
        int left = 2 * itemNum + 1, temp;
        if (left > numItems-1) 
            return;
        else {
            int big, right = 2 * itemNum + 2;
            if (right > numItems-1 || array[right] < array[left]) 
                big = left;
            else 
                big = right;
            if (array[big] > array[itemNum]) {
                //System.out.println("(parent,child) exchanged: nums["+itemNum+"]="
                       //+nums[itemNum]+",nums["+big+"]="+nums[big]);
                temp = array[big]; array[big] = array[itemNum]; array[itemNum] = temp;
                
            }
            AddtoHeap(big,numItems);
        }
    }
    
    public void MakeHeap(int numItems) {
        if (numItems == 1) 
            return;
        for (int i = (numItems/2) -1; i >= 0; i--) {
            AddtoHeap(i,numItems);
        }
    }
    
    public void HeapSort(int numItems) {
        int temp;
        for(int i=numItems; i>=0; i--) {
           if (i == 1){
               heap[i-1] = array[i-1];
               return;
           }
           else {
               MakeHeap(i);
               //System.out.print("["+array[0]);
               //for (int j=1; j<i; j++)
                   //System.out.print(", "+array[j]);
               //System.out.println("]");
               temp = array[0]; array[0] = array[i-1]; array[i-1] = temp;
               heap[i-1] = array[i - 1];
               //System.out.print("["+array[0]);
               //for (int k=1; k<i; k++)
                   //System.out.print(", "+array[k]);
               //System.out.println("]");
            }
        }
     }
    
    public static void main(String[] args) {
        int k;
        System.out.println("Comparison in nano-seconds: ");
        System.out.println();
        for(int r = 1; r <= 128; r = r*2) {
            long start = System.nanoTime();
            HeapSort hs = new HeapSort(r*1000);
            //for(int i = 0; i < hs.array.length; i++) //Prints original array
                //System.out.print(hs.array[i]+" ");
            //System.out.println();
            hs.HeapSort(r*1000);
            long end = System.nanoTime();
            //for(int j = 0; j < hs.heap.length; j++) //Prints Sorted Array/Heap
                //System.out.print(hs.heap[j]+" ");
            //System.out.println();
            System.out.println(" r = "+r+": "+(end-start)+", ");
        }
    }
}

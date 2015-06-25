package heapsort2;

public class HeapSort2 
{   int[] nums;
    int numItems;
    
    public HeapSort2(int i)
    {   numItems = i;
        nums = new int[numItems];
        for (int j=0; j<numItems; j++)
            nums[j] = j;
    }
    
    void exchange(int i, int j)
    {   int temp;
        temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
    
    void AddtoHeap(int itemNum, int numItems)
    {   int left = 2*itemNum+1;
        if (left > numItems-1) return;
        else
        {   int big, right = 2*itemNum+2;
            if (right > numItems-1 || nums[right] < nums[left]) big = left;
            else big = right;
            if (nums[big] > nums[itemNum])
            {   //System.out.println("("+itemNum+","+big+") exchanged: nums["+itemNum+"]="
                       //+nums[itemNum]+", nums["+big+"]="+nums[big]);
                exchange(big,itemNum);
            }
            AddtoHeap(big,numItems);
        }
    }
    
    void MakeHeap(int numItems)
    {   if (numItems == 1) return;
        for (int i=(numItems/2)-1; i>=0; i--) {
            AddtoHeap(i,numItems);
        }
    }
    
    void HeapSort(int numItems) 
    {   System.out.print(numItems);
        if (1 == numItems) return;
        
        else{ 
                if (numItems == numItems) { //if top level call
     
                   MakeHeap(numItems); }
        
                System.out.print("["+nums[0]);

                for (int i = 1; i < numItems; i++) 
                   System.out.print(", "+nums[i]);

                System.out.println("]");

                exchange(0,numItems-1);

                AddtoHeap(0, numItems-1); 
                      
                System.out.print("["+nums[0]);

                for (int i = 1; i < numItems; i++) 
                    System.out.print(", "+nums[i]);

                System.out.println("]");

                HeapSort(numItems-1);
         }
    }

    
    public static void main(String[] args) 
    {   HeapSort2 hs = new HeapSort2(10);
        hs.MakeHeap(10);
        hs.HeapSort(10);
    }
}

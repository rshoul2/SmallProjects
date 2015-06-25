package pagereplacement;

import java.util.Scanner;

public class PageReplacement {
    
    //public static int[] page = {0, 1, 2, 3, 2, 4, 5, 2, 4, 1, 6, 3, 7, 8, 3, 8, 4, 9, 7, 8, 1, 2, 9, 6, 4, 5, 0, 2, 5, 1, 9};
    //public static int[] page = {0,2,3,2,0,4,5,7,4,3,2};
    public static int[] page = {1,2,3,4,1,2,5,1,2,3,4,5};
    public static int[] frames, count, time;
    public static int memoryFIFO=-1, ffaults=0, ofaults=0, lfaults=0;
    
    public static void timeForm(int leng) { //used in LRU
        time = new int[leng];
        for(int i = 0; i<time.length; i++)
            time[i] = 0;
    }
    
    public static void resetForm() {
        for(int i = 0; i<time.length; i++)
            time[i] = 0;
    }
    
    public static void countForm(int fram) { //used in OPT
        count = new int[fram];
        for(int i=0; i<fram; i++)
            count[i] = 0;
    }
    
    public static void countReset() {
        for(int i=0; i<count.length; i++)
            count[i] = 0;
    }
    
    public static void setFrame(int fram) {
        frames = new int[fram];
        for(int i=0; i<fram; i++)
            frames[i] = -1;
    }
    
    public static void resetFrame() {
        for(int i=0; i<frames.length; i++)
            frames[i] = -1;
    }
    
    public static int switchFifo(int fram) {
        memoryFIFO++;
        int mem = memoryFIFO%fram;
        for(int i = 0; i<fram-1; i++) {
            if(mem == i)
                return mem;
        }
        return fram-1;
    }
    
    public static int optimalSwitch(int pag) {
        for(int i = 0; i<count.length; i++) {
            for(int j = pag+1; j<page.length; j++) {
                if(frames[i] != page[j])
                    count[i]++;
                else {
                    count[i]++;
                    break;
                }
            }
        }
        int pos = 0;
        boolean notIn = false;
        for(int j = 1; j<count.length; j++) {
            if(count[j] > count[j-1]) {
                pos = j;
                notIn = false;
            }
            else
                notIn = true;
        }
        if(notIn) {
            pos = 0;
        }
        return pos;
    }
    
    public static int lruFind() {
        int pos = 0;
        for(int i = 1; i<time.length; i++) {
            if(time[i-1] < time[i])
                pos = i;
        }    
        return pos;
    }
    
    public static int fifo() {
        boolean hit;
        int switchPos,faults = 0;
        for(int i = 0; i<page.length; i++) {
            hit = false;
            for(int j = 0; j<frames.length; j++) {
                if(page[i] == frames[j]) {
                    hit = true;
                    break;
                }
            }
            for(int k = 0; k<frames.length; k++) {
                if(!hit && frames[k] == -1) {
                    frames[k] = page[i];
                    faults++;
                    hit = true;
                    break;
                }
            }
            if(!hit) {
                faults++;
                switchPos = switchFifo(frames.length);
                frames[switchPos] = page[i];
            }
        }
        return faults;
    }
    
    public static int optimal() {
        int faults = 0,optPos = 0;
        boolean hit;
        for(int i = 0; i<page.length; i++) {
            hit = false;
            for(int j = 0; j<frames.length; j++) {
                if(page[i] == frames[j]) {
                    hit = true;
                    break;
                }
            }
            for(int k = 0; k<frames.length; k++) {
                if(!hit && frames[k] == -1) {
                    frames[k] = page[i];
                    faults++;
                    hit = true;
                    break;
                }
            }
            if(!hit) {
               faults++;
               countReset();
               optPos = optimalSwitch(i);
               frames[optPos] = page[i];
            }
        }
        return faults;
    }
    
    public static int lru() {
        int flts = 0,lruPos = 0;
        boolean hit;
        for(int i = 0; i<page.length; i++) {
            hit = false;
            for(int j = 0; j<frames.length; j++) {
                if(page[i] == frames[j]) {
                    hit = true;
                    if(i >= frames.length-1) {
                        for(int d = 0; d<time.length; d++)
                            time[d]++;
                    }
                    else {
                        for(int a = 0; a < j; a++)
                            time[a]++;
                    }
                    time[j] = 0;
                    break;
                }
            }
            for(int k = 0; k<frames.length; k++) {
                if(!hit && frames[k] == -1) {
                    frames[k] = page[i];
                    flts++;
                    hit = true;
                    for(int n = 0; n <= k; n++)
                        time[n]++;
                    break;
                }
            }
            if(!hit) {
                flts++;
                lruPos = lruFind();
                frames[lruPos] = page[i];
                for(int h = 0; h<time.length; h++)
                    time[h]++;
                time[lruPos] = 0;
            }
        }
        return flts;
    }    
    
    public static void main(String[] args) {
        Scanner keyb = new Scanner(System.in);
        System.out.print("Enter number of page frames(1-5): ");
        System.out.println();
        int fr = keyb.nextInt();
        setFrame(fr);
        ffaults  = fifo();
        System.out.println("Faults Fifo: "+ffaults);
        resetFrame();
        countForm(fr);
        ofaults = optimal();
        System.out.println("Faults OPT: "+ofaults);
        resetFrame();
        timeForm(fr);
        lfaults = lru();
        System.out.println("Faults LRU: "+lfaults);        
    }
}
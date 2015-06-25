package matrixmult;

class WorkerThread implements Runnable {
    private int row; //row
    private int col; //column
    private int[][] A;
    private int[][] B;
    private int[][] C;
    
    public WorkerThread(int row, int col, int[][] A, int[][] B, int[][] C) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }
    
    @Override
    public void run() {
        int sum = 0;
        for(int i = 0; i < 2; i++) 
            sum = sum + (A[row][i]*B[i][col]);
        C[row][col] = sum;
    }
}

public class MatrixMult {
    
    public static void printMe(int[][] a, int rows, int cols) {
        for(int i = 0; i < rows; i++) {
            System.out.print("{ ");
            for(int j = 0; j < cols; j++)
                System.out.print(a[i][j]+" ");
            System.out.println("}");
        }
    }

    public static void main(String[] args) {
        int A [][] = { {1,4}, {2,5}, {3,6} };
        int B [][] = { {8,7,6}, {5,4,3} };
        int C [][] = new int[3][3];
        final int NUM_THREADS = 9;
        Thread[] workers = new Thread[NUM_THREADS];
        
        workers[0] = new Thread(new WorkerThread(0,0,A,B,C));
        workers[1] = new Thread(new WorkerThread(0,1,A,B,C));
        workers[2] = new Thread(new WorkerThread(0,2,A,B,C));
        workers[3] = new Thread(new WorkerThread(1,0,A,B,C));
        workers[4] = new Thread(new WorkerThread(1,1,A,B,C));
        workers[5] = new Thread(new WorkerThread(1,2,A,B,C));
        workers[6] = new Thread(new WorkerThread(2,0,A,B,C));
        workers[7] = new Thread(new WorkerThread(2,1,A,B,C));
        workers[8] = new Thread(new WorkerThread(2,2,A,B,C));
        
        for(int j = 0; j < NUM_THREADS; j++)
            workers[j].start();
        
        for(int i = 0; i < NUM_THREADS; i++) {
            try {
                workers[i].join();
            }
            catch (InterruptedException ie) {}
        }
        
        printMe(C,3,3);
    }
}

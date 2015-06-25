

import java.io.*;
class IntLit extends Node {
    private int intVal;

    public IntLit(int i) { intVal = i; }
  
    @Override
    public boolean isNumber(){return true;}

    public void print(int n) {
        if(n>1)
            System.out.println();
        for (int i = 0; i < n; i++)
            System.out.print(" ");
        System.out.print(intVal);
    }
}

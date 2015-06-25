

import java.io.*;
class BooleanLit extends Node {
    private boolean booleanVal;

    public BooleanLit(boolean b) {
        booleanVal = b;
    }
  
    @Override
    public boolean isBoolean(){return true;}

    public void print(int n) {
    if(n>1)
        System.out.println();
    for (int i = 0; i < n; i++)
        System.out.print(" ");
    
    if (booleanVal)
        System.out.print("#t");
    else
        System.out.print("#f");
    }
}

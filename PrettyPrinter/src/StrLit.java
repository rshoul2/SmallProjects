

import java.io.*;
class StrLit extends Node {
    private String strVal;

    public StrLit(String s) { strVal = s; }
  
    @Override
    public boolean isString(){return true;}

    public void print(int n) {
        if(n>1)
            System.out.println();
        for (int i = 0; i < n; i++)
            System.out.print(" ");

        System.out.print("\"" + strVal + "\"");
    }
}



import java.io.*;

class Set extends Special {
 
    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.

    void print(Node t, int n, boolean p) {
        System.out.print("(set!");

        Node c = t.getCdr();
        if(c.isPair()==false&&c.isNull()==false){
            System.out.print('.');
            c.print(0,false);
            System.out.print(')');
        }
        else
            c.print(1,true); 
    }
}

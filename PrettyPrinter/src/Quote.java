

import java.io.*;

class Quote extends Special {
 
    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.

    void print(Node t, int n, boolean p) {
        
        System.out.print("'");
        
        Node c = t.getCdr();
        if(c.isPair()==false&&c.isNull()==false){
            System.out.print('.');
            c.print(0,false);
        }
        else{
            c = c.getCar();
            c.print(0,false);
        }
    }
}

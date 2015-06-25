

import java.io.*;

class Begin extends Special {
 
    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.

    void print(Node t, int n, boolean p) {
        if(p==false){
            System.out.print("(");
        }
        System.out.print("begin");
        //print inside stuff indented
        Node c = t.getCdr();
        if(c.isNull()){
            c.print(0,true);
        }
        else if(c.isPair()){
            c.print((n-(n%4))+4,true);    
        }
        else{
            System.out.print('.');
            c.print(0,false);
            System.out.print(')');
        }
    }
}

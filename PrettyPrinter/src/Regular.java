

import java.io.*;

class Regular extends Special {
 
    // TODO: Add any fields needed.
    // TODO: Add an appropriate constructor.

    void print(Node t, int n, boolean p) {
        if(p==false){
            System.out.print("(");
        }
        
        t.getCar().print(0);

        Node c = t.getCdr();
        if(c.isPair()){
            if(n>1){
                c.print(n,true);
            }
            else{
                c.print(1,true);
            }
        }
        else if(c.isNull()){
            if(n>1){              
                System.out.println();
                c.print(n-4,true);
            }
            else
                c.print(0,true);    
        }
        else{
            System.out.print(".");
            c.print(0,false); 
            if(n>1){     
                System.out.println();
                Nil.getInstance().print(n-4,true);
            }
            else
                Nil.getInstance().print(0,true);
        }
    }
}

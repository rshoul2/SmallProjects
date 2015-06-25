

import java.io.*;

class If extends Special {

    void print(Node t, int n, boolean p) {
        if(p==false){
            System.out.print("(");
        }
        System.out.print("if");
        Node c = t.getCdr();
        if(c.isPair()==false&&c.isNull()==false){
            System.out.print('.');
            c.print(0,false);
            System.out.print(')');
        }
        else if(c.isNull()){
            c.print(0,true);
        }
        else{
            c.getCar().print(1, false);
            //print inside stuff indented
            c = c.getCdr();
            if(c.isNull()){
                c.print(0,true);
            }
            else if(c.isPair()){
                c.print((n-(n%4))+4,true);    
            }
            else{
                c.print((n-(n%4))+4,true);
                System.out.println();
                Nil.getInstance().print(n,true);
            }
        }
    }
}

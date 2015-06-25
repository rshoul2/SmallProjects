

import java.io.*;
class Nil extends Node {
    static Nil instance = new Nil();
    public Nil() { }
    public static Nil getInstance(){ return instance; }
    public boolean isNull(){return true;}
    public void print(int n){ print(n, false); }
    public void print(int n, boolean p) {
        if(n>1){
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        if (p) {
            System.out.print(")");
        } 
        else {
            System.out.print("()");
        }
    }
}

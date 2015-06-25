

// Special.java -- the parse tree node data structure for special forms

import java.io.*;

abstract class Special {
    //t-Node being printed
    //n-indentation needed before printed characters
    //p-switch for '(' already printed
    abstract void print(Node t, int n, boolean p);
}


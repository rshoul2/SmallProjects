

class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special form;
  
    // parseList() `parses' special forms, constructs an appropriate
    // object of a subclass of Special, and stores a pointer to that
    // object in variable form.  It would be possible to fully parse
    // special forms at this point.  Since this causes complications
    // when using (incorrect) programs as data, it is easiest to let
    // parseList only look at the car for selecting the appropriate
    // object from the Special hierarchy and to leave the rest of
    // parsing up to the interpreter.
    public void parseList() { 
        if(!car.isSymbol()){
            form = new Regular();
        }
        else if(car.getName().equalsIgnoreCase("quote")){
            form = new Quote();
        }
        else if(car.getName().equalsIgnoreCase("set!")){
            form = new Set();
        }
        else if(car.getName().equalsIgnoreCase("begin")){
            form = new Begin();
        }
        else if(car.getName().equalsIgnoreCase("cond")){
            form = new Cond();
        }
        else if(car.getName().equalsIgnoreCase("let")){
            form = new Let();
        }
        else if(car.getName().equalsIgnoreCase("if")){
            form = new If();
        }
        else if(car.getName().equalsIgnoreCase("define")){
            form = new Define();
        }
        else if(car.getName().equalsIgnoreCase("lambda")){
            form = new Lambda();
        }
        else {
            form = new Regular();
        }
    }

    public Cons(Node a, Node d) {
	car = a;
	cdr = d;
	parseList();
    }
    
    @Override
    public boolean isPair(){return true;}
    
    @Override
    public Node getCar() {
        return car;
    }
  
    @Override
    public Node getCdr() {
        return cdr;
    }
  
    @Override
    public void setCar(Node a) {
        car = a;
    }
  
    @Override
    public void setCdr(Node d) {
        cdr = d;
    }
    
    @Override
    void print(int n) {
	if(n>1)
            System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        form.print(this, n, false);
    }

    @Override
    void print(int n, boolean p) {
        if(n>1)
            System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
	form.print(this, n, p);
    }
}

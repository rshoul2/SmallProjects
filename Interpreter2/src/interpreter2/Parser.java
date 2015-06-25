package interpreter2;

import java.io.*;

class Parser {
    private Scanner scanner;

    public Parser(Scanner s) { scanner = s; }

    public Node parseExp() throws IOException {
        return parseExp(scanner.getNextToken());
    }

    protected Node parseRest(boolean first) throws IOException {
        return parseRest(scanner.getNextToken(),first);
    }

    public Node parseExp(Token tok) throws IOException{
        if(tok.getType()==0){
            return new Cons(new Ident("quote"),new Cons(parseExp(),Nil.getInstance()));
        }
        else if(tok.getType()==1){
            return parseRest(true);
        }
        else if(tok.getType()==4){
            return new BooleanLit(true);
        }
        else if(tok.getType()==5){
            return new BooleanLit(false);
        }
        else if(tok.getType()==6){
            return new IntLit(tok.getIntVal());
        }
        else if(tok.getType()==7){
            return new StrLit(tok.getStrVal());
        }
        else if(tok.getType()==8){
            return new Ident(tok.getName());
        }
        //finds ')' after finding dot
        else if(tok.getType()==2){
            return parseRest(tok,false);
        }
        return null;
  }

  public Node parseRest(Token tok, boolean first) throws IOException{
      if(tok.getType()==2){
          return Nil.getInstance();
      }
      if(tok.getType()==3){
          if(first==true){
              //error in starting with .
              System.out.println("Error: Dot is not preceded by any element.");
          }
          Node n = parseExp();
          Node m = parseExp();
          if(m.isNull()==false){
              //error in . preceding two exp
              System.out.println("Error: Dot is followed by too many elements.");
              return new Cons(n,new Cons(m,parseRest(false)));
          }
          return n;
      }
      else {
          return new Cons(parseExp(tok),parseRest(false));
      }
  }

}


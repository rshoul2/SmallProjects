package interpreter2;

import java.io.*;

class Scanner {
    private PushbackInputStream in;

    public Scanner(InputStream i) { in = new PushbackInputStream(i); }

    public Token getNextToken() throws IOException {
    int bite = -1;

    try {
        bite = in.read();
    } catch (IOException e) {
        System.err.println("We fail: " + e.getMessage());
    }

    char ch = (char) bite;
    // skip white space and comments
    while(ch == ' '||ch == ';'||ch == '\n'){
        ch = (char)in.read();
        while(ch == ' ') {
            ch = (char)in.read();
        }
        if(ch == ';'){
            while(ch != '\n') {
                ch = (char)in.read();
            }
        }
        if(ch == '\n') {
            ch = (char)in.read();
        }
    }
    // byte does not represent a character
    if (bite == -1){
        return null;
    }

    // Special characters
    if (ch == '\'') {
        return new Token(Token.QUOTE);
    }
    else if (ch == '(') {
        return new Token(Token.LPAREN);
    }
    else if (ch == ')') {
        return new Token(Token.RPAREN);
    }
    else if (ch == '.') {
        return new Token(Token.DOT);
    }
    // Boolean constants
    else if (ch == '#') {
        try {
            bite = in.read();
        } catch (IOException e) {
            System.err.println("We fail: " + e.getMessage());
        }

        if (bite == -1) {
            System.err.println("Unexpected EOF following #");
            return null;
        }
        ch = (char) bite;
        if (ch == 't') {
            return new Token(Token.TRUE);
        }
        else if (ch == 'f') {
            return new Token(Token.FALSE);
        }
        else {
            System.err.println("Illegal character '" + (char) ch + "' following #");
            return getNextToken();
        }
    }
    // String constants
    else if (ch == '"') {
        // scan an string into st
        ch = (char)in.read();
        int i=0;
        String st="";
        while(ch!='"'){
            st+=ch;
            ch = (char)in.read();
        }
        return new StrToken(st);
    }
    // Integer constants
    else if (ch >= '0' && ch <= '9') {
        int i = ch - '0';
        // scan the number and convert it to an integer
        ch = (char)in.read();
        while(ch >= '0' && ch <= '9'){
            i = i * 10;
            i += ch - '0';
            ch = (char)in.read();
        }
        // put the character after the integer back into the input
        // in->putback(ch);
        in.unread((int)ch);
        return new IntToken(i);
    }
    // Identifiers
    else if (ch >= 'A' && ch <= 'Z'|| ch >= 'a' && ch <= 'z'|| ch == '!' ||
    ch == '$' || ch == '%' || ch == '&' || ch == '*' || ch == '/' ||
    ch == ':' || ch == '<' || ch == '=' || ch == '>' ||
    ch == '?' || ch == '^' || ch == '_' || ch == '~') {
        // scan an identifier into st
        int i=0;
        String st="";
        while(ch!='"'&&ch!=' '&&ch!=';'&&ch!='\n'&&ch!='\''&&ch!='('&&ch!=')'&&ch!='.'){
            st+=ch;
            ch = (char)in.read();
        }
        // put the character after the identifier back into the input
        // in->putback(ch);
        in.unread((int)ch);
        return new IdentToken(st);
    }
    // Illegal character
    else {
        System.err.println("Illegal input character '" + (char) ch + '\'');
        return getNextToken();
    }
    }
}


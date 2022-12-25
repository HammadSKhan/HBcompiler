package Exceptions.Lexical;

public class LexicalUnexpectedEnd extends RuntimeException{

    public LexicalUnexpectedEnd(int lineNumber, String expected) {
        super("Unexpected EOF at Line : " + lineNumber + ". Expected : " + expected);
    }
}

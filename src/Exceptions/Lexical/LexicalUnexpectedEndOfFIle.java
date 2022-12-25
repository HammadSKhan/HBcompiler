package Exceptions.Lexical;

public class LexicalUnexpectedEndOfFIle extends RuntimeException{

    public LexicalUnexpectedEndOfFIle(int lineNumber, String expected) {
        super("Unexpected EOF at Line : " + lineNumber + ". Expected : " + expected);
    }
}

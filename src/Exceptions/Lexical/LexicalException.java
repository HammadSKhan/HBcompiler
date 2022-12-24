package Exceptions.Lexical;

import java.util.List;

public class LexicalException extends RuntimeException {


    public LexicalException(String input, int indexOfToken, int lineNumber) {
        super("LEXICAL EXCEPTION : There was an error near '" + input.substring(indexOfToken - 2, indexOfToken + 1) + "'"
                + " on line number : " + lineNumber);
    }

    public LexicalException(String input, int indexOfToken, int lineNumber, List<String> expected) {
        super("LEXICAL EXCEPTION : There was an error on line number " + lineNumber + " near " +
                "'" + input.substring(indexOfToken - 2, indexOfToken + 1) + "'" +
                " Expected " + expected.toString());
    }
}

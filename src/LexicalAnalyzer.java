import Exceptions.LexicalException;

public class LexicalAnalyzer {
    public final String inputText;
    public int currentPointer;
    public int currentLine;

    public LexicalAnalyzer(String inputText) {
        this.inputText = inputText;
        this.currentPointer = 0;
        this.currentLine = 1;
    }

    public String analyze() throws LexicalException {

        getNextToken();
        return "";
    }

    Token recognizeToken(String lexeme) throws LexicalException {
        //Will return some token
        switch (lexeme.toLowerCase()) { // For case Insensitivity
            case "int":
                return new Token(TokenName.INT);
            case "char":
                return new Token(TokenName.CHAR);
            case "string":
                return new Token(TokenName.STRING);
            case "if":
                return new Token(TokenName.IF);
            case "else":
                return new Token(TokenName.ELSE);
            case "do":
                return new Token(TokenName.DO);
            case "while":
                return new Token(TokenName.WHILE);
            default:
                // if (lexeme) {

                // }

                break;
        }
        throw new LexicalException(inputText, currentPointer);
    }

    Token getNextToken() throws LexicalException {
        while (true) {
            char currentChar = inputText.charAt(currentPointer);
            //Will return some token
            switch (currentChar) {
                // This will have all our token recognition transition stufff ?????
                case '1':
                    return new Token(TokenName.AOP, "asd");
                default:
                    break;
            }
        }
        // throw new LexicalException(inputText, currentPointer);
    }

}

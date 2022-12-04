import Exceptions.LexicalException;

public class LexicalAnalyzer {
    public final String inputText;
    public int currentPointer;
    public int currentLine;

    public SymbolTable symbolTable;

    public LexicalAnalyzer(String inputText) {
        this.inputText = inputText;
        this.currentPointer = 0;
        this.currentLine = 1;
        this.symbolTable = new SymbolTable();
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
                if (Helpers.isInteger(lexeme)) {
                    return new Token(TokenName.IV, lexeme);
                } else if (Helpers.isStringLiteral(lexeme)) {
                    return new Token(TokenName.SL, lexeme);
                } else {
                    return new Token(TokenName.ID, lexeme);
                }
//               else{
//                  throw new LexicalException(inputText, currentPointer);
//               }
        }
    }


    Token getNextToken() throws LexicalException {
        char c = nextChar();

        if(c == ' ') {

        }


        throw new LexicalException(inputText, currentPointer);
    }

    char nextChar() {
        char currentChar = inputText.charAt(currentPointer);
        currentPointer++;
        if(currentChar == '\n') {
            currentLine++;
        }
        return currentChar;
    }
    char retractChar() {
        currentPointer--;
        char currentChar = inputText.charAt(currentPointer);
        if(currentChar == '\n') {
            currentLine--;
        }
        return currentChar;
    }
}

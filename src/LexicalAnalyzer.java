import Exceptions.Lexical.GeneralLexicalException;

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

    public String analyze() {
        StringBuilder stringBuilder = new StringBuilder();
        Token token = getNextToken();
        while (token != null) {
            stringBuilder.append(token + "\n");
            token = getNextToken();
        }
        return stringBuilder.toString();
    }

    Token recognizeKeywordAndId(String lexeme) {
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
                return new Token(TokenName.ID, lexeme);
        }
    }


    Token getNextToken() throws GeneralLexicalException {
        char c = nextChar();
        //Ignore extra spaces
        if (c == ' ') {
            while ((c = nextChar()) == ' ') {
                //
            }
        }

        if (c == Helpers.EOF) {
            return null;
        }

        if (c == '\"') {
            StringBuilder stringBuilder = new StringBuilder();
            while ((c = nextChar()) != '\"') {
                stringBuilder.append(c);
            }
            return new Token(TokenName.SL, "\"" + stringBuilder + "\"");
        }

        if (Helpers.isAlpha(c)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            c = nextChar();
            while (Helpers.isAlpha(c)
                    || Helpers.isInteger(c)
                //  || c == '_'
            ) {
                stringBuilder.append(c);
                c = nextChar();
            }
            return recognizeKeywordAndId(stringBuilder.toString());
        }

        if (Helpers.isInteger(c)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            c = nextChar();
            while (Helpers.isInteger(c)) {
                stringBuilder.append(c);
                c = nextChar();
            }
            if (!Helpers.isAlpha(c)) {
//                retractChar();
                return new Token(TokenName.IV, stringBuilder.toString());
            } else {
                throw new GeneralLexicalException(inputText, currentPointer);
            }
        }

        throw new GeneralLexicalException(inputText, currentPointer);
    }

    char nextChar() {
        if (currentPointer < inputText.length()) {
            char currentChar = inputText.charAt(currentPointer);
            currentPointer++;
            if (currentChar == '\n') {
                currentLine++;
            }
            return currentChar;
        } else {
            return Helpers.EOF;
        }
    }

    char retractChar() {
        currentPointer--;
        char currentChar = inputText.charAt(currentPointer);
        if (currentChar == '\n') {
            currentLine--;
        }
        return currentChar;
    }
}

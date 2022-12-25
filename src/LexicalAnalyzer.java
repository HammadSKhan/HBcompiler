import Exceptions.Lexical.LexicalException;
import Exceptions.Lexical.LexicalUnexpectedEndOfFIle;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {
    public final String inputText;
    public int currentPointer;
    public int currentLine;
    public SymbolTable symbolTable;

    public LexicalAnalyzer(String inputText) {
        this.inputText = inputText + ' ';
        this.currentPointer = 0;
        this.currentLine = 1;
        this.symbolTable = new SymbolTable();
    }

    public String analyze() {
        StringBuilder stringBuilder = new StringBuilder();
        Token token = getNextToken();
        while (token != null) {
            stringBuilder.append(token + "\n");
            System.out.println(" recognized token = " + token);
            token = getNextToken();

        }
        return stringBuilder.toString();
    }

    public ArrayList<Token> analyzeList() {
        ArrayList<Token> tokenArrayList = new ArrayList<>();
        Token token = getNextToken();
        while (token != null) {
            if (token.getName() != TokenName.COMMENT) {
                tokenArrayList.add(token);
            }
            token = getNextToken();
        }
        return tokenArrayList;
    }

    Token recognizeKeywordAndId(String lexeme) {
        //Will return some token
        switch (lexeme.toLowerCase()) { // For case Insensitivity
            case "int":
                return symbolTable.add(new Token(TokenName.INT), lexeme);
            case "char":
                return symbolTable.add(new Token(TokenName.CHAR), lexeme);
            case "string":
                return symbolTable.add(new Token(TokenName.STRING), lexeme);
            case "if":
                return symbolTable.add(new Token(TokenName.IF), lexeme);
            case "else":
                return symbolTable.add(new Token(TokenName.ELSE), lexeme);
            case "do":
                return symbolTable.add(new Token(TokenName.DO), lexeme);
            case "while":
                return symbolTable.add(new Token(TokenName.WHILE), lexeme);
            default:
                return symbolTable.add(new Token(TokenName.ID), lexeme);
        }
    }

    Token recognizeEverythingElse(String lexeme) {
        //Will return some token
        switch (lexeme.toLowerCase()) { // For case Insensitivity
            case "<":
                return new Token(TokenName.ROP, TokenValue.LT);
            case "<=":
                return new Token(TokenName.ROP, TokenValue.LE);
            case "==":
                return new Token(TokenName.ROP, TokenValue.EQ);
            case "<>":
                return new Token(TokenName.ROP, TokenValue.NE);
            case ">=":
                return new Token(TokenName.ROP, TokenValue.GE);
            case ">":
                return new Token(TokenName.ROP, TokenValue.GT);
            case "+":
                return new Token(TokenName.AOP, TokenValue.AD);
            case "-":
                return new Token(TokenName.AOP, TokenValue.SB);
            case "*":
                return new Token(TokenName.AOP, TokenValue.ML);
            case "/":
                return new Token(TokenName.AOP, TokenValue.DV);
            case "=":
                return new Token(TokenName.OOP, TokenValue.AS);
            case "(":
                return new Token(TokenName.OOP, TokenValue.OP);
            case ")":
                return new Token(TokenName.OOP, TokenValue.CP);
            case "{":
                return new Token(TokenName.OOP, TokenValue.OB);
            case "}":
                return new Token(TokenName.OOP, TokenValue.CB);
            case ";":
                return new Token(TokenName.OOP, TokenValue.TR);
            default:
                return null;
        }
    }

    Token getNextToken() throws LexicalException {
        char c = nextChar();
        //Ignore extra spaces
        if (c == ' ' || c == '\n' || c == '\t') {
            while ((c = nextChar()) == ' ' || c == '\n' || c == '\t') {
                //
            }
        }

        if (c == Helpers.EOF) {
            return null;
        }

        // Read Single Line Multi Line Comments
        if (c == '/') {
            c = nextChar();
            if (c == '/') {
                while ((c = nextChar()) != '\n' && c != Helpers.EOF) {
                    //
                }
                // Error fix temp: Returning COMMENT Token
                return new Token(TokenName.COMMENT);
            } else if (c == '*') {
                while ((c = nextChar()) != '*' && (c = nextChar()) != '/') {
                    if(c == Helpers.EOF){
                        throw new LexicalUnexpectedEndOfFIle(currentLine, "*/");
                    }
//                    System.out.println("NEXT CHAR =====" + c);
                    //
                }
                // Error fix: read next char if c = new line AND returning COMMENT Token
                c = nextChar();
                return new Token(TokenName.COMMENT);
            } else {
//                throw new LexicalException(inputText, currentPointer, currentLine);
//                retractChar();
                return recognizeEverythingElse("/");
            }
        }

        // Token For String Literal
        if (c == '\"') {
            StringBuilder stringBuilder = new StringBuilder();
            while ((c = nextChar()) != '\"') {
                stringBuilder.append(c);
            }
            return symbolTable.add(new Token(TokenName.SL), stringBuilder.toString());
        }

        // Token For ID
        if (Helpers.isAlpha(c)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            c = nextChar();
            while (Helpers.isAlpha(c)
                    || Helpers.isInteger(c)) {
                stringBuilder.append(c);
                c = nextChar();
            }
            retractChar();
            return recognizeKeywordAndId(stringBuilder.toString());
        }

        // Token For Integer Value
        if (Helpers.isInteger(c)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            c = nextChar();
            while (Helpers.isInteger(c)) {
                stringBuilder.append(c);
                c = nextChar();
            }
            if (!Helpers.isAlpha(c) || c == ' ') {
                retractChar();
                return symbolTable.add(new Token(TokenName.IV), stringBuilder.toString()); // new Token(TokenName.IV, stringBuilder.toString());
            } else {
                throw new LexicalException(inputText, currentPointer, currentLine, List.of("Non Alpha", "Or Integer"));
            }
        }

        // Token For AS and EQ
        if (c == '=') {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            c = nextChar();
            if (Helpers.isAlpha(c) || Helpers.isInteger(c) || c == ' ') {
                retractChar();
                return recognizeEverythingElse(stringBuilder.toString());
            } else if (c == '=') {
                stringBuilder.append(c);
                c = nextChar();
                if (Helpers.isAlpha(c) || Helpers.isInteger(c) || c == ' ' || c == '\n') {
                    retractChar();
                    return recognizeEverythingElse(stringBuilder.toString());
                }
            } else {
                throw new LexicalException(inputText, currentPointer, currentLine, List.of("=", "or Alpha or Integer"));
            }
        }

        // Token for LT, LE And NE
        if (c == '<') {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            c = nextChar();
            if (Helpers.isAlpha(c) || Helpers.isInteger(c) || c == ' ' || c == '\n') {
                retractChar();
                return recognizeEverythingElse(stringBuilder.toString());
            } else if (c == '=' || c == '>') {
                stringBuilder.append(c);
                c = nextChar();
                if (Helpers.isAlpha(c) || Helpers.isInteger(c) || c == ' ' || c == '\n') {
                    retractChar();
                    return recognizeEverythingElse(stringBuilder.toString());
                }
            } else {
                throw new LexicalException(inputText, currentPointer, currentLine, List.of(" = ", " or >", " or Alpha or Integer"));
            }
        }

        // Token For GT And GE
        if (c == '>') {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            c = nextChar();
            if (Helpers.isAlpha(c) || Helpers.isInteger(c) || c == ' ' || c == '\n') {
                retractChar();
                return recognizeEverythingElse(stringBuilder.toString());
            } else if (c == '=') {
                stringBuilder.append(c);
                c = nextChar();
                if (Helpers.isAlpha(c) || Helpers.isInteger(c) || c == ' ' || c == '\n') {
                    retractChar();
                    return recognizeEverythingElse(stringBuilder.toString());
                }
            } else {
                throw new LexicalException(inputText, currentPointer, currentLine, List.of("=", "or Alpha or Integer"));
            }
        }

        // Token For +
        ArrayList<Character> OAlist = new ArrayList<>();
        OAlist.add('+');
        OAlist.add('-');
        OAlist.add('*');
        OAlist.add('/');
        OAlist.add('(');
        OAlist.add(')');
        OAlist.add('{');
        OAlist.add('}');
        OAlist.add(';');
        Token OopAop = checkAopOop(c, OAlist);
        if (OopAop != null) {
            return OopAop;
        }

        throw new LexicalException(inputText, currentPointer, currentLine);
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
        if (currentPointer < inputText.length()) {
            currentPointer--;
            char currentChar = inputText.charAt(currentPointer);
            if (currentChar == '\n') {
                currentLine--;
            }
        }
        return inputText.charAt(currentPointer);
    }

    Token checkAopOop(char c, ArrayList<Character> input) {

        if (input.contains(c)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            return recognizeEverythingElse(stringBuilder.toString());
        }
        return null;
    }
}

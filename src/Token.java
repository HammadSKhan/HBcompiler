enum TokenValue {
    LE, LT, EQ, NE, GE, GT,
    AD, SB, ML, DV, AS,
    OP, CP, OB, CB, TR
}

enum TokenName {
    INT, CHAR, STRING, IF, ELSE, DO, WHILE, ROP, AOP, OOP, ID, SL, IV, COMMENT
}

public class Token {

    private final TokenName name;
    private String value;
    private String lexeme;

    public Token(TokenName name) {
        this.name = name;
    }

    public Token(TokenName name, String value, String lexeme) {
        this.name = name;
        this.value = value;
        this.lexeme = lexeme;
    }

    public Token(TokenName name, TokenValue value, String lexeme) {
        this.name = name;
        this.value = value.name();
        this.lexeme = lexeme;
    }

    /**
     * @return the name
     */
    public TokenName getName() {
        return name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token {" + "name= " + name + " , attributeValue= '" + value + "'"  + "}" + " , lexeme= '" + lexeme + "' ";
    }
}
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

    public Token(TokenName name) {
        this.name = name;
    }

    public Token(TokenName name, String value) {
        this.name = name;
        this.value = value;
    }

    public Token(TokenName name, TokenValue value) {
        this.name = name;
        this.value = value.name();
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
        return "Token {" + "name=" + name + ", value='" + value + "'" + "}";
    }
}
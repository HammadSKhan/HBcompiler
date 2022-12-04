public class SymbolTableEntry {
    private String lexeme;
    private String tokenName;
    private String attributeValue;

    public SymbolTableEntry(String lexeme, String tokenName, String attributeValue) {
        this.lexeme = lexeme;
        this.tokenName = tokenName;
        this.attributeValue = attributeValue;
    }

    public SymbolTableEntry(String lexeme, String tokenName) {
        this.lexeme = lexeme;
        this.tokenName = tokenName;
    }

    public String getLexeme() {
        return lexeme;
    }

    public String getTokenName() {
        return tokenName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }
}

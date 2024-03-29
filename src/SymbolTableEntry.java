public class SymbolTableEntry {
    private int attributeValue;
    private String tokenName;
    private String lexemeValue;

    public SymbolTableEntry(int attributeValue, String tokenName, String lexemeValue) {
        this.lexemeValue = lexemeValue;
        this.tokenName = tokenName;
        this.attributeValue = attributeValue;
    }

    public String getLexemeValue() {
        return lexemeValue;
    }

    public String getTokenName() {
        return tokenName;
    }

    int getAttributeValue() {
        return attributeValue;
    }
}

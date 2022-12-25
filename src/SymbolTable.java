import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SymbolTable {
    public ArrayList<SymbolTableEntry> entries;

    public SymbolTable() {
        entries = new ArrayList<SymbolTableEntry>(List.of(
                new SymbolTableEntry(0, "INT", "int"),
                new SymbolTableEntry(1, "CHAR", "char"),
                new SymbolTableEntry(2, "STRING", "string"),
                new SymbolTableEntry(3, "IF", "if"),
                new SymbolTableEntry(4, "ELSE", "else"),
                new SymbolTableEntry(5, "DO", "do"),
                new SymbolTableEntry(6, "WHILE", "while")
        ));
    }

    public Token add(Token token, String lexemeValue) {
        SymbolTableEntry ste = findEntry(lexemeValue);
        if (ste == null) {
            ste = new SymbolTableEntry(entries.size(), token.getName().name(), lexemeValue);
            entries.add(ste);
        }
        return new Token(token.getName(), ste.getAttributeValue() + "", ste.getLexemeValue());
    }

    // Checks whether entry is already in the SymbolTable or not
    public SymbolTableEntry findEntry(String lexemeValue) {
        for (SymbolTableEntry entry : entries) {
            if ((Objects.equals(lexemeValue, entry.getLexemeValue()))) {
                return entry;
            }
        }
        return null;
    }

    public SymbolTableEntry getEntry(int attributeValue){
        for (SymbolTableEntry entry : entries) {
            if (entry.getAttributeValue() == attributeValue) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (SymbolTableEntry entry : entries) {
            stringBuilder.append(entry.getAttributeValue() + "\t\t" + entry.getTokenName() + "\t" + " \t  - " + "\t" + entry.getLexemeValue() + "\n");
        }
        return "SymbolTable:\n" +
                "Attribute Value\t" + "Token Name\t" + "\tType\t" + "Value\n" +
                stringBuilder;
    }
}


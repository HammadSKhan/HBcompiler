import java.util.ArrayList;

public class SyntacticAnalyzer {

    ParsingTable pt = new ParsingTable();
    SymbolTable st;
    ArrayList<Token> al;
    public SyntacticAnalyzer(ArrayList<Token> tokenArrayList,SymbolTable symbolTable) {
        al = tokenArrayList;
        st = symbolTable;
    }

    public String analyze(){
        return "";
    }
}

import java.util.ArrayList;
import java.util.Stack;

public class SyntacticAnalyzer {

    ParsingTable pt = new ParsingTable();
    SymbolTable symbolTable;
    ArrayList<Token> inputBuffer;
    public SyntacticAnalyzer(ArrayList<Token> tokenArrayList,SymbolTable symbolTable) {
        inputBuffer = tokenArrayList;
        this.symbolTable = symbolTable;
    }

    Stack<String> inputStack = new Stack<>();
    public String analyze(){
        return "";
    }
}

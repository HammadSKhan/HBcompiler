public class Compiler {
    static String runLexicalAnalyzer(String inputText) {
        LexicalAnalyzer la = new LexicalAnalyzer(inputText);
        return la.analyze() + la.symbolTable.toString();
    }

    static String runSyntacticAnalyzer(String inputText) {
        LexicalAnalyzer la = new LexicalAnalyzer(inputText);
        SyntacticAnalyzer sa = new SyntacticAnalyzer(la.analyzeList(), la.symbolTable);
        return sa.analyze() + la.symbolTable.toString();
    }
}

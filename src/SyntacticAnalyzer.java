import DataStructures.ActionValue;
import DataStructures.ParsingAction;
import DataStructures.ReduceValue;
import Exceptions.Syntactic.SyntacticException;

import java.util.ArrayList;
import java.util.Stack;

public class SyntacticAnalyzer {

    Stack<String> inputStack = new Stack<>();
    ParsingTable parsingTable = new ParsingTable();
    SymbolTable symbolTable;
    ArrayList<Token> inputBuffer;
    int inputCounter = 0;


    public SyntacticAnalyzer(ArrayList<Token> tokenArrayList, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.inputBuffer = tokenArrayList;
    }

    public String analyze() {
        String state = "0";
        String input = getNextTerminal();
        inputStack.push(state);

        while (true) {
            state = inputStack.peek(); // state at top of stack

            ActionValue action = parsingTable.getAction(state, input); // action has (shift, reduce or accept) & (next state)
            if (action == null) {
                throw new SyntacticException(syntacticErrorToken());
            } else if (action.parsingAction == ParsingAction.shift) {
                inputStack.push(input);
                inputStack.push(action.state);
                input = getNextTerminal();
            } else if (action.parsingAction == ParsingAction.reduce) {
                ReduceValue rValue = parsingTable.getReduction(action.state); // state is production (1-6) in this case
                if (rValue == null) {
                    throw new SyntacticException(syntacticErrorToken());
                }
                try {
                    for (int i = 0; i < (rValue.count * 2); i++) {
                        inputStack.pop();
                    }
                } catch (Exception e) {
                    throw new SyntacticException(syntacticErrorToken());
                }
                state = inputStack.peek();
                inputStack.push(rValue.nonTerminal);
                String temp1 = parsingTable.getGoto(state, rValue.nonTerminal) + "";
                if (temp1 == null) {
                    throw new SyntacticException(syntacticErrorToken());
                }
                inputStack.push(temp1);


            } else if (action.parsingAction == ParsingAction.accept) {
                break;
            } else {
                throw new SyntacticException(syntacticErrorToken());
            }
        }

        return "Compilation Successful ;)\n";
    }

    private String getNextTerminal() {
        if (inputCounter >= inputBuffer.size()) {
            return "$";
        }
        Token input = inputBuffer.get(inputCounter);
        inputCounter++;
        if (input.getName() == TokenName.OOP || input.getName() == TokenName.AOP) {
            return input.getValue(); // e.g. BO for bracket open
        } else {
            return input.getName().name();
        }
    }

    private String syntacticErrorToken() {
        int temp = inputCounter - 1;
        if (inputBuffer.get(temp).getName() == TokenName.ID) {
            String temp1 = symbolTable.getEntry(Integer.parseInt(inputBuffer.get(temp).getValue())).getLexemeValue();
            return temp1;
        } else {
            return convertToSourceCharacter(inputBuffer.get(temp).getValue());
        }
    }

    private String convertToSourceCharacter(String value) {
        switch (value) {
            case "TR":
                return ";";
            case "CP":
                return ")";
            case "OP":
                return "(";
            case "OB":
                return "{";
            case "CB":
                return "}";
            case "AD":
                return "+";
            case "SB":
                return "-";
            case "ML":
                return "*";
            case "DV":
                return "/";
            case "AS":
                return "=";
            case "LT":
                return "<";
            case "LE":
                return "<=";
            case "GT":
                return ">";
            case "GE":
                return ">=";
            case "EQ":
                return "==";
        }
        return "";
    }
}

import java.util.ArrayList;
import java.util.Stack;

import DataStructures.ActionValue;
import DataStructures.ParsingAction;
import DataStructures.ReduceValue;
import Exceptions.Syntactic.SyntacticException;

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

			ActionValue action = parsingTable.getAction(state, input);
			if (action.parsingAction == ParsingAction.shift) {
				inputStack.push(input);
				inputStack.push(action.state);

				input = getNextTerminal();
			} else if (action.parsingAction == ParsingAction.reduce) {
				ReduceValue rValue = parsingTable.getReduction(action.state);
				for (int i = 0; i < rValue.count; i++) {
					inputStack.pop();
				}
				inputStack.push(rValue.nonTerminal);
			} else if (action.parsingAction == ParsingAction.accept) {
				break;
			} else {
				throw new SyntacticException(input);
			}
		}

		return "Compilation Successfull ;)";
	}

	private String getNextTerminal() {
		Token input = inputBuffer.get(inputCounter);
		if (input.getName() == TokenName.OOP || input.getName() == TokenName.AOP) {
			return input.getValue(); // e.g. BO for bracket open
		} else {
			return input.getName().name();
		}
	}

}

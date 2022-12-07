import java.util.Hashtable;

import DataStructures.ActionValue;
import DataStructures.ParsingAction;
import DataStructures.ReduceValue;

public class ParsingTable {
    Hashtable<String, ActionValue> actionTable = new Hashtable<>();
    Hashtable<String, Integer> gotoTable = new Hashtable<>();
    Hashtable<String, ReduceValue> reduceTable = new Hashtable<>();

    public ParsingTable() {
        actionTable.put("0-id", actionValue(ParsingAction.shift, 5));
        actionTable.put("0-(", actionValue(ParsingAction.shift, 4));
        actionTable.put("1-+", actionValue(ParsingAction.shift, 6));
        actionTable.put("1-$", actionValue(ParsingAction.accept, -1));
        actionTable.put("2-+", actionValue(ParsingAction.reduce, 2));
        actionTable.put("2-*", actionValue(ParsingAction.shift, 7));
        actionTable.put("2-)", actionValue(ParsingAction.reduce, 2));
        actionTable.put("2-$", actionValue(ParsingAction.reduce, 2));
        actionTable.put("3-+", actionValue(ParsingAction.reduce, 4));
        actionTable.put("3-*", actionValue(ParsingAction.reduce, 4));
        actionTable.put("3-)", actionValue(ParsingAction.reduce, 4));
        actionTable.put("3-$", actionValue(ParsingAction.reduce, 4));
        actionTable.put("4-id", actionValue(ParsingAction.shift, 5));
        actionTable.put("4-(", actionValue(ParsingAction.shift, 4));
        actionTable.put("5-+", actionValue(ParsingAction.reduce, 6));
        actionTable.put("5-*", actionValue(ParsingAction.reduce, 6));
        actionTable.put("5-)", actionValue(ParsingAction.reduce, 6));
        actionTable.put("5-$", actionValue(ParsingAction.reduce, 6));
        actionTable.put("6-id", actionValue(ParsingAction.shift, 5));
        actionTable.put("6-(", actionValue(ParsingAction.shift, 4));
        actionTable.put("7-id", actionValue(ParsingAction.shift, 5));
        actionTable.put("7-(", actionValue(ParsingAction.shift, 4));
        actionTable.put("8-+", actionValue(ParsingAction.shift, 6));
        actionTable.put("8-)", actionValue(ParsingAction.shift, 11));
        actionTable.put("9-+", actionValue(ParsingAction.reduce, 1));
        actionTable.put("9-*", actionValue(ParsingAction.shift, 7));
        actionTable.put("9-)", actionValue(ParsingAction.reduce, 1));
        actionTable.put("9-$", actionValue(ParsingAction.reduce, 1));
        actionTable.put("10-+", actionValue(ParsingAction.reduce, 3));
        actionTable.put("10-*", actionValue(ParsingAction.reduce, 3));
        actionTable.put("10-)", actionValue(ParsingAction.reduce, 3));
        actionTable.put("10-$", actionValue(ParsingAction.reduce, 3));
        actionTable.put("11-+", actionValue(ParsingAction.reduce, 5));
        actionTable.put("11-*", actionValue(ParsingAction.reduce, 5));
        actionTable.put("11-)", actionValue(ParsingAction.reduce, 5));
        actionTable.put("11-$", actionValue(ParsingAction.reduce, 5));
        //------------------------GOTO----------------//
        gotoTable.put("0-E", 1);
        gotoTable.put("0-T", 2);
        gotoTable.put("0-F", 3);
        gotoTable.put("4-E", 8);
        gotoTable.put("4-T", 2);
        gotoTable.put("4-F", 3);
        gotoTable.put("6-T", 9);
        gotoTable.put("6-F", 3);
        gotoTable.put("7-F", 10);

        reduceTable.put("1", new ReduceValue("E", 3));
        reduceTable.put("2", new ReduceValue("E", 1));
        reduceTable.put("3", new ReduceValue("T", 3));
        reduceTable.put("4", new ReduceValue("T", 1));
        reduceTable.put("5", new ReduceValue("F", 3));
        reduceTable.put("6", new ReduceValue("F", 1));
    }

    private ActionValue actionValue(ParsingAction parsingAction, int state) {
        return new ActionValue(parsingAction, state + "");
    }

    public ActionValue getAction(String state, String terminal) {
        return actionTable.get(state + "-" + terminal);
    }

    public int getGoto(String state, String nonTerminal) {
        return gotoTable.get(state + "-" + nonTerminal);
    }

    public ReduceValue getReduction(String production) {
        return reduceTable.get(production);
    }

    public boolean isNonTerminal(String input) {
        return (input == "E" || input == "T" || input == "F");
    }
}

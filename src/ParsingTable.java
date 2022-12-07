import java.util.Hashtable;

public class ParsingTable {
    Hashtable<String, ActionReturnValue> actionTable = new Hashtable<>();
    Hashtable<String, Integer> gotoTable = new Hashtable<>();

    public ParsingTable() {
        actionTable.put("0-id", new ActionReturnValue(ParsingAction.shift,5));
        actionTable.put("0-(", new ActionReturnValue(ParsingAction.shift,4));
        actionTable.put("1-+", new ActionReturnValue(ParsingAction.shift,6));
        actionTable.put("1-$", new ActionReturnValue(ParsingAction.accept,-1));
        actionTable.put("2-+", new ActionReturnValue(ParsingAction.reduce,2));
        actionTable.put("2-*", new ActionReturnValue(ParsingAction.shift,7));
        actionTable.put("2-)", new ActionReturnValue(ParsingAction.reduce,2));
        actionTable.put("2-$", new ActionReturnValue(ParsingAction.reduce,2));
        actionTable.put("3-+", new ActionReturnValue(ParsingAction.reduce,4));
        actionTable.put("3-*", new ActionReturnValue(ParsingAction.reduce,4));
        actionTable.put("3-)", new ActionReturnValue(ParsingAction.reduce,4));
        actionTable.put("3-$", new ActionReturnValue(ParsingAction.reduce,4));
        actionTable.put("4-id", new ActionReturnValue(ParsingAction.shift,5));
        actionTable.put("4-(", new ActionReturnValue(ParsingAction.shift,4));
        actionTable.put("5-+", new ActionReturnValue(ParsingAction.reduce,6));
        actionTable.put("5-*", new ActionReturnValue(ParsingAction.reduce,6));
        actionTable.put("5-)", new ActionReturnValue(ParsingAction.reduce,6));
        actionTable.put("5-$", new ActionReturnValue(ParsingAction.reduce,6));
        actionTable.put("6-id", new ActionReturnValue(ParsingAction.shift,5));
        actionTable.put("6-(", new ActionReturnValue(ParsingAction.shift,4));
        actionTable.put("7-id", new ActionReturnValue(ParsingAction.shift,5));
        actionTable.put("7-(", new ActionReturnValue(ParsingAction.shift,4));
        actionTable.put("8-+", new ActionReturnValue(ParsingAction.shift,6));
        actionTable.put("8-)", new ActionReturnValue(ParsingAction.shift,11));
        actionTable.put("9-+", new ActionReturnValue(ParsingAction.reduce,1));
        actionTable.put("9-*", new ActionReturnValue(ParsingAction.shift,7));
        actionTable.put("9-)", new ActionReturnValue(ParsingAction.reduce,1));
        actionTable.put("9-$", new ActionReturnValue(ParsingAction.reduce,1));
        actionTable.put("10-+", new ActionReturnValue(ParsingAction.reduce,3));
        actionTable.put("10-*", new ActionReturnValue(ParsingAction.reduce,3));
        actionTable.put("10-)", new ActionReturnValue(ParsingAction.reduce,3));
        actionTable.put("10-$", new ActionReturnValue(ParsingAction.reduce,3));
        actionTable.put("11-+", new ActionReturnValue(ParsingAction.reduce,5));
        actionTable.put("11-*", new ActionReturnValue(ParsingAction.reduce,5));
        actionTable.put("11-)", new ActionReturnValue(ParsingAction.reduce,5));
        actionTable.put("11-$", new ActionReturnValue(ParsingAction.reduce,5));
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
    }

    public ActionReturnValue getAction(String state, String terminal) {
        return actionTable.get(state + "-" + terminal);
    }

    public int getGoto(String state, String nonTerminal) {
        return gotoTable.get(state + "-" + nonTerminal);
    }
}

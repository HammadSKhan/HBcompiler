import java.util.Hashtable;

public class ParsingTable {
    Hashtable<String, String> actionTable = new Hashtable<>();
    Hashtable<String, Integer> gotoTable = new Hashtable<>();

    public ParsingTable() {
        actionTable.put("0-id", "s5");
        actionTable.put("0-(", "s4");
        actionTable.put("1-+", "s6");
        actionTable.put("1-$", "accept");
        actionTable.put("2-+", "r2");
        actionTable.put("2-*", "s7");
        actionTable.put("2-)", "r2");
        actionTable.put("2-$", "r2");
        actionTable.put("3-+", "r4");
        actionTable.put("3-*", "r4");
        actionTable.put("3-)", "r4");
        actionTable.put("3-$", "r4");
        actionTable.put("4-id", "s5");
        actionTable.put("4-(", "s4");
        actionTable.put("5-+", "r6");
        actionTable.put("5-*", "r6");
        actionTable.put("5-)", "r6");
        actionTable.put("5-$", "r6");
        actionTable.put("6-id", "s5");
        actionTable.put("6-(", "s4");
        actionTable.put("7-id", "s5");
        actionTable.put("7-(", "s4");
        actionTable.put("8-+", "s6");
        actionTable.put("8-)", "s11");
        actionTable.put("9-+", "r1");
        actionTable.put("9-*", "s7");
        actionTable.put("9-)", "r1");
        actionTable.put("9-$", "r1");
        actionTable.put("10-+", "r3");
        actionTable.put("10-*", "r3");
        actionTable.put("10-)", "r3");
        actionTable.put("10-$", "r3");
        actionTable.put("11-+", "r5");
        actionTable.put("11-*", "r5");
        actionTable.put("11-)", "r5");
        actionTable.put("11-$", "r5");
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

    public String getAction(String state, String terminal) {
        return actionTable.get(state + "-" + terminal);
    }

    public int getGoto(String state, String nonTerminal) {
        return gotoTable.get(state + "-" + nonTerminal);
    }
}

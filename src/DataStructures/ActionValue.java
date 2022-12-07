package DataStructures;

public class ActionValue {
    public final ParsingAction parsingAction;
    public final String state;

    public ActionValue(ParsingAction parsingAction, String state) {
        this.parsingAction = parsingAction;
        this.state = state;
    }
}
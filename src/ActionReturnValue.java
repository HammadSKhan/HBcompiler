public class ActionReturnValue {
    public final ParsingAction parsingAction;
    public final int state;

    public ActionReturnValue(ParsingAction parsingAction, int state) {
        this.parsingAction = parsingAction;
        this.state = state;
    }
}

enum ParsingAction {
    shift, reduce,accept
}
public class Helpers {
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static boolean isStringLiteral(String s) {
        if(s.startsWith("\"") && s.endsWith("\"")) {
            return true;
        } else {
            return false;
        }
    }
}

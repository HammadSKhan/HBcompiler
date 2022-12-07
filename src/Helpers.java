public class Helpers {
    public static final char EOF = (char) -1;

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

    //For Characters
    public static boolean isInteger(char c) {
        try {
            Integer.parseInt(c + "");
        } catch (Exception e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static boolean isAlpha(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }
        return false;
    }
}

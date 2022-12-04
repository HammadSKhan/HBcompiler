package Exceptions.Lexical;

public class TokenUnrecognizedException extends RuntimeException {
    /**
     * @param message
     */
    public TokenUnrecognizedException(String token, int lineNumber) {
        super("Token not recognized '" + token + "'");
    }
}

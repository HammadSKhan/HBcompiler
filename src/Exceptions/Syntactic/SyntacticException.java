package Exceptions.Syntactic;

public class SyntacticException extends RuntimeException {
	public SyntacticException(String token) {
		super("SYNTAX ERROR: Invalid Syntax near token \"" + token + "\"");
	}
}

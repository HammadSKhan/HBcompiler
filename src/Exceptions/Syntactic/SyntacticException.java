package Exceptions.Syntactic;

public class SyntacticException extends RuntimeException {
	public SyntacticException(String token) {
		super("Invalid Syntax near token \"" + token + "\"");
	}
}

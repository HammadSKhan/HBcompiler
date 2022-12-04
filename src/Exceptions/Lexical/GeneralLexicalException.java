package Exceptions.Lexical;

public class GeneralLexicalException extends RuntimeException {

	/**
	 * @param message
	 */
	public GeneralLexicalException(String input, int indexOfToken) {
		super("There was an error near '" + input.substring(indexOfToken - 1, indexOfToken + 1) + "'");
	}

}

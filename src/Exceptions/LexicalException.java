package Exceptions;

public class LexicalException extends Exception {

	/**
	 * @param message
	 */
	public LexicalException(String input, int indexOfToken) {
		super("There was an error near '" + input.substring(indexOfToken - 1, indexOfToken + 1) + "'");
	}

}

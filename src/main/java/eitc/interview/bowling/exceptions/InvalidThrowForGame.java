package eitc.interview.bowling.exceptions;

/**
 * This is the exception thrown if attempting to add in a throw on the game after all the frames are done.  
 * 
 */
public class InvalidThrowForGame extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8435898548075439825L;

	/**
	 * Constructor that takes in a message
	 * @param message - message for the exception
	 */
	public InvalidThrowForGame(String message) {
		super(message);
	}

	/**
	 * Constructor to support message and throwable.
	 * @param message - message for the exception
	 * @param t - throwable
	 */
	public InvalidThrowForGame(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * Constructor to support throwable only.
	 * @param t - throwable
	 */
	public InvalidThrowForGame(Throwable t) {
		super(t);
	}

}

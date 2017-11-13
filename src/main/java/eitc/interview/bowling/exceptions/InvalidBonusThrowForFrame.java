package eitc.interview.bowling.exceptions;

/**
 * This is the exception thrown if attempting to add in a bonus throw that 
 * does not belong on a frame.
 *
 */
public class InvalidBonusThrowForFrame extends Exception {

	private static final long serialVersionUID = 1962802058554971353L;

	/**
	 * Constructor that takes in a message
	 * @param message - message for the exception
	 */
	public InvalidBonusThrowForFrame(String message) {
		super(message);
	}

	/**
	 * Constructor to support message and throwable.
	 * @param message - message for the exception
	 * @param t - throwable
	 */
	public InvalidBonusThrowForFrame(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * Constructor to support throwable only.
	 * @param t - throwable
	 */
	public InvalidBonusThrowForFrame(Throwable t) {
		super(t);
	}

}

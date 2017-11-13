package eitc.interview.bowling.exceptions;

/**
 * This is the exception thrown if attempting to add in a hrow that does not belong on a frame.  If
 * attempting to add in too many balls for a regular frame, add in the second ball after having gotten a strike 
 * on the first ball.  If you get a strike the additional balls come in under the bonus throws.
 * 
 */
public class InvalidThrowForFrame extends Exception {

	private static final long serialVersionUID = -4422409090620299882L;

	/**
	 * Constructor that takes in a message
	 * @param message - message for the exception
	 */
	public InvalidThrowForFrame(String message) {
		super(message);
	}

	/**
	 * Constructor to support message and throwable.
	 * @param message - message for the exception
	 * @param t - throwable
	 */
	public InvalidThrowForFrame(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * Constructor to support throwable only.
	 * @param t - throwable
	 */
	public InvalidThrowForFrame(Throwable t) {
		super(t);
	}

}

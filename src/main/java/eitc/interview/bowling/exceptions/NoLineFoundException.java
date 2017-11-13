package eitc.interview.bowling.exceptions;

 /**
  * This is the exception thrown if no line was found in the input file.  
  * 
  */
public class NoLineFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that takes in a message
	 * @param message - message for the exception
	 */
	public NoLineFoundException(String message) {
		super(message);
	}

	/**
	 * Constructor to support message and throwable.
	 * @param message - message for the exception
	 * @param t - throwable
	 */
	public NoLineFoundException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * Constructor to support throwable only.
	 * @param t - throwable
	 */
	public NoLineFoundException(Throwable t) {
		super(t);
	}

	

}

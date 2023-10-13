package main.exceptions;

/** A class representing an exception thrown when the list of competitors size is not a power of two */ 

public class NotAPowerOfTwoException extends RuntimeException {
	
	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	/** An exception is defined by its message thrown
	 * @param msg the message thrown
	 */
	public NotAPowerOfTwoException(String msg) {
		super(msg);
	}
	
}



package depaul.stockexchange.message;

public class InvalidMessageDataException extends Exception {

	/**
	 * If the message contains invalid data, throw this exception.
	 */
	private static final long serialVersionUID = 4L;

	public InvalidMessageDataException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidMessageDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidMessageDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidMessageDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidMessageDataException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

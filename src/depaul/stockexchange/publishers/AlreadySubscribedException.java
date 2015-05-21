package depaul.stockexchange.publishers;

public class AlreadySubscribedException extends Exception {

	/**
	 * If the user is already subscribed for the stock, throw this exception 
	 */
	private static final long serialVersionUID = 5L;

	public AlreadySubscribedException() {
		// TODO Auto-generated constructor stub
	}

	public AlreadySubscribedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AlreadySubscribedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public AlreadySubscribedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AlreadySubscribedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

package depaul.stockexchange.publishers;

public class NotSubscribedException extends Exception {

	/**
	 * If the user is not subscribed for the stock, throw this exception
	 */
	private static final long serialVersionUID = 6L;

	public NotSubscribedException() {
		// TODO Auto-generated constructor stub
	}

	public NotSubscribedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotSubscribedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NotSubscribedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotSubscribedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

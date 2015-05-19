package depaul.stockexchange.tradable;

public final class InvalidTradableValue extends Exception {

	/**
	 * If the tradable value is negative or zero, throw this exception.
	 */
	private static final long serialVersionUID = 3L;

	public InvalidTradableValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidTradableValue(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public InvalidTradableValue(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InvalidTradableValue(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InvalidTradableValue(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}

package depaul.stockexchange.price;

public final class InvalidPriceOperation extends Exception {

	/**
	 * If either Price is a Market Price, or the Price passed in is null,
	 * throw this exception.
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPriceOperation() {
		super();
	}

	public InvalidPriceOperation(String message) {
		super(message);
	}
}

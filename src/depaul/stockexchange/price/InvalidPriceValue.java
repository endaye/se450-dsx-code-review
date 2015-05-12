package depaul.stockexchange.price;

public final class InvalidPriceValue extends Exception {

	/**
	 * If the value of Price is negative, throw this exception.
	 */
	
	private static final long serialVersionUID = 2L;

	public InvalidPriceValue() {
		super();
	}

	public InvalidPriceValue(String message) {
		super(message);
	}

}

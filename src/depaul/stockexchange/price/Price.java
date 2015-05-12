package depaul.stockexchange.price;

final class Price {
	private long value;
	private boolean isMarket; 
	
	/**
	 * Creates a Price object representing the provided value. 
	 * Remember, a long value passed in of 1499 represents $14.99, 12850 represents $128.50, etc. 
	 * @param value
	 * price value
	 */
	Price(long value) {
		this.value = value;
	}
	
	/**
	 *  Creates a Price object representing Market price.  
	 */
	Price() {
		
	}
	
	public Price add(Price p) throws InvalidPriceOperation {
		if (p == null) 
			throw new InvalidPriceOperation("The price value is NULL.");
		return new Price(this.value + p.value);
	}
	
	public Price substract(Price p) throws InvalidPriceOperation {
		if (p == null) 
			throw new InvalidPriceOperation("The price value is NULL.");
		return new Price(this.value - p.value);
	}
	
	public Price multiply(int p) throws InvalidPriceOperation {
		if (p <= 0)
			throw new InvalidPriceOperation("The price value is invalid.");
		return new Price(this.value * p);
	}
	
	public int compareTo(Price p) {
		
	}
	
	public boolean greaterOrEqual(Price p) {
		
	}
	
	public boolean greaterThan(Price p) {
		
	}
	
	public lessOrEqual(Price p) {
		
	}
	
	public lessThan(Price p) {
		
	}
	
	public boolean isMarket() {
		
	}
	
	public boolean isNegative() {
		
	}
	
	public String toString() {
		
	}
}

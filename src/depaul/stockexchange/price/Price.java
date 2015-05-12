package depaul.stockexchange.price;

final class Price {
	private final long value;
	private final boolean isMarket; 
	
	Price(long value) {
		this.value = value;
		this.isMarket = false;
	}

	Price() {
		this.value = -1;
		this.isMarket = true;
	}
	
	public Price add(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		return new Price(this.value + p.value);
	}
	
	public Price substract(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		return new Price(this.value - p.value);
	}
	
	public Price multiply(int p) throws InvalidPriceOperation {
		if (p <= 0) {
			throw new InvalidPriceOperation("The passed-in number is invalid.");
		}
		return new Price(this.value * p);
	}
	
	public int compareTo(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if ()
			
		
	}
	
	public boolean greaterOrEqual(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.value >= p.value) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean greaterThan(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.value > p.value) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean lessOrEqual(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.value <= p.value) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean lessThan(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.value < p.value) {
			return true;
		} else {
			return false;
		}
	}
	public boolean equals(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if 
	}
	public boolean isMarket() {
		if
	}
	
	public boolean isNegative() {
		
	}
	
	public String toString() {
		
	}
}

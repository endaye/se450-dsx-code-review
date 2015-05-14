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
	
	private long getPriceValue() {
		return this.value;
	}
	
	public Price add(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		return PriceFactory.makeLimitPrice(this.getPriceValue() + p.getPriceValue());
	}
	
	public Price substract(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		return PriceFactory.makeLimitPrice(this.getPriceValue() - p.getPriceValue());
	}
	
	public Price multiply(int p) throws InvalidPriceOperation {
		if (p <= 0) {
			throw new InvalidPriceOperation("The passed-in number is invalid.");
		}
		long newValue;
		try {
			newValue = Math.multiplyExact(this.getPriceValue(), p);
		} catch(ArithmeticException ae) {
			throw new InvalidPriceOperation("The result is overflow. "
					+ ae.getMessage());
		}
		return PriceFactory.makeLimitPrice(newValue);
	}
	
	
	public int compareTo(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.equals(p)) {
			return 0;
		} else if (this.greaterThan(p) ) {
			return 1;
		} else {
			return -1;
		}
			
		
	}
	
	public boolean greaterOrEqual(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.getPriceValue() >= p.getPriceValue()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean greaterThan(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.getPriceValue() > p.getPriceValue()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean lessOrEqual(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.getPriceValue() <= p.getPriceValue()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean lessThan(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.getPriceValue() < p.getPriceValue()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean equals(Price p) throws InvalidPriceOperation {
		if (p == null) {
			throw new InvalidPriceOperation("The price value is NULL.");
		}
		if (this.getPriceValue() == p.getPriceValue()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isMarket() {
		return this.isMarket;
	}
	
	public boolean isNegative() throws InvalidPriceValue {
		if (this.isMarket()) {
			throw new InvalidPriceValue("This is market price value.");
		}
		if (this.value < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		if (this.isMarket()) {
			return "MKT";
		} else {
			return String.format("$%,.2f", (double) this.getPriceValue() / 100.00);
		}
	}
}

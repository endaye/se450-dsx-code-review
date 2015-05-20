package depaul.stockexchange.tradable;

import depaul.stockexchange.BookSide;
import depaul.stockexchange.price.Price;

public class Quote {
	private String user;
	private String product;
	private QuoteSide buyQs;
	private QuoteSide sellQs;

	
	public Quote(String userName, String productSymbol, Price buyPrice, 
			int buyVolume, Price sellPrice, int sellVolume) throws InvalidTradableValue {
		this.user = userName;
		this.product = productSymbol;
		this.buyQs = new QuoteSide(userName, productSymbol, buyPrice, buyVolume, BookSide.BUY);
		this.sellQs = new QuoteSide(userName, productSymbol, sellPrice, sellVolume, BookSide.SELL);
	}
	
	public String getUserName() {
		return this.user;
	}
	
	public String getProduct() {
		return this.product;
	}
	
	public QuoteSide getQuoteSide(BookSide sideIn) throws InvalidTradableValue {
		if (sideIn == BookSide.BUY) {
			return new QuoteSide(this.buyQs);
		} else {
			return new QuoteSide(this.sellQs);
		}
	}
	
	public QuoteSide getQuoteSide(String sideIn) throws InvalidTradableValue {
		if (sideIn == null || "".equals(sideIn)) {
			throw new InvalidTradableValue("The side is invalid.");
		}
		if (BookSide.valueOf(sideIn) == BookSide.BUY) {
			return new QuoteSide(this.buyQs);
		} else if (BookSide.valueOf(sideIn) == BookSide.SELL) {
			return new QuoteSide(this.sellQs);
		} else {
			throw new InvalidTradableValue("The side is invalid.");
		}
	}
	
	public String toString() {
		return String.format("%s quote: %s - %s ", 
				this.getUserName(), this.buyQs, this.sellQs);
	}
}

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
	
	public QuoteSide getQuoteSide(String sideIn) {
		if (BookSide.valueOf(sideIn) == BookSide.BUY) {
			try {
				return new QuoteSide(this.buyQs);
			} catch (InvalidTradableValue e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		if (BookSide.valueOf(sideIn) == BookSide.SELL) {
			try {
				return new QuoteSide(this.sellQs);
			} catch (InvalidTradableValue e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String toString() {
		return String.format("%s quote: %s - %s ", 
				this.getUserName(), this.buyQs, this.sellQs);
	}
}

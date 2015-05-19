package depaul.stockexchange.tradable;

import depaul.stockexchange.BookSide;
import depaul.stockexchange.price.*;

public class Order extends TradableImplement implements Tradable {
	
	public Order(String userName, String productSymbol, 
			Price orderPrice, int originalVolume, BookSide side) 
					throws InvalidTradableValue {
		this.setUser(userName);
		this.setProduct(productSymbol);
		this.setPrice(orderPrice);
		this.setOriginalVolume(originalVolume);
		this.setRemainVolume(originalVolume);
		this.setSide(side);
		this.buildId();
	}

	@Override
	public boolean isQuote() {
		return false;
	}

	@Override
	protected void buildId() throws InvalidTradableValue {
		this.id = String.format("%s%s%s%s", this.getUser(), this.getProduct(), 
				this.getPrice(), System.nanoTime());
	}
	
}

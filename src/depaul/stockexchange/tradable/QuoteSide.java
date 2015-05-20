package depaul.stockexchange.tradable;

import depaul.stockexchange.BookSide;
import depaul.stockexchange.price.Price;

public class QuoteSide extends TradableImplement implements Tradable {
	
	public QuoteSide (String userName, String productSymbol, 
			Price sidePrice, int originalVolume, BookSide side) throws InvalidTradableValue {
		this.setUser(userName);
		this.setProduct(productSymbol);
		this.setPrice(sidePrice);
		this.setOriginalVolume(originalVolume);
		this.setSide(side);
		this.buildId();
	}
	
	public QuoteSide (QuoteSide qs) throws InvalidTradableValue {
		if (qs == null) {
			throw new InvalidTradableValue("The QuoteSide object is invalid.");
		}
		this.setUser(qs.getUser());
		this.setProduct(qs.getProduct());
		this.setPrice(qs.getPrice());
		this.setOriginalVolume(qs.getOriginalVolume());
		this.setRemainingVolume(qs.getRemainingVolume());
		this.setCancelledVolume(qs.getCancelledVolume());
		this.setSide(BookSide.valueOf(qs.getSide().trim().toUpperCase()));
		this.buildId();
	}
	
	@Override
	public boolean isQuote() {
		return true;
	}

	@Override
	protected final void buildId() {
		this.id = String.format("%s%s%s", this.getUser(), this.getProduct(), System.nanoTime());
	}

	public final String toString() {
		return String.format("%s * %s (Original Vol: %s, CXL'd Vol: %s) [%s]",
				this.getPrice(), this.getOriginalVolume(), this.getCancelledVolume(), this.getId());
	}
}

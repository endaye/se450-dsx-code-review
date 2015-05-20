package depaul.stockexchange.tradable;

import depaul.stockexchange.BookSide;
import depaul.stockexchange.price.Price;

public class TradableDTO {
	public String user; 
	public BookSide side; 
	public String product;
	public Price price;
	public int originalVolume; 
	public int remainingVolume; 
	public int cancelledVolume; 
	public boolean isQuote; 
	public String id;

	public TradableDTO(String product, Price price, 
			int originalVolume, int remainVolume, int cancelledVolume, 
			String userName, String side, boolean isQuote, String id) {
		if(side != null && "".equals(side)) {
			try {
				this.side = BookSide.valueOf(side.trim().toUpperCase());
			} catch(IllegalArgumentException ex) {}
		}
		this.user = userName;
		this.product = product;
		this.price = price;
		this.originalVolume = originalVolume;
		this.remainingVolume = remainVolume;
		this.cancelledVolume = cancelledVolume;
		this.isQuote = isQuote;
		this.id = id;

	}

	public TradableDTO(Tradable t) {
		this.user = t.getUser();
		this.side = BookSide.valueOf(t.getSide().trim().toUpperCase());
		this.product = t.getProduct();
		this.price = t.getPrice();
		this.originalVolume = t.getOriginalVolume();
		this.remainingVolume = t.getRemainingVolume();
		this.cancelledVolume = t.getCancelledVolume();
		this.isQuote = t.isQuote();
		this.id = t.getId();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Product: %s, ", this.product));
		sb.append(String.format("Price: %s, ", this.price.toString()));
		sb.append(String.format("OriginalVolume: %s, ", this.originalVolume));
		sb.append(String.format("RemainingVolume: %s, ", this.remainingVolume));
		sb.append(String.format("CancelledVolume: %s, ", this.cancelledVolume));
		sb.append(String.format("User: %s, ", this.user));
		sb.append(String.format("Side: %s, ", this.side.toString()));
		sb.append(String.format("IsQuote: %b, ",this.isQuote));
		sb.append(String.format("Id: %s, ", this.id));
		return sb.toString();
	}
}

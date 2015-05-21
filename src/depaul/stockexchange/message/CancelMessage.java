package depaul.stockexchange.message;

import depaul.stockexchange.BookSide;
import depaul.stockexchange.price.Price;

public class CancelMessage 
extends MessageBase 
implements Comparable<CancelMessage> {

	public CancelMessage(String user, String product, Price price, int volume, 
            String details, BookSide side, String id) 
                throws InvalidMessageDataException {
        super(user, product, price, volume, details, side, id);
    }

	@Override
	public int compareTo(CancelMessage message) {
		if (message == null) {
			return -1;
		}
		return this.getPrice().compareTo(message.getPrice());
	}
	
	@Override
	public String toString() {
		return String.format("User: %s, Product: %s, Price: %s, " 
				+ "Volume: %s, Details: %s, Side: %s, Id: %s",
				this.getUser(), this.getProduct(), this.getPrice(),
				this.getVolume(), this.getDetails(), 
				this.getSide(), this.getId());
	}
}

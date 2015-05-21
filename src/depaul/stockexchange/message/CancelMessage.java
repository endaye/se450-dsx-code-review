package depaul.stockexchange.message;

import depaul.stockexchange.BookSide;
import depaul.stockexchange.price.Price;

public class CancelMessage extends MessageBase implements Comparable<CancelMessage> {

	public CancelMessage(String user, String product, Price price, int volume, 
			String details, BookSide side, String id) 
					throws InvalidMessageDataException {
		super(user, product, price, volume, details, side, id);
	}

	@Override
	public int compareTo(CancelMessage cm) {
		if (cm == null) {
			return -1;
		}
		return this.getPrice().compareTo(cm.getPrice());
	}
}

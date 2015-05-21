package depaul.stockexchange.message;

import depaul.stockexchange.BookSide;
import depaul.stockexchange.price.Price;

public class FillMessage extends MessageBase implements Comparable<FillMessage> {

	public FillMessage(String user, String product, Price price, int volume, 
			String details, BookSide side, String id) 
					throws InvalidMessageDataException {
		super(user, product, price, volume, details, side, id);
	}
	
	 public void setDetails(String details) {
	        super.setDetails(details);
	    }
	 
	 public int getVolume() {
		 return super.getVolume();
	 }
	 
	 public void setVolume(int volume) throws InvalidMessageDataException {
		 super.setVolume(volume);
	 }

	@Override
	public int compareTo(FillMessage fm) {
		if (fm == null) {
			return -1;
		}
		return this.getPrice().compareTo(fm.getPrice());
	}
}

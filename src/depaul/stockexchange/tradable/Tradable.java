package depaul.stockexchange.tradable;

import depaul.stockexchange.price.*;

public interface Tradable {
	String getProduct();
	Price getPrice();
	int getOriginalVolume();
	int getRemainVolume();
	int getCancelledVolume();
	String getUser();
	String getSide();
	boolean isQuote();
	String getId();
	
	void setCancelledVolume(int newCancelledVolume) throws InvalidTradableValue;
	void setRemainVolume(int newRemainVolume) throws InvalidTradableValue;
}

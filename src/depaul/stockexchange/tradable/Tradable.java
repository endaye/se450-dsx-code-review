package depaul.stockexchange.tradable;

import depaul.stockexchange.price.*;

public interface Tradable {
	String getProduct();
	Price getPrice();
	int getOriginalVolume();
	int getRemainingVolume();
	int getCancelledVolume();
	String getUser();
	String getSide();
	boolean isQuote();
	String getId();
	
	void setCancelledVolume(int newCancelledVolume) throws InvalidTradableValue;
	void setRemainingVolume(int newRemainVolume) throws InvalidTradableValue;
}

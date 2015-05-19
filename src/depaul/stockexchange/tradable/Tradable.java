package depaul.stockexchange.tradable;

import depaul.stockexchange.price.*;

public interface Tradable {
	String getProduct();
	Price getPrice();
	int getOriginalVolume();
	int getRemainVolume();
	int getCancelledVolume();
	void setCancelledVolume(int newCancelledVolume) throws InvalidTradableValue;
	void setRemainVolume(int mewRemainvolume) throws InvalidTradableValue;
	String getUser();
	String getSide();
	boolean isQuote();
	String getId();
}

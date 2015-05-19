package depaul.stockexchange.tradable;

import depaul.stockexchange.price.*;

public interface Tradable {
	String getProduct();
	Price getPrice();
	int getOriginalVolume();
	int getRemainVolume();
	int getCancelledVolume();
	void setCancelledVolume(int newCancelledVolume);
	void setRemainVolume(int mewRemainvolume);
	String getUser();
	String getSide();
	boolean isQuote();
	String getId();
}

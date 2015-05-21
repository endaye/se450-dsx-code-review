package depaul.stockexchange.publishers;

import depaul.stockexchange.price.*;

public class MarketDataDTO {

	public String product;
	public Price buyPrice;
	public int buyVolume;
	public Price sellPrice;
	public int sellVolume;

	public MarketDataDTO(String product, Price buyPrice, int buyVolume, 
			Price sellPrice, int sellVolume) {
		this.product = product;
		this.buyPrice = buyPrice;
		this.buyVolume = buyVolume;
		this.sellPrice = sellPrice;
		this.sellVolume = sellVolume;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Product: %s,", product));
		sb.append(String.format(", buyVolume: %s, buyPrice: %s", buyVolume, buyPrice));
		sb.append(String.format(", sellVolume: %s, sellPrice: %s", sellVolume, sellPrice));
		return sb.toString();	
	}

}

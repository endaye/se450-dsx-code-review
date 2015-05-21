package depaul.stockexchange.tradable;

import depaul.stockexchange.*;
import depaul.stockexchange.price.*;

public abstract class TradableImplement implements Tradable {
	protected String user;
	protected String product;
	protected String id;
	protected BookSide side;
	protected Price price;
	protected int originalVolume = 0;
	protected int remainVolume = 0;
	protected int cancelledVolume = 0;
	
	@Override
	public String getProduct() {
		return this.product;
	}

	@Override
	public Price getPrice() {
		return this.price;
	}

	@Override
	public int getOriginalVolume() {
		return this.originalVolume;
	}

	@Override
	public int getRemainingVolume() {
		return this.remainVolume;
	}

	@Override
	public int getCancelledVolume() {
		// TODO Auto-generated method stub
		return this.cancelledVolume;
	}

	@Override
	public String getUser() {
		return this.user;
	}

	@Override
	public BookSide getSide() {
		return this.side;
	}

	@Override
	public abstract boolean isQuote();

	@Override
	public String getId() {
		return this.id;
	}
	
	protected void setUser(String userName) throws InvalidTradableValue {
		if (userName == null || "".equals(userName)) {
			throw new InvalidTradableValue("The user name" + userName + " is invalid.");
		}
		this.user = userName;
	}
	
	protected void setProduct(String product) throws InvalidTradableValue {
		if (product == null || "".equals(product)) {
			throw new InvalidTradableValue("The product is " + product +" is invalid.");
		}
		this.product = product;
		
	}
	
	protected abstract void buildId();
	
	protected void setSide(BookSide side) {
		this.side = side;
	}
	
	protected void setSide(String side) throws InvalidTradableValue {
		if (side == null || "".equals(side)) {
			throw new InvalidTradableValue("The side is empty.");
		}
		try {
			this.side = BookSide.valueOf(side.trim().toUpperCase());
		} catch(IllegalArgumentException ex) {
			throw new InvalidTradableValue("The side is invalid");
		}
	}
	
	protected void setPrice(Price price) throws InvalidTradableValue {
		if (price == null) {
			throw new InvalidTradableValue("The price is invalid");
		}
		this.price = price;
	}
	
	protected void setOriginalVolume(int originalVolume) throws InvalidTradableValue {
		if (originalVolume <= 0) {
			throw new InvalidTradableValue (
					"The original volume " + originalVolume + " is invalid.");
		} 
		this.originalVolume = originalVolume;
	}
	
	@Override
	public void setCancelledVolume(int newCancelledVolume) throws InvalidTradableValue {
		if (newCancelledVolume < 0) {
			throw new InvalidTradableValue (
					"The cancelled volume " + newCancelledVolume + " is invalid.");
		}
		if (newCancelledVolume > this.originalVolume - this.remainVolume) {
			String errMsg = String.format("Requested new cancelled volume " 
					+ "(%s) plus the remaining volume (%s) exceeds the " 
					+ "tradable's Original Volume (%s)"
                    , newCancelledVolume, this.remainVolume, this.originalVolume);
            throw new InvalidTradableValue(errMsg);
		} 
		this.cancelledVolume = newCancelledVolume;
	}

	@Override
	public void setRemainingVolume(int newRemainVolume) throws InvalidTradableValue {
		if (newRemainVolume < 0) {
			throw new InvalidTradableValue (
					"The remain volume " + newRemainVolume + " is invalid.");
		}
		if (newRemainVolume + this.cancelledVolume > this.originalVolume) {
            String errMsg = String.format("Requested new Remaining Volume "
                    + "(%s) plus the Cancelled Volume (%s) exceeds the "
                    + "tradable's Original Volume (%s)"
                    , newRemainVolume, this.cancelledVolume, this.originalVolume);
            throw new InvalidTradableValue(errMsg);
        }
        this.remainVolume = newRemainVolume;
	}
	
}

package depaul.stockexchange.price;
import java.util.HashMap;

public class PriceFactory {
	
	private static final HashMap<Long,Price> prices = new HashMap<Long, Price>();
	private static final Price marketPrice = new Price();
	
	public static Price makeLimitPrice(String value) throws InvalidPriceValue {
		double d = -1;
		try {
			d = Double.parseDouble(value.replaceAll("[$,]", ""));
		} catch(NumberFormatException ne) {
			throw new InvalidPriceValue("The value is invalid.");
		}
		return PriceFactory.makeLimitPrice((long) (d * 100.0));
	}
	
	public static Price makeLimitPrice(long value) {
		Price p = prices.get(value);
		if (p == null) {
			p = new Price(value);
			prices.put(value, p);
		}
		return p;
	}
	
	public static Price makeMarketPrice() {
		return marketPrice;
	}
}

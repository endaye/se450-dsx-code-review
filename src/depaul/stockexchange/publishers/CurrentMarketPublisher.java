package depaul.stockexchange.publishers;

import java.util.HashMap;
import java.util.HashSet;

import depaul.stockexchange.DataValidationException;
import depaul.stockexchange.client.User;
import depaul.stockexchange.price.PriceFactory;

public class CurrentMarketPublisher implements PublisherInterface {

	private HashMap<String, HashSet<User>> subscriptions = new HashMap<>();
	
	private volatile static CurrentMarketPublisher instance;

	private CurrentMarketPublisher() {};

	public static CurrentMarketPublisher getInstance() {
		if (instance == null) {
			synchronized(CurrentMarketPublisher.class) {
				if (instance == null) {
					instance = new CurrentMarketPublisher();
				}
			}
		}
		return instance;
	}

	/**
	 * Users subscribe for data. 
	 */
	public synchronized void subscrible(User u, String product) 
			throws AlreadySubscribedException, DataValidationException{

		// user can't be null
		if (u == null) {
			throw new DataValidationException("User can't be null.");
		}

		// product can't be null or empty
		if (product == null || product.isEmpty()) {
			throw new DataValidationException("Product can't be null or empty.");
		}

		HashSet<User> subscribers = subscriptions.get(product);

		// check if user has already subscribed
		if (subscribers != null && subscribers.contains(u)) {
			throw new AlreadySubscribedException("The user has already "
					+ "subscribed for this stock.");
		}

		// if there is no HashSet with this product, create a new one
		if (subscribers == null) {
			subscribers = new HashSet<User>();
			subscriptions.put(product, subscribers);
		}

		// Add the user to the subscriber list
		subscribers.add(u);
	}

	/**
	 * Users un-subscribe for data. 
	 */
	public synchronized void unSubscribe(User u, String product) 
			throws NotSubscribedException, DataValidationException {
		// user can't be null
		if (u == null) {
			throw new DataValidationException("User can't be null.");
		}

		// product can't be null or empty
		if (product == null || product.isEmpty()) {
			throw new DataValidationException("Product can't be null or empty.");
		}

		HashSet<User> subscribers = subscriptions.get(product);

		// check if user has not subscribed
		if (subscribers == null || !subscribers.contains(u)) {
			throw new NotSubscribedException("The user is not "
					+ "subscribed for the stock.");
		}

		// Remove the user from the subscriber list
		subscribers.remove(u);
	}

	public HashSet<User> getSubscribers(String product) throws DataValidationException {
		// product can't be null or empty
		if (product == null || product.isEmpty()) {
			throw new DataValidationException ("Product can't be null or empty.");
		}
		return subscriptions.get(product);
	}

	public synchronized void publishCurrentMarket(MarketDataDTO md) 
			throws DataValidationException {
		
		if (md == null) {
			throw new DataValidationException("The market data passed in "
					+ "can't be null.");
		}

		// Check if the product is null or empty
		if (md.product == null || md.product.isEmpty()) {
			throw new DataValidationException("The String stock symbol "
					+ "taken from the MarketDataDTO passed in "
					+ "can't be null or empty.");
		}

		// Check if the BUY side volume is negative
		if (md.buyVolume < 0) {
			throw new DataValidationException("The BUY side volume taken "
					+ "from the MarketDataDTO passed in can't be negative.");
		}

		// Check if the SELL side volume is negative
		if (md.sellVolume < 0) {
			throw new DataValidationException("The SELL side volume taken "
					+ "from the MarketDataDTO passed in can't be negative.");
		}

		// If the BUY side price is null, 
		// a Price object representing $0.00 should be used
		if (md.buyPrice == null) {
			md.buyPrice = PriceFactory.makeLimitPrice(0);
		}

		// If the SELL side price is null, 
		// a Price object representing $0.00 should be used
		if (md.sellPrice == null) {
			md.sellPrice = PriceFactory.makeLimitPrice(0);
		}

		// get all subscribers
		HashSet<User> subscribers = getSubscribers(md.product);

		// if there is no subscribers, no needs to notify anybody
		if (subscribers == null || subscribers.isEmpty()) {
			return;
		}

		// For each subscriber
		for (User u : subscribers) {
			// call the user object's "acceptCurrentMarket"
			u.acceptCurrentMarket(md.product, md.buyPrice, md.buyVolume, 
					md.sellPrice, md.sellVolume);
		}
	}


}

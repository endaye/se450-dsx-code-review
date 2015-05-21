package depaul.stockexchange.publishers;

import java.util.HashMap;
import java.util.HashSet;

import depaul.stockexchange.DataValidationException;
import depaul.stockexchange.client.User;
import depaul.stockexchange.price.*;

public class TickerPublisher implements PublisherInterface {

	private volatile static TickerPublisher instance;

	/**
	 * The most recent ticker value for each stock symbol 
	 * so it can determine if the stock price have 
	 * moved up, down, or stayed the same. 
	 */
	private final HashMap<String, Price> mostRecentTickers = new HashMap<String, Price>();
	
	private HashMap<String, HashSet<User>> subscriptions = new HashMap<>();

	private TickerPublisher() {}

	/**
	 * Get the singleton instance
	 * @return 
	 *      The instance of TickerPublisher class
	 */
	public static TickerPublisher getInstance() {
		if (instance == null) {
			synchronized(TickerPublisher.class) {
				if (instance == null) {
					instance = new TickerPublisher();
				}
			}
		}
		return instance;
	}

	/**
	 * Users subscribe for data. 
	 */
	public synchronized void subscribe(User u, String product) 
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

	private HashSet<User> getSubscribers(String product) throws DataValidationException {
		// product can't be null or empty
		if (product == null || product.isEmpty()) {
			throw new DataValidationException ("Product can't be null or empty.");
		}
		return subscriptions.get(product);
	}

	/**
	 * Publish a ticker
	 * @param product
	 *      A String stock symbol
	 * @param p
	 *      The last sale price
	 * @throws DataValidationException 
	 *      If the product is empty or null
	 * @throws InvalidPriceOperation 
	 */
	public synchronized void publishTicker(String product, Price p) 
			throws DataValidationException, InvalidPriceOperation {

		// Check if the product is null or empty
		if (product == null || product.isEmpty()) {
			throw new DataValidationException("The String stock symbol "
					+ "passed in can't be null or empty.");
		}

		// If the last sale price is null, 
		// a Price object representing $0.00 should be used
		if (p == null) {
			p = PriceFactory.makeLimitPrice(0);
		}

		// Get previous ticker value for this product
		Price previousPrice = mostRecentTickers.get(product);
		// If can't get the previous ticker value, assume it's $0.00
		if (previousPrice == null) {
			previousPrice = PriceFactory.makeLimitPrice(0);
		}

		char direction;
		if (p.greaterThan(previousPrice)) {
			direction = (char) 8593; //'↑' 
		} else if (p.lessThan(previousPrice)) {
			direction = (char) 8595; //' ↓'
		} else {
			direction = '=';
		}

		// set the current price as last sale price
		mostRecentTickers.put(product, p);


		// get all subscribers
		HashSet<User> subscribers = getSubscribers(product);

		// if there is no subscribers, no needs to notify anybody
		if (subscribers == null || subscribers.isEmpty()) {
			return;
		}

		// For each subscriber
		for (User u : subscribers) {
			// call the user object's "acceptLastSale"
			u.acceptTicker(product, p, direction);
		}
	}

}

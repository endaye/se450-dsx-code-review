package depaul.stockexchange.publishers;

import java.util.HashMap;
import java.util.HashSet;

import depaul.stockexchange.DataValidationException;
import depaul.stockexchange.client.User;
import depaul.stockexchange.price.Price;
import depaul.stockexchange.price.PriceFactory;

public class LastSalePublisher implements PublisherInterface{

	private HashMap<String, HashSet<User>> subscriptions = new HashMap<>();
	private volatile static LastSalePublisher instance;

	private LastSalePublisher() {}

	/**
	 * Get the singleton instance
	 * @return 
	 *      The instance of LastSalePublisher class
	 */
	public static LastSalePublisher getInstance() {
		if (instance == null) {
			synchronized(LastSalePublisher.class) {
				if (instance == null) {
					instance = new LastSalePublisher();
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

	/**
	 * 
	 * @param product
	 *      A String stock symbol
	 * @param p
	 *      A Price holding the last sale price
	 * @param v
	 *      An “int” holding the last sale volume a parameter to the method
	 * @throws DataValidationException 
	 *      If the product is null or empty
	 *      If the volume is negative
	 */
	public synchronized void publishLastSale(String product, Price p, int v) 
			throws DataValidationException {

		// Check if the product is null or empty
		if (product == null || product.isEmpty()) {
			throw new DataValidationException("The String stock symbol "
					+ "passed in can't be null or empty.");
		}

		// Check if the BUY side volume is negative
		if (v < 0) {
			throw new DataValidationException("The last sale volume "
					+ "passed in can't be negative.");
		}

		// If the last sale price is null, 
		// a Price object representing $0.00 should be used
		if (p == null) {
			p = PriceFactory.makeLimitPrice(0);
		}

		// get all subscribers
		HashSet<User> subscribers = getSubscribers(product);

		// if there is no subscribers, no needs to notify anybody
		if (subscribers == null || subscribers.isEmpty()) {
			return;
		}

		// For each subscriber
		for (User u : subscribers) {
			// call the user object's "acceptLastSale"
			u.acceptLastSale(product, p, v);
		}
	}

}

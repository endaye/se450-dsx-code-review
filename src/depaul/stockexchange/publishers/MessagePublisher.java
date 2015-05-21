package depaul.stockexchange.publishers;

import java.util.HashMap;
import java.util.HashSet;

import depaul.stockexchange.DataValidationException;
import depaul.stockexchange.client.User;
import depaul.stockexchange.message.*;

public class MessagePublisher implements PublisherInterface{

	private HashMap<String, HashSet<User>> subscriptions = new HashMap<>();
	private volatile static MessagePublisher instance;

	private MessagePublisher() {}

	/**
	 * Get the singleton instance
	 * @return 
	 *      The instance of MessagePublisher class
	 */
	public static MessagePublisher getInstance() {
		if (instance == null) {
			synchronized(MessagePublisher.class) {
				if (instance == null) {
					instance = new MessagePublisher();
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

	public HashSet<User> getSubscribers(String product) throws DataValidationException {
		// product can't be null or empty
		if (product == null || product.isEmpty()) {
			throw new DataValidationException ("Product can't be null or empty.");
		}
		return subscriptions.get(product);
	}
	/**
	 * Find the User object in the HashSet for the specified stock symbol
	 * whose user name matches the user name passed in
	 * @param product
	 *      A String stock symbol
	 * @param username
	 *      A String username of user
	 * @return
	 *      Return the user object that found.
	 *      Return null if can't find any
	 * @throws DataValidationException 
	 *      If product is null or empty
	 *      If username is null or empty
	 */
	private User findSubscriber(String product, String username) 
			throws DataValidationException {

		// product can't be null or empty
		if (product == null || product.isEmpty()) {
			throw new DataValidationException("Product can't be null or empty.");
		}
		// product can't be null or empty
		if (product == null || product.isEmpty()) {
			throw new DataValidationException("Username can't be null or empty.");
		}

		HashSet<User> subscribers = subscriptions.get(product);

		User user = null;
		// Find the User object in the HashSet for the specified stock symbol  
		// whose user name matches the user name passed in
		if (subscribers != null) {
			for (User u : subscribers) {
				if (username.equals(u.getUserName())) {
					user = u;
					break;
				}
			}
		}
		return user;
	}

	/**
	 * Push a cancel message to the user who subscribe this product
	 * @param cm
	 *      The cancel message
	 * @throws DataValidationException
	 *      If the cancel message is null
	 */
	public synchronized void publishCancel(CancelMessage cm) 
			throws DataValidationException {
		if (cm == null) {
			throw new DataValidationException("CancelMessage can't be null.");
		}

		User user = findSubscriber(cm.getProduct(), cm.getUser());
		if (user != null) {
			user.acceptMessage(cm);
		}
	}


	/**
	 * Push a fill message to the user who subscribe this product
	 * @param fm
	 *      The fill message
	 * @throws DataValidationException
	 *      If the fill message is null
	 * @throws NotSubscribedException 
	 *      If the user has not subscribed the product
	 */
	public synchronized void publishFill(FillMessage fm) 
			throws DataValidationException, NotSubscribedException {
		if (fm == null) {
			throw new DataValidationException("FillMessage can't be null.");
		}

		User user = findSubscriber(fm.getProduct(), fm.getUser());
		if (user != null) {
			user.acceptMessage(fm);
		}
	}

	/**
	 * Push market message to all subscribed users
	 * @param mm
	 *      The market message
	 * @throws DataValidationException 
	 *      If the market message is null
	 */
	public synchronized void publishMarketMessage(MarketMessage mm) 
			throws DataValidationException {
		if (mm == null) {
			throw new DataValidationException("MarketMessage can't be null.");
		}

		// all subscribed Users 
		// regardless of the stock symbol they are interested in 
		for (HashSet<User> subscribers : subscriptions.values()) {
			for (User u : subscribers) {
				u.acceptMarketMessage(mm.toString());
			}
		}
	}

}

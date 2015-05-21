package depaul.stockexchange.publishers;

import depaul.stockexchange.DataValidationException;
import depaul.stockexchange.client.*;

import java.util.HashMap;
import java.util.HashSet;

public abstract class PublisherBase {

	private HashMap<String, HashSet<User>> subscriptions = new HashMap<>();
	
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
	
	protected HashSet<User> getSubscribers(String product) 
           throws DataValidationException {
       // product can't be null or empty
       if (product == null || product.isEmpty()) {
           throw new DataValidationException("Product can't be null or empty.");
       }
       
       return subscriptions.get(product);
   }

}

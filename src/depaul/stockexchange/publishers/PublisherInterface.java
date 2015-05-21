package depaul.stockexchange.publishers;

import java.util.HashSet;

import depaul.stockexchange.DataValidationException;
import depaul.stockexchange.client.User;

interface PublisherInterface {

	public void subscrible(User u, String product) 
			throws AlreadySubscribedException, DataValidationException;
	
	public void unSubscribe(User u, String product) 
          throws NotSubscribedException, DataValidationException;
	
	public HashSet<User> getSubscribers(String product) throws DataValidationException;
}

package depaul.stockexchange.publishers;

import depaul.stockexchange.DataValidationException;
import depaul.stockexchange.client.*;

import java.util.HashMap;
import java.util.HashSet;

public abstract class PublisherBase {

	protected HashMap<String, HashSet<User>> subscriptions = new HashMap<>();
	
	public synchronized void subscrible(User u, String product) 
			throws AlreadySubscribedException, DataValidationException{
		if (u == null) {
			throw new DataValidationException("User can't be null");
		}
		
		if(U)
	}

}

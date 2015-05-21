package depaul.stockexchange.publishers;

import depaul.stockexchange.DataValidationException;
import depaul.stockexchange.client.User;

interface PublisherInterface {

	public void subscribe(User u, String product) 
			throws AlreadySubscribedException, DataValidationException;
	
	public void unSubscribe(User u, String product) 
          throws NotSubscribedException, DataValidationException;
}

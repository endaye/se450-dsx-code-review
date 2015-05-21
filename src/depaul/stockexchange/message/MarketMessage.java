package depaul.stockexchange.message;

public class MarketMessage {

	private MarketState state;
	
	public MarketMessage(MarketState state) {
		try {
			this.setState(state);
		} catch (InvalidMessageDataException e) {
			e.printStackTrace();
		}
	}

	public MarketState getState() {
		return state;
	}

	private void setState(MarketState state) throws InvalidMessageDataException{
		if (state == null) {
			throw new InvalidMessageDataException("The state is empty");
		}
		this.state = state;
	}

}

package depaul.stockexchange.message;

public class MarketMessage {

	private MarketState state;
	
	public MarketMessage(MarketState state) {
		this.setState(state);
	}

	public MarketState getState() {
		return state;
	}

	private void setState(MarketState state) {
		this.state = state;
	}

}

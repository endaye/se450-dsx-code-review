package depaul.stockexchange.message;

import depaul.stockexchange.BookSide;
import depaul.stockexchange.price.Price;

public abstract class MessageBase {

	protected String user;
	protected String product;
	protected Price price;
	protected int volume;
	protected String details;
	protected BookSide side;
	public String id;

	public MessageBase(String user, String product, Price price, int volume, 
			String details, BookSide side, String id) 
					throws InvalidMessageDataException {
		this.setUser(user);
		this.setProduct(product);
		this.setPrice(price);
		this.setVolume(volume);
		this.setDetails(details);
		this.setSide(side);
		this.setId(id);
	}

	public MessageBase() {
		// TODO Auto-generated constructor stub
	}

	public String getUser() {
		return user;
	}

	public String getProduct() {
		return product;
	}

	public Price getPrice() {
		return price;
	}

	public int getVolume() {
		return volume;
	}

	public String getDetails() {
		return details;
	}

	public BookSide getSide() {
		return side;
	}

	public String getId() {
		return id;
	}

	protected void setUser(String user) 
			throws InvalidMessageDataException {
		if (user == null || user.length() == 0) {
			throw new InvalidMessageDataException("The username passed in "
					+ "can't be null or empty.");
		}
		this.user = user;
	}

	protected void setProduct(String product)
			throws InvalidMessageDataException {
		if (product == null || product.length() == 0) {
			throw new InvalidMessageDataException("The product passed in "
					+ "can't be null or empty.");
		}
		this.product = product;
	}

	protected void setPrice(Price price) 
			throws InvalidMessageDataException {
		if (price == null) {
			throw new InvalidMessageDataException("The price passed in "
					+ "can't be null.");
		}
		this.price = price;
	}

	protected void setVolume(int volume)  
            throws InvalidMessageDataException {
        if (volume < 0) {
            throw new InvalidMessageDataException("The volume passed in "
                    + "can't be negative: " + volume);
        }
        this.volume = volume;
    }

	protected void setDetails(String details) {
		this.details = details;
	}

	protected void setSide(BookSide side) {
		this.side = side;
	}

	protected void setSide(String side) 
			throws InvalidMessageDataException {
		if (side == null || "".equals(side)) {
			throw new InvalidMessageDataException("The side is empty");
		}
		try {
			this.side = BookSide.valueOf(side.trim().toUpperCase());
		} catch(IllegalArgumentException ex) {
			throw new InvalidMessageDataException("The side is invalid");
		}
	}

	protected void setId(String id)
			throws InvalidMessageDataException {
        if (id == null) {
            throw new InvalidMessageDataException("The id passed in "
                    + "can't be null.");
        }
        this.id = id;
    }
}

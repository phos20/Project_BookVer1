package dto;

public class NewBooks {
	private String newbooksId;
	private String newbooksName;
	private String newbooksWrite;
	private String newbooksGenre;
	private int newbooksPrice;
	private int stock;

	public NewBooks() {
	}

	public NewBooks(String newbooksId, String newbooksName, String newbooksWrite, String newbooksGenre,
			int newbooksPrice, int stock) {
		super();
		this.newbooksId = newbooksId;
		this.newbooksName = newbooksName;
		this.newbooksWrite = newbooksWrite;
		this.newbooksGenre = newbooksGenre;
		this.newbooksPrice = newbooksPrice;
		this.stock = stock;
	}

	public String getNewbooksId() {
		return newbooksId;
	}

	public void setNewbooksId(String newbooksId) {
		this.newbooksId = newbooksId;
	}

	public String getNewbooksName() {
		return newbooksName;
	}

	public void setNewbooksName(String newbooksName) {
		this.newbooksName = newbooksName;
	}

	public String getNewbooksWrite() {
		return newbooksWrite;
	}

	public void setNewbooksWrite(String newbooksWrite) {
		this.newbooksWrite = newbooksWrite;
	}

	public String getNewbooksGenre() {
		return newbooksGenre;
	}

	public void setNewbooksGenre(String newbooksGenre) {
		this.newbooksGenre = newbooksGenre;
	}

	public int getNewbooksPrice() {
		return newbooksPrice;
	}

	public void setNewbooksPrice(int newbooksPrice) {
		this.newbooksPrice = newbooksPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return newbooksId + " | " + newbooksName + " | " + newbooksWrite + " | " + newbooksGenre + " | " + newbooksPrice
				+ " | " + stock;
	}

	@Override
	public int hashCode() {
		return newbooksId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		NewBooks other = (NewBooks) obj;
		if (newbooksId.equals(other.newbooksId)) {
			return true;
		} else {
			return false;
		}
	}

}

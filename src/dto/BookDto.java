package dto; 

public class BookDto {
	private String booksId;
	private String booksName;
	private String booksWriter;
	private String booksPublisher;
	private String booksPubDate;
	private String booksGenre;
	private int booksPrice;
	private int stock;
	private String regDate;

	public BookDto() {
	}

	public BookDto(String booksId, String booksName, String booksWriter, String booksPublisher, String booksPubDate,
			String booksGenre, int booksPrice, int stock, String regDate) {
		super();
		this.booksId = booksId;
		this.booksName = booksName;
		this.booksWriter = booksWriter;
		this.booksPublisher = booksPublisher;
		this.booksPubDate = booksPubDate;
		this.booksGenre = booksGenre;
		this.booksPrice = booksPrice;
		this.stock = stock;
		this.regDate = regDate;
	}
	

	public String getBooksId() {
		return booksId;
	}

	public void setBooksId(String booksId) {
		this.booksId = booksId;
	}

	public String getBooksName() {
		return booksName;
	}

	public void setBooksName(String booksName) {
		this.booksName = booksName;
	}

	public String getBooksWriter() {
		return booksWriter;
	}

	public void setBooksWriter(String booksWriter) {
		this.booksWriter = booksWriter;
	}

	public String getBooksPublisher() {
		return booksPublisher;
	}

	public void setBooksPublisher(String booksPublisher) {
		this.booksPublisher = booksPublisher;
	}

	public String getBooksPubDate() {
		return booksPubDate;
	}

	public void setBooksPubDate(String booksPubDate) {
		this.booksPubDate = booksPubDate;
	}

	public String getBooksGenre() {
		return booksGenre;
	}

	public void setBooksGenre(String booksGenre) {
		this.booksGenre = booksGenre;
	}

	public int getBooksPrice() {
		return booksPrice;
	}

	public void setBooksPrice(int booksPrice) {
		this.booksPrice = booksPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	

	@Override
	public String toString() {
		return booksId +" | " + booksName +" | " + booksWriter +" | " + booksPublisher + " | " + booksPubDate 
				+ " | " + booksGenre + " | " + booksPrice + " | " + stock + " | " + regDate ; 
	}

	@Override
	public int hashCode() {
		return booksId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		BookDto other = (BookDto) obj;
		if (booksId.equals(other.booksId)) {
			return true;
		} else {
			return false;
		}
	}

}

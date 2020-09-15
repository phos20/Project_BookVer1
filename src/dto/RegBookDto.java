package dto;

public class RegBookDto {
	private int regNo;
	private String bookName;
	private String bookWriter;
	private String regDate;

	public RegBookDto() {
	}

	public RegBookDto(String bookName, String bookWriter) {
		super();
		this.bookName = bookName;
		this.bookWriter = bookWriter;

	}

	public RegBookDto(int regNo, String bookName, String bookWriter, String regDate) {
		super();
		this.regNo = regNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.regDate = regDate;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookWriter() {
		return bookWriter;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	@Override
	public String toString() {
		return bookName + " | " + bookWriter;
	}

}

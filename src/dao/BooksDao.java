package dao;

import java.sql.SQLException;

import dto.BookDto;

public interface BooksDao {
	/** 책번호로 검색 */
	BookDto booksSelectBybooksId(String booksId)throws SQLException;

}

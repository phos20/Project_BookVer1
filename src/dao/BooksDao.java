package dao;

import java.sql.SQLException;
import java.util.List;

import dto.BookDto;

public interface BooksDao {
	/**
	 * 책 제목으로 검색
	 */
	BookDto SelectByName(String booksName) throws SQLException;

	/**
	 * 책 장르로 검색
	 */
	List<BookDto> SelectByGenre(String booksGenre) throws SQLException;

	/** 책번호로 검색 */
	BookDto booksSelectBybooksId(String booksId) throws SQLException;

	/**
	 * 전체 검색
	 */
	List<BookDto> selectBook() throws SQLException;

	/**
	 * 신규 도서 등록
	 */
	int insertBook(BookDto bookDto) throws Exception;
	
	/**
	 * 도서 재고 추가
	 */
	int updateBook(String bookId, int stock) throws SQLException;

	/**
	 * 도서 삭제
	 */
	int deleteBook(String bookId) throws SQLException;

}

package dao;

import java.sql.SQLException;
import java.util.List;

import dto.BookDto;

public interface BooksDao {
	/**
	 * å �������� �˻�
	 */
	BookDto SelectByName(String booksName) throws SQLException;

	/**
	 * å �帣�� �˻�
	 */
	List<BookDto> SelectByGenre(String booksGenre) throws SQLException;

	/** å��ȣ�� �˻� */
	BookDto booksSelectBybooksId(String booksId) throws SQLException;

	/**
	 * ��ü �˻�
	 */
	List<BookDto> selectBook() throws SQLException;

	/**
	 * �ű� ���� ���
	 */
	int insertBook(BookDto bookDto) throws SQLException;

	/**
	 * �ű� ���� ����
	 */
	int deleteBook(String bookId) throws SQLException;

}

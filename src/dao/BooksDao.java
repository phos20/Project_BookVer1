package dao;

import java.sql.SQLException;

import dto.BookDto;

public interface BooksDao {
	/** å��ȣ�� �˻� */
	BookDto booksSelectBybooksId(String booksId)throws SQLException;

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.BookDto;
import util.DbUtil;



public class BooksDaoImpl implements BooksDao {
	
	/** 책번호로 검색 */
	@Override
	public BookDto booksSelectBybooksId(String booksId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookDto books = null;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from books where books_id=?");
			ps.setString(1, booksId);
			rs = ps.executeQuery();

			if (rs.next()) {
				books = new BookDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							 	 	rs.getInt(5), rs.getInt(6));

			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return books;
	}

	@Override
	public BookDto SelectByName(String booksName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto SelectByGenre(String booksGenre) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDto> selectBook() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBook(BookDto bookDto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBook(String bookId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}

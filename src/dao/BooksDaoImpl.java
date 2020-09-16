package dao; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));

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
	
	/**전체 책 목록 검색*/
	@Override
	public List<BookDto> selectBook() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookDto> list = new ArrayList<>();
		BookDto books = null;
		
		try {
			
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from books orderby books_id ");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				books = new BookDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
				list.add(books);
						
			}
			
		}finally {
			
			DbUtil.close(con, ps, rs);
		}
		
		return list;
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





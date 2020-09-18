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
	
	RegBookDaoImpl reg = new RegBookDaoImpl();

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
	
	/**책 제목 검색*/
	@Override
	public BookDto SelectByName(String booksName) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookDto books = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from books where books_name=?");
			ps.setString(1, booksName);
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


	   /**책 장르 검색*/
	   @Override
	   public List<BookDto> SelectByGenre(String booksGenre) throws SQLException {
		   Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from books where books_genre = ?";
			List<BookDto> list =  new ArrayList<>();
			
			try {
				con = DbUtil.getConnection();
				ps = con.prepareStatement(sql);
				
				ps.setString(1, booksGenre);
				rs = ps.executeQuery();
				
				
				while(rs.next()) {
					BookDto bookDto = new BookDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
							rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
					list.add(bookDto);
				}
			} finally {
				DbUtil.close(con, ps, rs);
			}
			
			return list;
	   }
	
	/**
	 * 도서목록 검색
	 */
	@Override
	public List<BookDto> selectBook() throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from books";
		List<BookDto> list =  new ArrayList<>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BookDto bookDto = new BookDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
				list.add(bookDto);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return list;
	}

	/**
	 * 희망도서에 있는 도서인지 없는 도서인지 검사 
	 */
	
	public int check(BookDto bookDto) {
		int result = 0;
		try {
			List<String> list = reg.selectRegBookName();
			for(String regName : list) {
				if(bookDto.getBooksName().equals(regName)) {
					// 희망도서에 있는 책 -> 신규도서 등록하면서 희망도서 함께 삭제 
					result = insertRegBookName(bookDto);
				} else {
					// 희망도서에 없는 책 -> 바로 신규 도서 등록 
					result = insertBook(bookDto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * 신규도서 등록
	 */
	@Override
	public int insertBook(BookDto bookDto) throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into books values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		int result = 0;
		
		try {
			
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, bookDto.getBooksId());
			ps.setString(2, bookDto.getBooksName());
			ps.setString(3, bookDto.getBooksWriter());
			ps.setString(4, bookDto.getBooksPublisher());
			ps.setString(5, bookDto.getBooksPubDate());
			ps.setString(6, bookDto.getBooksGenre());
			ps.setInt(7, bookDto.getBooksPrice());
			ps.setInt(8, bookDto.getStock());
			
			result = ps.executeUpdate();

		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/**
	 * 희망도서목록에 있는 도서 신규 입고
	 */
	public int insertRegBookName(BookDto bookDto) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into books values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, bookDto.getBooksId());
			ps.setString(2, bookDto.getBooksName());
			ps.setString(3, bookDto.getBooksWriter());
			ps.setString(4, bookDto.getBooksPublisher());
			ps.setString(5, bookDto.getBooksPubDate());
			ps.setString(6, bookDto.getBooksGenre());
			ps.setInt(7, bookDto.getBooksPrice());
			ps.setInt(8, bookDto.getStock());
			
			result = ps.executeUpdate();
			
			//if(result == 0) {
			//	con.rollback();
			//	throw new SQLException("도서 등록 실패");
			//} 
			
			//else {
				int re = booksDelete(con, bookDto.getBooksName());
	            if (re == 0) {
	            	con.rollback();
	            	throw new SQLException("등록 할수없습니다");
	            }
	               
	            con.commit();
			//}
			
		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	
	/**
	 * 희망도서 입고시 희망도서 삭제
	 */
	private int booksDelete(Connection con, String booksName) throws SQLException {
	      PreparedStatement ps = null;
	      int result = 0;

	      try {
	         ps = con.prepareStatement("delete from regbook where reg_name =?");
	         ps.setString(1, booksName);

	         result = ps.executeUpdate();

	      } finally {
	         DbUtil.close(null, ps, null);
	      }
	      return result;
	   }
	
	/**
	 * 도서 삭제
	 */
	@Override
	public int deleteBook(String bookId) throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from books where books_Id = ?";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, bookId);
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
}





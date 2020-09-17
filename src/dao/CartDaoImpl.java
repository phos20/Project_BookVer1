package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.CartDto;
import util.DbUtil;
import dto.BookDto;

public class CartDaoImpl implements CartDao {

	/**
	 * 장바구니에 같은 도서 있는지 확인
	 */
	@Override
	public int checkCart(CartDto cartDto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select books_id from cart where books_id = ?";
		int result = 0;
		String booksId = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, cartDto.getBooksId());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				booksId = rs.getString(1);
			}
			if(booksId == null) {
				result = insertCart(cartDto);
			} else {
				result = updateCart(cartDto);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return result;
		
	}
	/**
	 * 장바구니 담기
	 */
	
	public int insertCart(CartDto cartDto) throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into cart values(cart_no_seq.nextval, ?, ?, ?, sysdate)";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, cartDto.getUserId());
			ps.setString(2, cartDto.getBooksId());
			ps.setInt(3, cartDto.getQuantity());
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(con, ps, null);
		}
		
		return result;
	}

	/**
	 * 장바구니에 같은 도서코드 있는 경우 update
	 */
	public int updateCart(CartDto cartDto) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update cart set quantity = quantity+? where user_Id = ? and books_id = ?";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, cartDto.getQuantity());
			ps.setString(2, cartDto.getUserId());
			ps.setString(3, cartDto.getBooksId());
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(con, ps, null);
		}
		
		return result;
	}
	
	/**
	 * 장바구니 보기
	 */
	@Override
	public Map<BookDto, Integer> selectCart(String userId) throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select books_id, books_name, books_writer, books_price, quantity from books inner join cart using(books_id) where user_id = ?";
		//BookDto bookDto = null;
		Map<BookDto, Integer> map = new HashMap<BookDto, Integer>();
		//List<BookDto> list =  new ArrayList<>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BookDto bookDto = new BookDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				
				map.put(bookDto, rs.getInt(5));

				//list.add(bookDto);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return map;
	}
	
	/**
	 * 장바구니 삭제
	 */
	@Override
	public int deleteCart(String userId) throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from cart where user_id = ?";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userId);
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(con, ps, null);
		}
		
		return result;
	}

}

package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dto.BookDto;
import dto.CartDto;

public interface CartDao {
	
	/**
	 * 장바구니 담기 
	 */
	int checkCart(CartDto cartDto) throws SQLException;
	
	/**
	 * 장바구니 보기
	 */
	Map<BookDto, Integer> selectCart(String userId) throws SQLException;
	
	/**
	 * 장바구니 삭제
	 */
	int deleteCart(String userId) throws SQLException;
	

}

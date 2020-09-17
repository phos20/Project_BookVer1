package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dto.BookDto;
import dto.CartDto;

public interface CartDao {
	
	/**
	 * ��ٱ��� ��� 
	 */
	int checkCart(CartDto cartDto) throws SQLException;
	
	/**
	 * ��ٱ��� ����
	 */
	Map<BookDto, Integer> selectCart(String userId) throws SQLException;
	
	/**
	 * ��ٱ��� ����
	 */
	int deleteCart(String userId) throws SQLException;
	

}

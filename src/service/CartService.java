package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.CartDao;
import dao.CartDaoImpl;
import dto.BookDto;
import dto.CartDto;

public class CartService {
	
	CartDao cartDao = new CartDaoImpl();
	
	public int insertCart(CartDto cartDto) throws SQLException{
		
		int result = cartDao.checkCart(cartDto);
		if(result==0) throw new SQLException("��ٱ��� ��� ����");
		return result;
	}
	
	public Map<BookDto, Integer> selectCart(String userId) throws SQLException{
		Map<BookDto, Integer> map = cartDao.selectCart(userId);
		if(map==null) throw new SQLException("��ٱ��ϰ� ������ϴ�. ");
		return map;
	}
	
	public int deleteCart(String userId) throws SQLException{
		int result = cartDao.deleteCart(userId);
		if(result==0) throw new SQLException("��ٱ��� ���� ����");
		return result;
	}

}
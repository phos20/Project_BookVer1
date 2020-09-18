package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Orders;

public interface OrderDAO {
	
	/**주문*/
	int orderInsert(Orders orders)throws SQLException;
	/**주문내역확인*/
	List<Orders> selectOrdersByUserId(String userId)throws SQLException;
	

}

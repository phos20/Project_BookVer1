package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Orders;

public interface OrderDAO {
	
	/**�ֹ�*/
	int orderInsert(Orders orders)throws SQLException;
	/**�ֹ�����Ȯ��*/
	List<Orders> selectOrdersByUserId(String userId)throws SQLException;
	

}

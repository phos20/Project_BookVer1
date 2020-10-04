package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Orders;
import dto.Pay;

public interface OrderDAO {

	/** �ֹ� */
	int orderInsert(Orders orders) throws SQLException;

	/** �ֹ�����Ȯ�� */
	List<Orders> selectOrdersByUserId(String userId) throws SQLException;

	/** �������ݰ˻� */
	List<Pay> ordersPriceByUserId(String userId) throws SQLException;

	/** �ֹ���� */
	int cancleOrders(String userId, int orderNo) throws SQLException;

}

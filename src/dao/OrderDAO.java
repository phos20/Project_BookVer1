package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Orders;
import dto.Pay;

public interface OrderDAO {

	/** 주문 */
	int orderInsert(Orders orders) throws SQLException;

	/** 주문내역확인 */
	List<Orders> selectOrdersByUserId(String userId) throws SQLException;

	/** 결제가격검색 */
	List<Pay> ordersPriceByUserId(String userId) throws SQLException;

	/** 주문취소 */
	int cancleOrders(String userId, int orderNo) throws SQLException;

}

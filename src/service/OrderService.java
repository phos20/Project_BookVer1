package service;

import java.sql.SQLException;
import java.util.List;

import dao.OrderDAO;
import dao.OrderDAOImpl;
import dto.Orders;

public class OrderService {
	OrderDAO orderDao = new OrderDAOImpl();

	/** 주문 */
	public void insertOrders(Orders orders) throws SQLException {
		int result = orderDao.orderInsert(orders);
		if (result == 0)
			throw new SQLException("주문하기가 실패하였습니다.");
	}

	/** 주문내역 */
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		List<Orders> list = orderDao.selectOrdersByUserId(userId);
		if (list == null || list.size() == 0)
			throw new SQLException(userId + "님의 주문내역이 없습니다.");
		return list;
	}

	/** 주문취소 */
	public int cancleOrders(String userId, int orderNo) throws SQLException {
		int result = orderDao.cancleOrders(userId, orderNo);
		if (result == 0)
			throw new SQLException("주문 취소 실패");
		return result;
	}

}

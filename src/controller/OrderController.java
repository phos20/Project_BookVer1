package controller;

import java.util.List;


import dto.Orders;
import service.OrderService;
import view.EndView;
import view.FailView;

public class OrderController {
	private static OrderService orderService = new OrderService();

	/** �ֹ� */
	public static void insertOrders(Orders orders) {
		try {
			orderService.insertOrders(orders);
			EndView.messagePrint("�ֹ� �Ϸ�");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/** �ֹ�����Ȯ�� */
	public static void selectOrdersByUserId(String userId) {
		try {
			List<Orders> orderList = orderService.selectOrdersByUserId(userId);
			EndView.printOrderByUserId(orderList);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/** �ֹ���� */
	public static void cancleOrders(String userId, int orderNo) {
		try {
			orderService.cancleOrders(userId, orderNo);
			EndView.messagePrint("�ֹ� ��� �Ϸ� ");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

}

package controller;

import java.util.List;

import dto.Orders;
import service.OrderService;
import view.EndView;
import view.FailView;
import view.MenuView;

public class OrderController {
	private static OrderService orderService = new OrderService();

	/** 주문 */
	public static void insertOrders(Orders orders) {
		try {
			orderService.insertOrders(orders);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/** 주문내역확인 */
	public static void selectOrdersByUserId(String userId) {
		try {
			List<Orders> orderList = orderService.selectOrdersByUserId(userId);
			EndView.printOrderByUserId(orderList);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

}

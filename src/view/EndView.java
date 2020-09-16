package view;

import java.util.List;

import dto.OrderLine;
import dto.Orders;

public class EndView {
	/** 주문내역확인 */
	public static void printOrderByUserId(List<Orders> orderList) {
		for (Orders order : orderList) {
			System.out.println(order.getOrderId() + " | " + order.getOrderDate() + " | " + order.getTotalAmount()
					+ " | " + order.getAddress());

			for (OrderLine orderLine : order.getOrderLineList()) {
				System.out.println("  주문품목  :" + orderLine);
			}
			System.out.println();
		}

	}
	/**메세지*/
	public static void messagePrint(String message) {
		System.out.println(message);		
	}

}

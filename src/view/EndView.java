package view;

import java.util.List;

import dto.OrderLine;
import dto.Orders;

public class EndView {
	

	/** 林巩郴开犬牢 */
	public static void printOrderByUserId(List<Orders> orderList) {
		for (Orders order : orderList) {
			System.out.println(order.getOrderId() + " | " + order.getOrderDate() + " | " + order.getTotalAmount()
					+ " | " + order.getAddress());

			for (OrderLine orderLine : order.getOrderLineList()) {
				System.out.println("  林巩前格  :" + orderLine);
			}
			System.out.println();
		}

	}

}

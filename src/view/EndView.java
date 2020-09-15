package view;

import java.util.List;

import dto.OrderLine;
import dto.Orders;

public class EndView {
	

	/** �ֹ�����Ȯ�� */
	public static void printOrderByUserId(List<Orders> orderList) {
		for (Orders order : orderList) {
			System.out.println(order.getOrderId() + " | " + order.getOrderDate() + " | " + order.getTotalAmount()
					+ " | " + order.getAddress());

			for (OrderLine orderLine : order.getOrderLineList()) {
				System.out.println("  �ֹ�ǰ��  :" + orderLine);
			}
			System.out.println();
		}

	}

}

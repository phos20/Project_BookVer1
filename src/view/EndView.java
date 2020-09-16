package view;

import java.util.List;

import dto.BookDto;
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
	
	/** ��ü ����Ʈ �˻�*/
	public static void printBooksList(List<BookDto>list) {
		System.out.println("-----å "+ list.size() +"�� -------------");
		for(BookDto books : list) {
			System.out.println(books);
		}
		System.out.println();
	}
	
	/**�޼���*/
	public static void messagePrint(String message) {
		System.out.println(message);
		
	}

}

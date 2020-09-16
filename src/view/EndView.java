package view;

import java.util.List;

import dto.OrderLine;
import dto.Orders;
import dto.RegBookDto;

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
	/**������������ȸ */
	public static void printRegBookList(List<RegBookDto> list) {
	      System.out.println("-- ���� "+ list.size() +"�� --");
	      for(RegBookDto regbookDto : list) {
	         System.out.println(regbookDto);
	      }
	      System.out.println();
	   }
	/**printmessage*/
	public static void messagePrint(String message) {
		System.out.println(message);
	}
	
}

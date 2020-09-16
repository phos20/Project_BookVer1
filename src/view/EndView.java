package view;

import java.util.List;

import dto.OrderLine;
import dto.Orders;
import dto.RegBookDto;

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
	/**희망도서목록조회 */
	public static void printRegBookList(List<RegBookDto> list) {
	      System.out.println("-- 도서 "+ list.size() +"권 --");
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

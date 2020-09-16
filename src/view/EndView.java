package view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controller.OrderController;
import dto.OrderLine;
import dto.Orders;
import user.User;
import user.UserSet;
import dto.BookDto;
import dto.RegBookDto;

import dto.OrderLine;
import dto.Orders;

public class EndView {

	public static void printBookList(List<BookDto> list) {
		System.out.println("-- ���� "+ list.size() +"�� --");
		for(BookDto bookDto : list) {
			System.out.println(bookDto);
		}
		System.out.println();
	}
	
	public static void printRegBookList(List<RegBookDto> list) {
		System.out.println("-- ���� "+ list.size() +"�� --");
		for(RegBookDto regbookDto : list) {
			System.out.println(regbookDto);
		}
		System.out.println();
	}
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * ��ٱ��� ���� Ȯ�� 
	 */
	public static void printCart(String userId, Map<BookDto,Integer> cart) {
		
		for(BookDto BookDto: cart.keySet()) {
			String booksId = BookDto.getBooksId(); //���� �ڵ�
			String booksName = BookDto.getBooksName(); //���� ����
			int booksPrice = BookDto.getBooksPrice(); //���� ����
			int quantity = cart.get(BookDto); //
			System.out.println("�ڵ�: " + booksId+", ����: " + booksName + ", ����: " + booksPrice + ", ����: " + quantity);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1.�ֹ��ϱ�  |  9.������");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			
			 System.out.print("����ּ� : ");
			 String address = sc.nextLine();

			 Orders orders = new Orders(0, null, userId, address, 0);
			 List<OrderLine> orderLineList = orders.getOrderLineList();
			 for(BookDto bookDto : cart.keySet()) {
				 int qty = cart.get(bookDto);
				 OrderLine orderLine = new OrderLine(0, 0, bookDto.getBooksId() , 0, qty, 0);
				 orderLineList.add(orderLine);
			 }
			 System.out.println("�ֹ� ���� : " + orderLineList.size());
			 //OrderController.insertOrders(orders);
			 
			 //��ٱ��Ϻ���
			 UserSet ss = UserSet.getInstance();
			 User session = ss.get(userId);
			 session.removeAttribute("cart");
			break;
			
		case 9:
			break;
		}
	}

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

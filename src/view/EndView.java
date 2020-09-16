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
		System.out.println("-- 도서 "+ list.size() +"권 --");
		for(BookDto bookDto : list) {
			System.out.println(bookDto);
		}
		System.out.println();
	}
	
	public static void printRegBookList(List<RegBookDto> list) {
		System.out.println("-- 도서 "+ list.size() +"권 --");
		for(RegBookDto regbookDto : list) {
			System.out.println(regbookDto);
		}
		System.out.println();
	}
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * 장바구니 내용 확인 
	 */
	public static void printCart(String userId, Map<BookDto,Integer> cart) {
		
		for(BookDto BookDto: cart.keySet()) {
			String booksId = BookDto.getBooksId(); //도서 코드
			String booksName = BookDto.getBooksName(); //도서 제목
			int booksPrice = BookDto.getBooksPrice(); //도서 가격
			int quantity = cart.get(BookDto); //
			System.out.println("코드: " + booksId+", 제목: " + booksName + ", 가격: " + booksPrice + ", 수량: " + quantity);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1.주문하기  |  9.나가기");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			
			 System.out.print("배송주소 : ");
			 String address = sc.nextLine();

			 Orders orders = new Orders(0, null, userId, address, 0);
			 List<OrderLine> orderLineList = orders.getOrderLineList();
			 for(BookDto bookDto : cart.keySet()) {
				 int qty = cart.get(bookDto);
				 OrderLine orderLine = new OrderLine(0, 0, bookDto.getBooksId() , 0, qty, 0);
				 orderLineList.add(orderLine);
			 }
			 System.out.println("주문 개수 : " + orderLineList.size());
			 //OrderController.insertOrders(orders);
			 
			 //장바구니비우기
			 UserSet ss = UserSet.getInstance();
			 User session = ss.get(userId);
			 session.removeAttribute("cart");
			break;
			
		case 9:
			break;
		}
	}

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

}

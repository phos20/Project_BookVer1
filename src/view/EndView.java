package view;

import java.util.List;

import java.util.Map;
import java.util.Scanner;

import dto.BookDto;
import dto.OrderLine;
import dto.Orders;
import dto.RegBookDto;
import user.User;
import user.UserSet;

public class EndView {

	public static void printBookList(List<BookDto> list) {
		System.out.println("*** 도서 목록 ***");
		System.out.println("-- 도서 " + list.size() + "권 --");
		for (BookDto bookDto : list) {
			System.out.println(bookDto);
		}
		System.out.println();
	}
	
	public static void nonprintBookList(List<BookDto> list) {
		System.out.println("-- 도서 " + list.size() + "권 --");
		for (BookDto bookDto : list) {
			System.out.println(bookDto);
		}
		System.out.println();
		MenuView.nonmember();
	}

	
	
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * 장바구니 보기
	 */
	public static void showCart(Map<BookDto, Integer> map) {
		
		for(BookDto bookDto: map.keySet()) {
			String booksId = bookDto.getBooksId(); // 도서 코드
			String booksName = bookDto.getBooksName(); // 도서 제목
			int price = bookDto.getBooksPrice(); // 도서 가격
			//int quantity = map.get(bookDto);//
			System.out.println(booksId+" : "+booksName+" : "+price+" \t " + map.get(bookDto) + "권");
			//map.get(bookDto);
		}
		
	}

	/**
	 * 장바구니 내용 확인
	 */
	public static void printCart(String userId, Map<BookDto, Integer> cart) {

		for (BookDto BookDto : cart.keySet()) {
			String booksId = BookDto.getBooksId(); // 도서 코드
			String booksName = BookDto.getBooksName(); // 도서 제목
			int booksPrice = BookDto.getBooksPrice(); // 도서 가격
			int quantity = cart.get(BookDto); //
			System.out.println("코드: " + booksId + ", 제목: " + booksName + ", 가격: " + booksPrice + ", 수량: " + quantity);
		}

		Scanner sc = new Scanner(System.in);
		System.out.println("1.주문하기  |  9.나가기");
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:

			System.out.print("배송주소 : ");
			String address = sc.nextLine();

			Orders orders = new Orders(0, null, userId, address, 0);
			List<OrderLine> orderLineList = orders.getOrderLineList();
			for (BookDto bookDto : cart.keySet()) {
				int qty = cart.get(bookDto);
				OrderLine orderLine = new OrderLine(0, 0, bookDto.getBooksId(), 0, qty, 0);
				orderLineList.add(orderLine);
			}
			System.out.println("주문 개수 : " + orderLineList.size());
			// OrderController.insertOrders(orders);

			// 장바구니비우기
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
			System.out.println("주문일 : "+ order.getOrderDate() + " | " + order.getTotalAmount()
					+ " | " + order.getAddress());

			for (OrderLine orderLine : order.getOrderLineList()) {
				System.out.println("  주문품목  :" + orderLine);
			}
			System.out.println();
		}

	}

	/** 전체 리스트 검색 */
	public static void printBooksList(List<BookDto> list) {
		System.out.println("-----책 " + list.size() + "개 -------------");
		for (BookDto books : list) {
			System.out.println(books);
		}
		System.out.println();
	}

	/** 메세지 */
	public static void messagePrint(String message) {
		System.out.println(message);
	}

	/** 희망도서목록조회 */
	public static void printRegBookList(List<RegBookDto> list) {
		System.out.println("*** 희망도서 목록 ***");
		System.out.println("-- 도서 " + list.size() + "권 --");
		for (RegBookDto regbookDto : list) {
			System.out.println(regbookDto);
		}
		System.out.println();
	}
	
	/** 책 제목 검색 */
	   public static void printBookNameList(BookDto books) {
		   System.out.println("*** " + books.getBooksName() + " 검색 결과 ***");
	      System.out.println(books);
	      
	      
	   }

	   /** 책 장르 검색 */
	   public static void printBookGenreList(List<BookDto> list) {
		   System.out.println("-- 도서 " + list.size() + "권 --");
			for (BookDto bookDto : list) {
				System.out.println(bookDto);
			}
			System.out.println();
	   }

	

}

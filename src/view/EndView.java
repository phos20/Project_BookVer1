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
	/** ��ü ���� �˻� */
	public static void printBookList(List<BookDto> list) {
		System.out.println("���������������������������� ���� ��� ����������������������������");
		System.out.println("     �� ���� " + list.size() + "�� ");
		System.out.println("����������������������������������������������������������������������");
		System.out.println();
		for (BookDto bookDto : list) {
			System.out.println(bookDto);
		}
		System.out.println();
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * ��ٱ��� ����
	 */
	public static void showCart(Map<BookDto, Integer> map) {

		for (BookDto bookDto : map.keySet()) {
			String booksId = bookDto.getBooksId(); // ���� �ڵ�
			String booksName = bookDto.getBooksName(); // ���� ����
			int price = bookDto.getBooksPrice(); // ���� ����
			// int quantity = map.get(bookDto);//
			System.out.println(booksId + " : " + booksName + " : " + price + " \t " + map.get(bookDto) + "��");
			// map.get(bookDto);
		}

	}

	/**
	 * ��ٱ��� ���� Ȯ��
	 */
	public static void printCart(String userId, Map<BookDto, Integer> cart) {

		for (BookDto BookDto : cart.keySet()) {
			String booksId = BookDto.getBooksId(); // ���� �ڵ�
			String booksName = BookDto.getBooksName(); // ���� ����
			int booksPrice = BookDto.getBooksPrice(); // ���� ����
			int quantity = cart.get(BookDto); //
			System.out.println("�ڵ�: " + booksId + ", ����: " + booksName + ", ����: " + booksPrice + ", ����: " + quantity);
		}

		Scanner sc = new Scanner(System.in);
		System.out.println(" �� �ֹ��ϱ�     �ﳪ����");
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:

			System.out.print("����ּ� : ");
			String address = sc.nextLine();

			Orders orders = new Orders(0, null, userId, address, 0);
			List<OrderLine> orderLineList = orders.getOrderLineList();
			for (BookDto bookDto : cart.keySet()) {
				int qty = cart.get(bookDto);
				OrderLine orderLine = new OrderLine(0, 0, bookDto.getBooksId(), 0, qty, 0);
				orderLineList.add(orderLine);
			}
			System.out.println("�ֹ� ���� : " + orderLineList.size());
			// OrderController.insertOrders(orders);

			// ��ٱ��Ϻ���
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
			System.out.println("�ֹ���ȣ : " + order.getOrderId() + "  �ֹ��� :" + order.getOrderDate() + "  ���η������ �Ѱ��� :"
					+ order.getTotalAmount() + "  ����� : " + order.getAddress());

			for (OrderLine orderLine : order.getOrderLineList()) {
				System.out.println("  �ֹ�ǰ��  :" + orderLine);
			}
			System.out.println();
		}

	}

	/** ��ȸ�� ���� �˻� */
	public static void printBooksList(List<BookDto> list) {
		System.out.println("������������������������ å " + list.size() + "��  ������������������������");
		for (BookDto books : list) {
			System.out.println(books);
		}
		System.out.println();
		MenuView.nonmember();
	}

	/** �޼��� */
	public static void messagePrint(String message) {
		System.out.println(message);
	}

	/** ������������ȸ */
	public static void printRegBookList(List<RegBookDto> list) {
		System.out.println("���������������������������� ������� ��� ����������������������������");
		System.out.println("     ���� " + list.size() + "�� ");
		System.out.println("��������������������������������������������������������������������������������");
		for (RegBookDto regbookDto : list) {
			System.out.println(regbookDto);
		}
		System.out.println();
	}

	/** å ���� �˻� */
	public static void printBookNameList(BookDto books) {
		System.out.println("�������������� " + books.getBooksName() + " �˻� ��� ��������������");
		System.out.println(books);

	}

	/** å �帣 �˻� */
	public static void printBookGenreList(List<BookDto> list) {
		System.out.println("�������������� ���� " + list.size() + "�� ��������������");
		for (BookDto bookDto : list) {
			System.out.println(bookDto);
		}
		System.out.println();
	}

}
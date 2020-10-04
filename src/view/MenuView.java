package view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import controller.AdminController;
import controller.BooksController;
import controller.CartController;
import controller.OrderController;
import controller.RegBookController;
import controller.UserController;
import dao.OrderDAO;
import dao.OrderDAOImpl;
import dto.BookDto;
import dto.CartDto;
import dto.OrderLine;
import dto.Orders;
import dto.Pay;
import dto.RegBookDto;
import dto.UserDto;

public class MenuView {

	private static Scanner sc = new Scanner(System.in);
	static OrderDAO orderDao = new OrderDAOImpl();

	/**
	 * �ʱ� ȭ��
	 */
	public static void menu() {
		System.out.println("���������������������������������������������������������������������� ");
		System.out.println("      Book Store�� ���Ű� ȯ���մϴ�.     ");
		System.out.println("����������������������������������������������������������������������");
		System.out.println("\t��  ȸ��           �� ��ȸ��   ");
		System.out.println();
		System.out.print("����> ");
		int menu = Integer.parseInt(sc.nextLine());
		System.out.println();
		switch (menu) {
		case 1:
			Login();
			break;
		case 2:
			MenuView.nonmember();
			break;
		default:
			System.out.println("�ùٸ� ��ȣ�� ������ �ּ���");
			break;
		}
	}

	/**
	 * �α���
	 */
	private static void Login() {
		System.out.print("ID = ");
		String userId = sc.nextLine();
		System.out.print("PWD = ");
		String userPwd = sc.nextLine();
		System.out.println();

		UserController.Login(userId, userPwd);
	}

	/**
	 * case : 2 -��ȸ��-
	 */
	public static void nonmember() {
		System.out.println("�������������������� ��ȸ�� �޴� �������������������� ");
		System.out.println("\t�湮�ڴ� �޴��� �����ϼ���");
		System.out.println("��������������������������������������������������������");
		System.out.println("         �� ȸ������  �� ���� ��� ����  �� ����");
		System.out.println();
		System.out.print("����> ");
		int menu = Integer.parseInt(sc.nextLine());
		System.out.println();
		switch (menu) {
		case 1:
			MenuView.signUp();
			break;
		case 2:
			BooksController.selectBooks();
			break;
		case 3:
			System.exit(0);
		default:
			System.out.println("�ùٸ� ��ȣ�� ������ �ּ���");
			break;
		}
	}

	/**
	 * case : 2-1 -ȸ�� ����-
	 */
	private static void signUp() {
		System.out.print("���� ID : ");
		String userId = sc.nextLine();

		System.out.print("���� PW : ");
		String userPwd = sc.nextLine();

		System.out.print("���� �̸� : ");
		String userName = sc.nextLine();

		System.out.print("���� ����ȣ : ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.signUp(userDto);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////

	/** ���� �޴� */
	public static void printUserMenu(String userId) {

		while (true) {
			System.out.println("���������������������������������� User Menu �������������������������������������� ");
			System.out.println("      \t������ : " + userId + " ��  �޴��� �����ϼ��� ");
			System.out.println("���������������������������������������������������������������������������������������������� ");
			System.out.println(" �絵���˻�         ���ֹ�               ���ֹ�Ȯ�� &����     ������������&����  ");
			System.out.println(" ����ٱ��ϴ��   ����ٱ��Ϻ���   ����������           ��α׾ƿ�     ");
			System.out.println();
			System.out.print("����> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				MenuView.booksearch(userId);
				break;
			case 2:
				printInputOrder(userId);
				break;
			case 3:
				selectOrderAndPay(userId);
				break;
			case 4:
				wishBook(userId);
				break;
			case 5:
				putCart(userId);
				break;
			case 6:
				showCart(userId);
				break;
			case 7:
				myPage(userId);
				break;
			case 8:
				logOut(userId);
				break;
			default:
				System.out.println(userId + "�� �ùٸ� ��ȣ�� ������ �ּ���");
				break;
			}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 1 -�����˻� -
	 */
	public static void booksearch(String userId) {
		System.out.println("�������������������������������� ���� �˻�  ��������������������������������");
		System.out.println("                    ������ : " + userId + " ��  �޴��� �����ϼ��� ");
		System.out.println("������������������������������������������������������������������������������ ");
		System.out.println("     �� ��ü �˻�   �� ���� �˻�  �� �帣 �˻�  �� �ڷΰ��� ");
		System.out.println();
		System.out.print("����> ");

		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			BooksController.selectBook();
			booksearch(userId);
			break;
		case 2:
			searchName();
			booksearch(userId);
			break;
		case 3:
			searchGenre();
			booksearch(userId);
			break;
		case 4:
			printUserMenu(userId);
			break;
		default:
			System.out.println("�ùٸ� ��ȣ�� ������ �ּ���");
			booksearch(userId);
			break;
		}
	}

	/**
	 * case : 1-2 -���� �˻�-
	 */
	private static void searchName() {
		System.out.print("�˻��� å ���� : ");
		String booksName = sc.nextLine();
		System.out.println();
		BooksController.selectByName(booksName);

	}

	/**
	 * case : 1-3 -�帣 �˻�-
	 */
	private static void searchGenre() {
		System.out.print("�˻��� å �帣 : ");
		String booksGenre = sc.nextLine();
		System.out.println();
		BooksController.selectByGenre(booksGenre);

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 2 -�ֹ�-
	 */
	public static void printInputOrder(String userId) {
		System.out.print("�ֹ��� å ��ȣ : ");
		String booksId = sc.nextLine();

		System.out.print("�ֹ����� : ");
		int qty = Integer.parseInt(sc.nextLine());

		System.out.print("����ּ� : ");
		String address = sc.nextLine();

		Orders orders = new Orders(0, null, userId, address, 0);
		OrderLine orderLine = new OrderLine(0, 0, booksId, 0, qty, 0);
		orders.getOrderLineList().add(orderLine);

		OrderController.insertOrders(orders);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 3 - �ֹ�����Ȯ��& ����
	 */
	private static void selectOrderAndPay(String userId) {
		System.out.println(" �� �ֹ�Ȯ��     �����  ");
		System.out.println();
		System.out.print("����> ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			selectOrderMenu(userId);
			break;
		case 2:
			Payment(userId);
			break;
		default:
			System.out.println("�ùٸ� ��ȣ�� ������ �ּ���");
			break;
		}
	}

	/** case : 3-1 -�ֹ�Ȯ�� */
	private static void selectOrderMenu(String userId) {
		System.out.println(" �� �ֹ�����Ȯ��     ���ֹ����  ");
		System.out.println();
		System.out.print("����> ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			OrderController.selectOrdersByUserId(userId);
			break;
		case 2:
			cancleOrders(userId);
			break;
		default:
			System.out.println("�ùٸ� ��ȣ�� ������ �ּ���");
			break;
		}

	}

	/** case :3-1-2 -�ֹ���� */
	private static void cancleOrders(String userId) {
		System.out.println("�����ϰ����ϴ� �ֹ� ��ȣ�� �Է��ϼ��� :");
		int orderNo = Integer.parseInt(sc.nextLine());
		OrderController.cancleOrders(userId, orderNo);

	}

	/**
	 * case : 3-2 -����
	 */
	public static void Payment(String userId) {
		try {
			List<Pay> paylist = orderDao.ordersPriceByUserId(userId);
			double price = 0;
			for (Pay pay : paylist) {
				price += pay.getTotalAmount();

			}

			System.out.println("�ѱݾ� : " + Double.parseDouble(String.format("%.1f", price)) + " ���� �Ͻðڽ��ϱ�?");
			System.out.println("         ��  ����         �� ���  ");
			System.out.println();
			System.out.print("����> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				UserController.Pay(price, userId);
				break;
			case 2:
				break;
			default:
				System.out.println("�ùٸ� ��ȣ�� ��������");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 4 ����������&��ȸ
	 */
	public static void wishBook(String userId) {
		while (true) {
			System.out.println("�������������������������������� ������� ���&��ȸ  ��������������������������������");
			System.out.println("         �� ����������    �� ������������ȸ   �� ������");
			System.out.print("����> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				insertRegBook(userId); // ���
				break;
			case 2:
				RegBookController.selectRegBook(); // ��ȸ
				break;
			case 3:
				printUserMenu(userId); // ������

			default:
				System.out.println(userId + "�� �ùٸ� ��ȣ�� ������ �ּ���");
				break;
			}
		}
	}

	/**
	 * case : 4-1 ����������
	 */
	public static void insertRegBook(String userId) {

		System.out.print("������� ����: ");
		String regName = sc.nextLine();
		System.out.print("������� ����: ");
		String regWriter = sc.nextLine();
		System.out.print("������� ���ǻ�: ");
		String regPublisher = sc.nextLine();

		RegBookDto wish = new RegBookDto(0, regName, regWriter, regPublisher, userId, null);
		RegBookController.insertRegBook(wish);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 5 -��ٱ��� ���-
	 */
	public static void putCart(String userId) {
		System.out.println("�������������������� ��ٱ��� ��� ��������������������");
		System.out.print("�����ڵ�: ");
		String booksId = sc.nextLine();
		System.out.print("����: ");
		int quantity = Integer.parseInt(sc.nextLine());
		System.out.println();

		CartDto cartDto = new CartDto(0, userId, booksId, quantity, null);

		if (BooksController.selectByBooksId(booksId) == null) {
			System.out.println("������ �������� �ʾ� ��ٱ��Ͽ� ���� �� �����ϴ�. ");
		} else
			CartController.insertCart(cartDto);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////		

	/**
	 * case : 6 -��ٱ��� ����-
	 */
	public static void showCart(String userId) {
		System.out.println("������������������������������ ��ٱ��� ��� ������������������������������");
		Map<BookDto, Integer> map = CartController.selectCart(userId);
		Set<BookDto> set = map.keySet(); // ��ٱ��Ͽ� ��� å���� ������

		// if(map==null) {
		// System.out.println("��ٱ��ϰ� ����ֽ��ϴ�.");
		// }
		// else {
		System.out.println();
		System.out.println("�� �ֹ��ϱ�    �� �����ϱ�    �鳪����");
		System.out.print("����> ");
		System.out.println();
		int num = Integer.parseInt(sc.nextLine());

		if (num == 1) {
			System.out.print("����ּ�: ");
			String address = sc.nextLine();

			Orders orders = new Orders(0, null, userId, address, 0);

			Iterator<BookDto> iterator = set.iterator(); // �ݺ��� ���
			while (iterator.hasNext()) { // ��ü �� ��ŭ ����
				BookDto bookDto = iterator.next(); // �� ���� ��ü ������

				OrderLine orderLine = new OrderLine(0, 0, bookDto.getBooksId(), 0, map.get(bookDto), 0);
				orders.getOrderLineList().add(orderLine);
			}

			OrderController.insertOrders(orders);
			CartController.deleteCart(userId);
		} else if (num == 2) {
			CartController.deleteCart(userId);
		} else if (num == 3)
			return;
		// else System.out.println("�ٽ� �Է����ּ���. ");
		// }

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 7 -����������-
	 */
	public static void myPage(String userId) {
		while (true) {
			System.out.println("������������������������������������������������������������������������������������������");
			System.out.println("               ����� ���� ���������� �Դϴ� ");
			System.out.println("������������������������������������������������������������������������������������������");
			System.out.println("  �系���� �����ϱ�    �� ������ Ż���ϱ�    �� ����Ʈ �����ϱ�  �� �ڷΰ���    ");
			System.out.println();
			System.out.print("����> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				updateUserInfo(userId);
				break;
			case 2:
				deleteUserInfo(userId);
				menu();
			case 3:
				userPoint(userId);
				break;
			case 4:
				printUserMenu(userId);
			default:
				System.out.println(userId + "�� �ùٸ� ��ȣ�� ������ �ּ���");
				break;
			}
		}
	}

	/**
	 * case: 7-1 -ȸ������-
	 */
	private static void updateUserInfo(String userId) {
		System.out.print("ȸ�� ��й�ȣ: ");
		String userPwd = sc.nextLine();
		System.out.print("ȸ�� �̸�: ");
		String userName = sc.nextLine();
		System.out.print("ȸ�� �޴�����ȣ: ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.updateUserInfo(userDto);
	}

	/**
	 * case : 7-2 -ȸ��Ż��-
	 */
	private static void deleteUserInfo(String userId) {
		System.out.print("ȸ�� ��й�ȣ: ");
		String userPwd = sc.nextLine();
		System.out.print("ȸ�� �̸�: ");
		String userName = sc.nextLine();
		System.out.print("ȸ�� �޴�����ȣ: ");
		String userPhone = sc.nextLine();

		UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
		UserController.deleteUserInfo(userDto);
	}

	/**
	 * case : 7-3 -����Ʈ-
	 */
	public static void userPoint(String userId) {

		System.out.println("���� ����Ʈ: " + UserController.selectPoint(userId));
		System.out.println(" �� ����Ʈ ����ϱ�    �� �ڷ� ");
		System.out.println();
		System.out.print("����> ");
		int num = Integer.parseInt(sc.nextLine());
		if (num == 1) {
			System.out.print("����� ����Ʈ: ");
			int point = Integer.parseInt(sc.nextLine());

			UserController.userPoint(userId, point);
		}
		if (num == 2)
			return;
		// else System.out.println("�ٽ� �Է����ּ���. ");
	}

///////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 8 -�α׾ƿ�-
	 */
	private static void logOut(String userId) {
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
		UserDto userDto = new UserDto(null, null, null, null, 0, null, 0, null);
		System.exit(0);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * �����ڸ޴�
	 */
	public static void printAdminMenu(String userId) {

		while (true) {
			System.out.println("����������������������������  Admin Menu ������������������������������");
			System.out.println("\t\t������ " + userId + "�� ���   ");
			System.out.println("������������������������������������������������������������������������������������");
			System.out.println("  ��ȸ������   �赵������   ��������   ��α׾ƿ�  ");
			System.out.println();
			System.out.print("����> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				UserManagement();
				break;
			case 2:
				manageBook();
				break;
			case 3:
				SalesManagement(userId);
				break;
			case 4:
				logOut(userId);
				break;
			default:
				System.out.println(userId + "�����ڴ� �ùٸ� ��ȣ�� ������ �ּ���");
				break;
			}
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 1 -ȸ������-
	 */
	private static void UserManagement() {
		System.out.println("���������������������� ȸ�� ���� �޴� ����������������������");
		System.out.println("    �� ȸ�� ��� ����   �� ȸ�����   �� �ڷΰ��� ");
		System.out.print("����> ");
		System.out.println();

		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			AdminController.selectUserList();
			break;
		case 2:
			updateUserGrade();
			break;
		case 3:
			return;
		default:
			System.out.println("�����ڴ� �ùٸ� ��ȣ�� ������ �ּ���");
			break;
		}
	}

	/**
	 * case 1-2 : -ȸ�����-
	 */
	private static void updateUserGrade() {
		System.out.print("��� �� ȸ�� ID : ");
		String userId = sc.nextLine();
		System.out.print("��� : ");
		String grade = sc.nextLine();

		AdminController.updateUserGrade(grade, userId);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 2 -��������-
	 */
	public static void manageBook() {
		System.out.println("������������������������������ �������� �޴� ������������������������������������");
		System.out.println(" �� ������� ����   ������������ ����   �鵵�� ���   �굵�� ����   �볪����");
		System.out.println();
		System.out.print("����> ");
		int num = Integer.parseInt(sc.nextLine());

		switch (num) {
		case 1: // �������
			BooksController.selectBook();
			return;
		case 2: // ����������
			RegBookController.selectRegBook();
			break;
		case 3: // �������
			printInsertBook();
			break;
		case 4: // ��������
			printDeleteBook();
			break;
		case 5:
			return;

		}
	}

	/**
	 * case : 2-3 -���� ���-
	 */
	public static void printInsertBook() {

		System.out.print("��ϵ��� �ڵ�: ");
		String booksId = sc.nextLine();
		System.out.print("��ϵ��� ���� : ");
		String booksName = sc.nextLine();
		System.out.print("��ϵ��� ���� : ");
		String booksWriter = sc.nextLine();
		System.out.print("��ϵ��� ���ǻ� : ");
		String booksPublisher = sc.nextLine();
		System.out.print("��ϵ��� ������ : ");
		String booksPubDate = sc.nextLine();
		System.out.print("��ϵ��� �帣 : ");
		String booksGenre = sc.nextLine();
		System.out.print("��ϵ��� ���� : ");
		int booksPrice = Integer.parseInt(sc.nextLine());
		System.out.print("��ϵ��� ���� : ");
		int bookStock = Integer.parseInt(sc.nextLine());

		BookDto bookDto = new BookDto(booksId, booksName, booksWriter, booksPublisher, booksPubDate, booksGenre,
				booksPrice, bookStock, null); // book ��ü ����

		BooksController.insertBook(bookDto);
	}

	/**
	 * case : 2-4 -���� ����-
	 */
	public static void printDeleteBook() {
		System.out.print("�������� �ڵ�: ");
		String bookId = sc.nextLine();

		BooksController.deleteBook(bookId);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * case : 3 -�������-
	 */
	private static void SalesManagement(String userId) {
		System.out.println("�������������������������������� ������� �޴� ��������������������������������");
		System.out.println("    �� ������ ����   �� �Ⱓ�� ����   �� �� ����   �� �ڷΰ��� ");
		System.out.println();
		System.out.print("����> ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			AdminController.todaySales();
			break;
		case 2:
			periodSales();
			break;
		case 3:
			AdminController.totalSales();
			break;
		case 4:
			printAdminMenu(userId);
			break;
		default:
			System.out.println("��ȣ�� �°� �������ּ���");
			break;
		}
	}

	/**
	 * case : 3-2 -�Ⱓ�� ����-
	 */
	private static void periodSales() {
		System.out.print("�������� ? ");
		String startdate = sc.nextLine();
		System.out.print("�������� ? ");
		String enddate = sc.nextLine();

		AdminController.periodSales(startdate, enddate);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

} // MainView ��

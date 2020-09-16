package view;

import java.util.Scanner;

import controller.AdminController;
import controller.BooksController;
import controller.OrderController;
import controller.UserController;
import dto.OrderLine;
import dto.Orders;
import dto.UserDto;
import user.UserSet;

public class MenuView {

	private static Scanner sc = new Scanner(System.in);

	/** �ʱ� ȭ�� */
	public static void menu() {
		System.out.println("--- Book Store�� ���Ű� ȯ���մϴ�. ---");
		System.out.println("      | 1.ȸ��   | 2.��ȸ��    |");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			break;
		case 2:
			MenuView.nonmember();
			break;
		default:
			System.out.println("�ùٸ� ��ȣ�� ������ �ּ���");
			break;
		}

	}
	
	//case : 2 - ��ȸ��-
	public static void nonmember() {
		System.out.println("---- ��ȸ�� �޴� ----");
		System.out.println(" | 1.ȸ������ | 2.���� ��� ���� | ");
		
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			MenuView.signUp();
			break;
		case 2:
			BooksController.selectBook();
			break;
		default:
			System.out.println("�ùٸ� ��ȣ�� ������ �ּ���");
			break;
		}
	}
			
	// case : 2-1 ȸ�� ����
	private static void signUp() {
			System.out.println("���� ID : ");
			String userId = sc.nextLine();
				
			System.out.println("���� PW : ");
			String userPwd = sc.nextLine();
				
			System.out.println("���� �̸� : ");
			String userName = sc.nextLine();
				
			System.out.println("���� ����ȣ : ");
			String userPhone = sc.nextLine();
			
			UserDto userDto = new UserDto(userId, userPwd, userName, userPhone, 0, null, 0, null);
			UserController.signUp(userDto);
	}
				

	/** ���� �޴� */
	public static void printUserMenu(String userId) {

		while (true) {
			UserSet userset = UserSet.getInstance();
			System.out.println(userset.getSet());
			System.out.println("-----------------  User Menu -------------------");
			System.out.println("--------------- " + userId + " �� ������ ȯ���մϴ�  --------------");
			System.out.println("| 1.�����˻�        | 2.�ֹ�     | 3.�ֹ�����Ȯ��    | 4.����������&���� | ");
			System.out.println("| 5.��ٱ��ϴ��  | 6.��ٱ��Ϻ���  |  7.����������   |  8.�α׾ƿ�     |");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				MenuView.booksearch();
				return;
			case 2:
				printInputOrder(userId);
				break;
			case 3:
				OrderController.selectOrdersByUserId(userId);
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			default:
				System.out.println(userId + "�� �ùٸ� ��ȣ�� ������ �ּ���");
				break;
			}
		}
	}

	//case : 1 -�����˻�-
		public static void booksearch() {
			System.out.println("---- ���� �˻� ----");
			System.out.println(" | 1.��ü �˻� | 2.���� �˻� | 3.�帣 �˻� |");
			System.out.println("-------------------");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				BooksController.selectBook();
				break;
			case 2:
				BooksController.selectByName();
				break;
			case 3:
				BooksController.selectByGenre();
				break;
			default:
				System.out.println("�ùٸ� ��ȣ�� ������ �ּ���");
				break;
			}
		}
	
	// case : 2 -�ֹ�
	
	
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

	/** �����ڸ޴� */
	public static void printAdminMenu(String userId) {

		while (true) {
			UserSet userset = UserSet.getInstance();
			System.out.println("--------------  Admin Menu --------------");
			System.out.println("------------- ������ " + userId + "�� ���    -------------");
			System.out.println("| 1.ȸ������   | 2.��������  | 3.�������  | 4.�α׾ƿ�  |");

			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				UserManagement();
				return;
			case 2:
				break;
			case 3:
				SalesManagement(userId);
				break;
			case 4:
				break;
			default:
				System.out.println(userId + "�����ڴ� �ùٸ� ��ȣ�� ������ �ּ���");
				break;

			}
		}
	}
	
	//case : 1 -ȸ������
	private static void UserManagement() {
		System.out.println("---- ȸ�� ���� �޴� ----");
		System.out.println("| 1. ȸ�� ��� ����  |  2.ȸ�����  | 3. �ڷΰ���  |");

		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			AdminController.selectUserList();
			break;
		case 2:
			updateUserGrade();
			break;
		case 3 :
			return;
		default:
			System.out.println("�����ڴ� �ùٸ� ��ȣ�� ������ �ּ���");
			break;
		}
		
	}
	//case 1-2 : ȸ�����
	private static void updateUserGrade() {
		System.out.println("��� �� ȸ�� ID : ");
		String userId = sc.nextLine();
		System.out.println("��� : ");
		String grade = sc.nextLine();
		
		AdminController.updateUserGrade(grade,userId);
		

	}

	// case : 3 - �������
	private static void SalesManagement(String userId) {
		System.out.println("---- ������� �޴� ----");
		System.out.println(" | 1.������ ���� | 2.�Ⱓ�� ���� | 3.�� ����   | 4.�ڷΰ��� ");
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

	// case : 3-2 - �Ⱓ�� ����
	private static void periodSales() {
		System.out.println("�������� ? ");
		String startdate = sc.nextLine();
		System.out.println("�������� ? ");
		String enddate = sc.nextLine();

		AdminController.periodSales(startdate, enddate);

	}

}

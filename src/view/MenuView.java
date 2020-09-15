package view;

import java.util.Scanner;

import controller.AdminController;
import controller.BooksController;
import controller.OrderController;
import controller.RegBookController;
import dto.BookDto;
import dto.OrderLine;
import dto.Orders;
import user.UserSet;

public class MenuView {
	
	private static Scanner sc = new Scanner(System.in);

	/** �ʱ� ȭ��*/
	public static void menu() {
		System.out.println("--- Book Store�� ���Ű� ȯ���մϴ�. ---");
		System.out.println("      | 1.ȸ��   | 2.��ȸ��    |");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			break;
		case 2:
			break;
		default:
			System.out.println("�ùٸ� ��ȣ�� ������ �ּ���");
			break;
		}

	}

	/** ���� �޴�*/
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
				return;
			case 2:
				break;
			case 3:
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
	


	/** �����ڸ޴�*/
	public static void printAdminMenu(String userId) {

		while (true) {
			UserSet userset = UserSet.getInstance();
			System.out.println("--------------  Admin Menu --------------");
			System.out.println("------------- ������ " + userId + "�� ���    -------------");
			System.out.println("| 1.ȸ������   | 2.��������  | 3.�������  | 4.�α׾ƿ�  |");

			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				return;
			case 2:
				manageBook();
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				System.out.println(userId + "�����ڴ� �ùٸ� ��ȣ�� ������ �ּ���");
				break;

			}
		}
	}
	
	public static void manageBook() {
		System.out.println("1. ������� ���� 2. ���������� ���� 3. ���� ��� 4. ���� ���� 5. ������");
		int num = Integer.parseInt(sc.nextLine());
		
		switch(num) {
			case 1 : // �������
				BooksController.selectBook(); 
				return;	
			case 2 : // ���������� 
				RegBookController.selectRegBook();
				break;
			case 3: // ������� 
				printInsertBook();
				break;
			case 4: // ��������
				printDeleteBook();
				break;
			case 5:
				System.exit(0);
		
		}
	}
	
	
	/**
	 * ���� ��� 
	 */
	public static void printInsertBook() {
		
		System.out.println("��ϵ��� �ڵ�: ");
		String booksId = sc.nextLine();
		System.out.println("��ϵ��� ���� : ");
		String booksName = sc.nextLine();
		System.out.println("��ϵ��� ���� : ");
		String booksWriter = sc.nextLine();
		System.out.println("��ϵ��� ���ǻ� : ");
		String booksPublisher = sc.nextLine();
		System.out.println("��ϵ��� ������ : ");
		String booksPubDate = sc.nextLine();
		System.out.println("��ϵ��� �帣 : ");
		String booksGenre = sc.nextLine();
		System.out.println("��ϵ��� ���� : ");
		int booksPrice = Integer.parseInt(sc.nextLine());
		System.out.println("��ϵ��� ���� : ");
		int bookStock = Integer.parseInt(sc.nextLine());
		
		BookDto bookDto = new BookDto(booksId, booksName, booksWriter, booksPublisher, booksPubDate, booksGenre, booksPrice, bookStock, null); // book ��ü ���� 
		
		BooksController.insertBook(bookDto); 
	}
	
	
	/**
	 * ���� ����
	 */
	public static void printDeleteBook() {
		System.out.println("�������� �ڵ�: ");
		String bookId = sc.nextLine();
		
		BooksController.deleteBook(bookId);
	}



}

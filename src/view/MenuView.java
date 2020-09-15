package view;

import java.util.Scanner;

import controller.AdminController;
import controller.OrderController;
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


}

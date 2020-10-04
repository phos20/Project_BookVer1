package view;

import java.util.List;

import dto.Sales;
import dto.UserDto;

public class AdminEndView {
	/** �޼��� */
	public static void printMessage(String message) {
		System.out.println(message);

	}

	/** ���� ����ǥ */
	public static void printSales(List<Sales> list) {
		int total = 0;
		System.out.println("���������������������������� ������ ����ǥ ����������������������������");
		for (Sales sales : list) {
			System.out.println(sales);
			total += sales.getTotalAmount();
		}
		System.out.println("���� �� ���� = " + total);
		System.out.println();

	}

	/** �Ѹ���� */
	public static void totalSales(int result) {
		System.out.println("�Ѹ��� �ݾ� = " + result);

	}

	/** �Ⱓ���� ����� */
	public static void periodSales(String startdate, String enddate, int result) {
		System.out.println(startdate + "����" + enddate + " ���� �Ѹ��� =" + result);

	}

	/** ȸ�� ��� ���� */
	public static void selectUserList(List<UserDto> list) {
		System.out.println("���������������������������� ȸ�� ��� ���� ����������������������������");
		for (UserDto userDto : list) {
			System.out.println(userDto);
		}

	}

}



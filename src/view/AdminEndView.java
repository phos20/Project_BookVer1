package view;

import java.util.List;

import dto.Orders;

public class AdminEndView {
	
	/** �޼��� */
	public static void printMessage(String message) {
		System.out.println(message);

	}

	/** ���� ����ǥ */
	public static void printSales(List<Orders> list) {
		System.out.println("--------------������ ����ǥ-------------");
		for (Orders or : list) {
			System.out.println(or);
		}
		System.out.println();

	}

	/** �Ѹ���� */
	public static void totalSales(int result) {
		System.out.println("�Ѹ��� �ݾ� = " + result);

	}

	/** �Ⱓ���� ����� */
	public static void periodSales(String startdate, String enddate, int result) {
		System.out.println(startdate + "~" + enddate + " ���� �Ѹ��� =" + result);

	}

}

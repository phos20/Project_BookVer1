package view;

import java.util.List;

import dto.Orders;

public class AdminEndView {
	/** 메세지 */
	public static void printMessage(String message) {
		System.out.println(message);

	}

	/** 일일 매출표 */
	public static void printSales(List<Orders> list) {
		int total = 0;
		System.out.println("--------------오늘의 매출표-------------");
		for (Orders or : list) {
			System.out.println(or);
			total += or.getTotalAmount();
		}
		System.out.println("오늘 총 매출 = " + total);
		System.out.println();

	}

	/** 총매출액 */
	public static void totalSales(int result) {
		System.out.println("총매출 금액 = " + result);

	}

	/** 기간동안 매출액 */
	public static void periodSales(String startdate, String enddate, int result) {
		System.out.println(startdate + "~" + enddate + " 까지 총매출 =" + result);

	}

}

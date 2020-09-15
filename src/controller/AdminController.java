package controller;

import java.util.List;

import dto.Orders;
import service.AdminService;
import view.AdminEndView;
import view.FailView;

public class AdminController {
	static AdminService adminService = new AdminService();

	/** 오늘의매출 */
	public static void todaySales() {
		try {
			List<Orders> list = adminService.todaySales();
			AdminEndView.printSales(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/** 총매출 */
	public static void totalSales() {
		try {
			int result = adminService.totalSales();
			AdminEndView.totalSales(result);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/** 기간별 매출 */
	public static void periodSales(String startdate, String enddate) {
		try {
			int result = adminService.periodSales(startdate, enddate);
			AdminEndView.periodSales(startdate, enddate, result);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

}// class

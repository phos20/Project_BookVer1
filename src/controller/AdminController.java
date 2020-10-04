package controller;

import java.util.List;

import dto.Sales;
import dto.UserDto;
import service.AdminService;
import view.AdminEndView;
import view.FailView;

public class AdminController {
	static AdminService adminService = new AdminService();

	/** �����Ǹ��� */
	public static void todaySales() {
		try {
			List<Sales> list = adminService.todaySales();
			AdminEndView.printSales(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/** �Ѹ��� */
	public static void totalSales() {
		try {
			int result = adminService.totalSales();
			AdminEndView.totalSales(result);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/** �Ⱓ�� ���� */
	public static void periodSales(String startdate, String enddate) {
		try {
			int result = adminService.periodSales(startdate, enddate);
			AdminEndView.periodSales(startdate, enddate, result);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/** ȸ�� ��� ���� */
	public static void selectUserList() {
		try {
			List<UserDto> list = adminService.selectUserList();
			AdminEndView.selectUserList(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/** ȸ�� ��� */
	public static void updateUserGrade(String grade, String userId) {
		try {
			int result = adminService.updateUserGrade(grade, userId);
			AdminEndView.printMessage("����� ó�� �Ϸ�Ǿ����ϴ�.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

}// class

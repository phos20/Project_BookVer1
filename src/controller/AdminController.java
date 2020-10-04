package controller;

import java.util.List;

import dto.Sales;
import dto.UserDto;
import service.AdminService;
import view.AdminEndView;
import view.FailView;

public class AdminController {
	static AdminService adminService = new AdminService();

	/** 오늘의매출 */
	public static void todaySales() {
		try {
			List<Sales> list = adminService.todaySales();
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

	/** 회원 목록 보기 */
	public static void selectUserList() {
		try {
			List<UserDto> list = adminService.selectUserList();
			AdminEndView.selectUserList(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/** 회원 등업 */
	public static void updateUserGrade(String grade, String userId) {
		try {
			int result = adminService.updateUserGrade(grade, userId);
			AdminEndView.printMessage("등업이 처리 완료되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

}// class

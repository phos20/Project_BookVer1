package service;

import java.sql.SQLException;
import java.util.List;

import dao.AdminDao;
import dao.AdminDaoImpl;
import dto.Orders;

public class AdminService {
	AdminDao adminDao = new AdminDaoImpl();

	/** 일일매출액 */
	public List<Orders> todaySales() throws Exception {
		List<Orders> list = adminDao.todaySales();
		if (list.size() == 0)
			throw new SQLException("오늘 매출이 없습니다");
		return list;
	}

	/** 총 매출액 */
	public int totalSales() throws Exception {
		int result = adminDao.totalSales();
		if (result == 0)
			throw new SQLException("총매출이 없습니다");
		return result;
	}

	/** 기간별매출액 */
	public int periodSales(String startdate, String enddate) throws Exception {
		int result = adminDao.periodSales(startdate, enddate);
		if (result == 0)
			throw new SQLException("기간동안 매출이 없습니다");
		return result;
	}

}

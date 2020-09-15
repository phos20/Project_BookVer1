package service;

import java.sql.SQLException;
import java.util.List;

import dao.AdminDao;
import dao.AdminDaoImpl;
import dto.Orders;

public class AdminService {
	AdminDao adminDao = new AdminDaoImpl();

	/** ���ϸ���� */
	public List<Orders> todaySales() throws Exception {
		List<Orders> list = adminDao.todaySales();
		if (list.size() == 0)
			throw new SQLException("���� ������ �����ϴ�");
		return list;
	}

	/** �� ����� */
	public int totalSales() throws Exception {
		int result = adminDao.totalSales();
		if (result == 0)
			throw new SQLException("�Ѹ����� �����ϴ�");
		return result;
	}

	/** �Ⱓ������� */
	public int periodSales(String startdate, String enddate) throws Exception {
		int result = adminDao.periodSales(startdate, enddate);
		if (result == 0)
			throw new SQLException("�Ⱓ���� ������ �����ϴ�");
		return result;
	}

}

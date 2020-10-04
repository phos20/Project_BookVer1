package service;

import java.sql.SQLException;
import java.util.List;

import dao.AdminDao;
import dao.AdminDaoImpl;
import dto.Sales;
import dto.UserDto;

public class AdminService {
	AdminDao adminDao = new AdminDaoImpl();

	/** ���ϸ���� */
	public List<Sales> todaySales() throws Exception {
		List<Sales> list = adminDao.todaySales();
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

	/**ȸ�� ��� ����*/
	public List<UserDto> selectUserList() throws Exception{
		List<UserDto> list = adminDao.selectUserList();
		if(list.size() == 0)
			throw new SQLException("���Ե� ȸ�� ����� �����ϴ�");
		return list;
	}

	/**ȸ�� ���*/
	public int updateUserGrade(String grade,String userId) throws Exception{
		int result = adminDao.updateUserGrade(grade,userId);
		if(result == 0)
			throw new SQLException("��� ���� �Ͽ����ϴ�.");
		return result;
	}


}

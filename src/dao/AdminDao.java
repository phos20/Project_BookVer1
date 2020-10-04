package dao;

import java.sql.SQLException;

import java.util.List;

import dto.Sales;
import dto.UserDto;

public interface AdminDao {
	/** ���ϸ��� */
	List<Sales> todaySales() throws SQLException;

	/** �Ѹ��� */
	int totalSales() throws SQLException;

	/** �Ⱓ������ */
	int periodSales(String startdate, String enddate) throws SQLException;

	/**
	 * ȸ�� ���� - ȸ�� ��� ����
	 */

	List<UserDto> selectUserList() throws SQLException;

	/**
	 * ȸ�� ���� - ȸ�� ���
	 */

	int updateUserGrade(String grade, String userId) throws SQLException;

}

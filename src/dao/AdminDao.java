package dao;

import java.sql.SQLException;

import java.util.List;

import dto.Orders;
import dto.UserDto;

public interface AdminDao {
	/** ���ϸ��� */
	List<Orders> todaySales() throws SQLException;

	/** �Ѹ��� */
	int totalSales() throws SQLException;

	/** �Ⱓ������ */
	int periodSales(String startdate, String enddate) throws SQLException;

	/**
	 * ȸ�� ���� - ȸ�� ��� ����
	 */

	List<UserDto> selectUserList(String userId) throws SQLException;

	/**
	 * ȸ�� ���� - ȸ�� ���
	 */

	int updateUserGrade(String userId) throws SQLException;

}

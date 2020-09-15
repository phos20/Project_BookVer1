package dao;

import java.sql.SQLException;

import java.util.List;

import dto.Orders;
import dto.UserDto;

public interface AdminDao {
	/** 일일매출 */
	List<Orders> todaySales() throws SQLException;

	/** 총매출 */
	int totalSales() throws SQLException;

	/** 기간별매출 */
	int periodSales(String startdate, String enddate) throws SQLException;

	/**
	 * 회원 관리 - 회원 목록 보기
	 */

	List<UserDto> selectUserList(String userId) throws SQLException;

	/**
	 * 회원 관리 - 회원 등업
	 */

	int updateUserGrade(String userId) throws SQLException;

}

package dao;

import java.sql.SQLException;

import java.util.List;

import dto.Sales;
import dto.UserDto;

public interface AdminDao {
	/** 일일매출 */
	List<Sales> todaySales() throws SQLException;

	/** 총매출 */
	int totalSales() throws SQLException;

	/** 기간별매출 */
	int periodSales(String startdate, String enddate) throws SQLException;

	/**
	 * 회원 관리 - 회원 목록 보기
	 */

	List<UserDto> selectUserList() throws SQLException;

	/**
	 * 회원 관리 - 회원 등업
	 */

	int updateUserGrade(String grade, String userId) throws SQLException;

}

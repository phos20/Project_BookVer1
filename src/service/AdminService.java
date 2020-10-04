package service;

import java.sql.SQLException;
import java.util.List;

import dao.AdminDao;
import dao.AdminDaoImpl;
import dto.Sales;
import dto.UserDto;

public class AdminService {
	AdminDao adminDao = new AdminDaoImpl();

	/** 일일매출액 */
	public List<Sales> todaySales() throws Exception {
		List<Sales> list = adminDao.todaySales();
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

	/**회원 목록 보기*/
	public List<UserDto> selectUserList() throws Exception{
		List<UserDto> list = adminDao.selectUserList();
		if(list.size() == 0)
			throw new SQLException("가입된 회원 목록이 없습니다");
		return list;
	}

	/**회원 등업*/
	public int updateUserGrade(String grade,String userId) throws Exception{
		int result = adminDao.updateUserGrade(grade,userId);
		if(result == 0)
			throw new SQLException("등업 실패 하였습니다.");
		return result;
	}


}

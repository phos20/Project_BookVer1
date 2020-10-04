package service;

import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;
import dto.UserDto;
import view.MenuView;

public class UserService {
	UserDao userDao = new UserDaoImpl();
	MenuView menuView = new MenuView();

	/**
	 * 회원가입
	 */
	public int signup(UserDto userDto) throws Exception {
		int result = userDao.signUp(userDto);
		if (result == 0)
			throw new Exception("회원가입을 할 수 없습니다.");
		return result;

	}

	/**
	 * 회원정보 수정
	 */
	public int updateUserInfo(UserDto userDto) throws SQLException {
		int result = userDao.updateUserInfo(userDto);
		if (result == 0)
			throw new SQLException("수정되지 않았습니다");
		return result;
	}

	/**
	 * 회원정보 탈퇴
	 **/
	public int deleteUserInfo(UserDto userDto) throws SQLException {
		int result = userDao.deleteUserInfo(userDto);
		if (result == 0)
			throw new SQLException("탈퇴되지 않았습니다.");
		return result;
	}

	/**
	 * 포인트 검색
	 */
	public int getUserPoint(String userId) throws Exception {
		int point = userDao.getUserPoint(userId);
		return point;
	}

	/**
	 * 포인트 등록
	 */
	public int userPoint(String userId, int point) throws Exception {
		int result = userDao.userPoint(userId, point);
		if (result == 0)
			throw new Exception("포인트가 적립되지 않았습니다. ");
		return result;
	}

	/**
	 * 회원정보 조회
	 */
	public UserDto Login(String userId, String userPwd) throws SQLException {
		UserDto userDto = userDao.Login(userId, userPwd);
		if (userDto == null) {
			System.out.println("회원정보가 존재하지 않습니다");
			menuView.menu();
		}
		return userDto;
	}

	/** 결제 */
	public void Pay(double price, String userId) throws SQLException {
		double result = userDao.Pay(price, userId);
		if (result == 0)
			throw new SQLException("결제 불가");

	}

}

package service;

import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;
import dto.UserDto;

public class UserService {
	UserDao userDao = new UserDaoImpl();
	/**
	 * 회원정보 수정
	 * */
	public int updateUserInfo(UserDto userDto) throws SQLException {
		int result = userDao.updateUserInfo(userDto);
		if(result==0) throw new SQLException("수정되지 않았습니다");
		return result;
	}
	
	/**
	 *  회원정보 탈퇴
	 **/
	public int deleteUserInfo(UserDto userDto)throws SQLException {
		int result = userDao.deleteUserInfo(userDto);
		if(result==0) throw new SQLException("탈퇴되지 않았습니다.");
		return result;
	}
	
}

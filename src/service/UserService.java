package service;

import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;
import dto.UserDto;

public class UserService {
	UserDao userDao = new UserDaoImpl();
	/**
	 * ȸ������ ����
	 * */
	public int updateUserInfo(UserDto userDto) throws SQLException {
		int result = userDao.updateUserInfo(userDto);
		if(result==0) throw new SQLException("�������� �ʾҽ��ϴ�");
		return result;
	}
	
	/**
	 *  ȸ������ Ż��
	 **/
	public int deleteUserInfo(UserDto userDto)throws SQLException {
		int result = userDao.deleteUserInfo(userDto);
		if(result==0) throw new SQLException("Ż����� �ʾҽ��ϴ�.");
		return result;
	}
	
}

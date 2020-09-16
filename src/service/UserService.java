package service;

import java.sql.SQLException;


import dao.UserDao;
import dao.UserDaoImpl;
import dto.UserDto;

public class UserService {
	UserDao userDao = new UserDaoImpl();

	public UserDto Login(String userId, String userPwd)throws SQLException {
		 UserDto userDto = userDao.Login(userId, userPwd);
		 if(userDto==null)
			 throw new SQLException("ȸ�������� �������� �ʽ��ϴ�");
		 return userDto; 
		
	}

}

package service;

import dao.UserDao;
import dao.UserDaoImpl;
import dto.UserDto;

public class UserService {
	UserDao userDao = new UserDaoImpl();
	
	/**
	 * ȸ������
	 */
	public int signup(UserDto userDto) throws Exception {
		int result = userDao.signUp(userDto);
		if (result == 0) throw new Exception ("ȸ�������� �� �� �����ϴ�.");
		return result;
		
		
	}
}

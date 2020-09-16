package service;

import dao.UserDao;
import dao.UserDaoImpl;
import dto.UserDto;

public class UserService {
	UserDao userDao = new UserDaoImpl();
	
	/**
	 * 회원가입
	 */
	public int signup(UserDto userDto) throws Exception {
		int result = userDao.signUp(userDto);
		if (result == 0) throw new Exception ("회원가입을 할 수 없습니다.");
		return result;
		
		
	}
}

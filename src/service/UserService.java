package service;

import dao.UserDao;
import dao.UserDaoImpl;

public class UserService {
	UserDao userDao = new UserDaoImpl();
	
	public int userPoint(String userId, int point) throws Exception{
		int result = userDao.userPoint(userId, point);
		if(result==0) throw new Exception("포인트가 적립되지 않았습니다. ");
		return result;
	}

}

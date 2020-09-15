package dao;

import java.sql.SQLException;

import dto.UserDto;

public class UserDaoImpl implements UserDao {

	@Override
	public int signUp(UserDto userDto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDto login(String userId, String userPwd) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int userPoint(int point) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUserInfo(UserDto userDto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUserInfo(String userId, String UserPwd) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}

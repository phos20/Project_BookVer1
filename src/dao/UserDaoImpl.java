package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.UserDto;
import util.DbUtil;

public class UserDaoImpl implements UserDao {

	
	/**
	 * 회원가입
	 * */
	@Override
	public int signUp(UserDto userDto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
	
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("insert into userlist values(?, ?, ?, ?, 0, sysdate, 0, default)");
			
			ps.setString(1, userDto.getUserId());
			ps.setString(2, userDto.getUserPwd());
			ps.setString(3, userDto.getUserName());
			ps.setString(4, userDto.getUserPhone());
			
			result = ps.executeUpdate();

		}finally {
			
			DbUtil.close(con, ps, null);
		}
		
		return result;
		
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

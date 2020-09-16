package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.UserDto;
import util.DbUtil;

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
		Connection con= null;
		PreparedStatement ps = null;
		String sql="UPDATE USERLIST SET USER_PWD=?, USER_NAME=? ,USER_PHONE=? WHERE USER_ID=?";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userDto.getUserPwd());
			ps.setString(2, userDto.getUserName());
			ps.setString(3, userDto.getUserPhone());
			ps.setString(4, userDto.getUserId());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}


	@Override
	public int deleteUserInfo(UserDto userDto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from userlist where user_pwd=? and user_name=? and user_phone=?";
		
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userDto.getUserPwd());
			ps.setString(2, userDto.getUserName());
			ps.setString(3, userDto.getUserPhone());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps,null);
		}
		return result;
	}
}

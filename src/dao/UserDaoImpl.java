package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public UserDto Login(String userId, String userPwd) throws SQLException {
		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		String sql = "SELECT * FROM USERLIST WHERE USER_ID =? AND USER_PWD=?";
		UserDto userDto =null;
		
		try {
			con =DbUtil.getConnection();
			ps =con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userPwd);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				userDto = new UserDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
											rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getString(8));
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return userDto;
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

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BookDto;
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
	public int userPoint(String userId, int point) throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update userlist set user_point = ? where user_id = ?";
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, getUserPoint(userId)+point);
			ps.setString(2, userId);
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	 * userId에 해당하는 포인트 구하기 
	 */
	public int getUserPoint(String userId) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select user_point from userlist where user_id = ? ";
		int point = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				point = rs.getInt("user_Point");
			}
			
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return point;
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

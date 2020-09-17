package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	/**
	 * 포인트 등록
	 */
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
	@Override
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
		String sql = "delete userlist where user_pwd=? and user_name=? and user_phone=?";
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

	/**포인트 차감*/
	@Override
	public int Pay(int price, String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result =0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("update orders set total_amount= ? where user_id =?");
			ps.setInt(1, Culculation(con,userId,price));
			ps.setString(1, userId);
			result = ps.executeUpdate();
			
		}finally {
			DbUtil.close(con, ps, null);
		}
	
		return result;
	}

	/**차감 포인트 계산*/
	private int Culculation(Connection con, String userId, int price)throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs =null;
		int result =0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select total_amount from orders where user_id =?");
			ps.setString(1, userId);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				result = (rs.getInt(1)-price);
				
				if(result <0) throw new SQLException("포인트가 부족합니다");
						
			}
		}finally {
			DbUtil.close(null, ps, rs);
		}
	
		return result;
	}

	
}

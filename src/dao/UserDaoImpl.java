package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Orders;
import dto.Pay;
import dto.UserDto;
import util.DbUtil;

public class UserDaoImpl implements UserDao {

	/**
	 * 회원가입
	 */
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

		} finally {

			DbUtil.close(con, ps, null);
		}

		return result;

	}

	/** 로그인 */
	@Override
	public UserDto Login(String userId, String userPwd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM USERLIST WHERE USER_ID =? AND USER_PWD=?";
		UserDto userDto = null;

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userPwd);
			rs = ps.executeQuery();

			if (rs.next()) {
				userDto = new UserDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
			}

		} finally {
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

			ps.setInt(1, getUserPoint(userId) + point);
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
	public int getUserPoint(String userId) throws SQLException {

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

			if (rs.next()) {
				point = rs.getInt("user_Point");
			}

		} finally {
			DbUtil.close(con, ps, rs);
		}
		return point;
	}

	/** 회원정보수정 */
	@Override
	public int updateUserInfo(UserDto userDto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE USERLIST SET USER_PWD=?, USER_NAME=? ,USER_PHONE=? WHERE USER_ID=?";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userDto.getUserPwd());
			ps.setString(2, userDto.getUserName());
			ps.setString(3, userDto.getUserPhone());
			ps.setString(4, userDto.getUserId());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/** 회원탈퇴 */
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

		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/** 결제 */
	@Override
	public double Pay(double price, String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.getAutoCommit();
			ps = con.prepareStatement("update userlist set USER_POINT= ? where user_id =?");
			ps.setDouble(1, Culculation(con, userId, price));
			ps.setString(2, userId);
			result = ps.executeUpdate();

			if (result == 0) {
				con.rollback();
				throw new SQLException("결제 실패 - 포인트 차감 오류");
			} else { // 결제목록에서 삭제
				int re = deletePay(con, userId);
				if (re == 0) {
					con.rollback();
					throw new SQLException("결제 실패 -결제목록 삭제 오류");
				}

				// 결제후 누적포인트에 따른 등업
				int re2 = updateAutoGrade(con, userId);
				if (re2 == 0) {
					con.rollback();
					throw new SQLException("결제 실패 -자동등업 오류");
				}

				// 매출테이블 등록
				int re3 = updateSales(con, price);
				if (re3 == 0) {
					con.rollback();
					throw new SQLException("결제 실패 -매출등록 오류");
				}

			}

			con.commit();

		} finally {
			DbUtil.close(con, ps, null);
		}

		return result;
	}

	/** 매출테이블 등록 */
	private int updateSales(Connection con, double price) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into sales values( SALES_NO_SEQ.nextval,default,?) ";
		try {
			ps = con.prepareStatement(sql);
			ps.setDouble(1, price);

			result = ps.executeUpdate();

		} finally {
			DbUtil.close(null, ps, null);
		}
		return result;
	}

	/** 자동등업 */
	private int updateAutoGrade(Connection con, String userId) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "UPDATE USERLIST SET GRADE =(SELECT GRADE FROM USERGRADE "
				+ "WHERE USERLIST.USER_TOTAL BETWEEN LOWAMOUNT and HIGHAMOUNT) WHERE USER_ID =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);

			result = ps.executeUpdate();

		} finally {
			DbUtil.close(null, ps, null);
		}
		return result;

	}

	/** 차감 포인트 계산 */
	private double Culculation(Connection con, String userId, double price) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserDto userDto = null;
		double result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from userlist where user_id =?");
			ps.setString(1, userId);

			rs = ps.executeQuery();
			if (rs.next()) {
				userDto = new UserDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
			}
			result = userDto.getUserPoint() - price;
			if (result < 0)
				throw new SQLException("포인트가 부족합니다");
		} finally {
			DbUtil.close(null, ps, rs);
		}

		return result;
	}

	/** 결제시 결제테이블에 주문목록 삭제 */
	private int deletePay(Connection con, String userId) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete pay where user_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			result = ps.executeUpdate();

		} finally {
			DbUtil.close(null, ps, null);
		}
		return result;

	}

}

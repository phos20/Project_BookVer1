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
	 * ȸ������
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

	/** �α��� */
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
	 * ����Ʈ ���
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
	 * userId�� �ش��ϴ� ����Ʈ ���ϱ�
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

	/** ȸ���������� */
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

	/** ȸ��Ż�� */
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

	/** ���� */
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
				throw new SQLException("���� ���� - ����Ʈ ���� ����");
			} else { // ������Ͽ��� ����
				int re = deletePay(con, userId);
				if (re == 0) {
					con.rollback();
					throw new SQLException("���� ���� -������� ���� ����");
				}

				// ������ ��������Ʈ�� ���� ���
				int re2 = updateAutoGrade(con, userId);
				if (re2 == 0) {
					con.rollback();
					throw new SQLException("���� ���� -�ڵ���� ����");
				}

				// �������̺� ���
				int re3 = updateSales(con, price);
				if (re3 == 0) {
					con.rollback();
					throw new SQLException("���� ���� -������ ����");
				}

			}

			con.commit();

		} finally {
			DbUtil.close(con, ps, null);
		}

		return result;
	}

	/** �������̺� ��� */
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

	/** �ڵ���� */
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

	/** ���� ����Ʈ ��� */
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
				throw new SQLException("����Ʈ�� �����մϴ�");
		} finally {
			DbUtil.close(null, ps, rs);
		}

		return result;
	}

	/** ������ �������̺� �ֹ���� ���� */
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

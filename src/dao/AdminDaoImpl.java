package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Orders;
import util.DbUtil;


public class AdminDaoImpl implements AdminDao {
	
	/** 일일매출 */
	@Override
	public List<Orders> todaySales() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<Orders>();

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("Select*from orders where order_date = sysdate");
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Orders(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}

		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	/** 총매출 */
	@Override
	public int totalSales() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Orders or = null;
		int total = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("Select*from orders");
			rs = ps.executeQuery();

			while (rs.next()) {

				or = new Orders(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				total += or.getTotalAmount();
			}

		} finally {
			DbUtil.close(con, ps, rs);
		}
		return total;

	}

	/** 기간별 매출 */
	@Override
	public int periodSales(String startdate, String enddate) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Orders or = null;
		int total = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("Select*from orders where order_date between ? and ?");
			ps.setString(1, startdate);
			ps.setString(2, enddate);

			rs = ps.executeQuery();

			while (rs.next()) {

				or = new Orders(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				total += or.getTotalAmount();
			}

		} finally {
			DbUtil.close(con, ps, rs);
		}
		return total;
	}

}

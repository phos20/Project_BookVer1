package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RegBookDto;
import util.DbUtil;

public class RegBookDaoImpl implements RegBookDao {

	/**
	 * ���������� �˻�
	 */
	@Override
	public List<RegBookDto> selectRegBook() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from regbook";
		List<RegBookDto> list = new ArrayList<>();

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				RegBookDto regBookDto = new RegBookDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				list.add(regBookDto);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}

		return list;
	}

	@Override
	public int insertRegbook(RegBookDto wish) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO REGBOOK VALUES(REG_NO_SEQ.NEXTVAL,?,?,?,?,sysdate)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, wish.getRegName());
			ps.setString(2, wish.getRegWriter());
			ps.setString(3, wish.getRegPublisher());
			ps.setString(4, wish.getUserId());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

}

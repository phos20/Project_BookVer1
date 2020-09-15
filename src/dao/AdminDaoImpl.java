package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Orders;
import dto.UserDto;
import util.DbUtil;


public class AdminDaoImpl implements AdminDao {

	@Override
	public List<Orders> todaySales() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int totalSales() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int periodSales(String startdate, String enddate) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserDto> selectUserList(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateUserGrade(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	

}

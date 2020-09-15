package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Orders;

public interface AdminDao {
	/**일일매출*/
	List<Orders> todaySales()throws SQLException;
	/**총매출*/
	int totalSales()throws SQLException;
	/**기간별매출*/
	int periodSales(String startdate, String enddate)throws SQLException;

}

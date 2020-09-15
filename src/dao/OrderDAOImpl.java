package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Orders;



public class OrderDAOImpl implements OrderDAO {

	BooksDao booksDao = new BooksDaoImpl();

	@Override
	public int orderInsert(Orders orders) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

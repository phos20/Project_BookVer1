package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.BookDto;
import dto.OrderLine;
import dto.Orders;
import util.DbUtil;

public class OrderDAOImpl implements OrderDAO {
	

	BooksDao booksDao = new BooksDaoImpl();

	
}

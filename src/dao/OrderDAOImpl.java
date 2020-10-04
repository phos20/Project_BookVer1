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
import dto.Pay;
import dto.UserDto;
import util.DbUtil;

public class OrderDAOImpl implements OrderDAO {

	BooksDao booksDao = new BooksDaoImpl();

	/** �ֹ��ϱ� */
	@Override
	public int orderInsert(Orders orders) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO ORDERS(ORDER_ID, ORDER_DATE,USER_ID, ADDRESS, TOTAL_AMOUNT)"
				+ "  VALUES(ORDER_ID_SEQ.NEXTVAL ,sysdate,?,?,?)";
		int result = 0;
		try {

			con = DbUtil.getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(sql);
			ps.setString(1, orders.getUserId());
			ps.setString(2, orders.getAddress());
			ps.setDouble(3, totalAmountByGrade(con, orders));

			result = ps.executeUpdate();
			if (result == 0) {
				con.rollback();
				throw new SQLException("�ֹ� ����");
			} else {
				int re[] = orderLineInsert(con, orders); // �ֹ��� ����ϱ�
				for (int i : re) {
					if (i != Statement.SUCCESS_NO_INFO) {
						con.rollback();
						throw new SQLException("�ֹ� ���� - ���������� �ֹ������ �߰������ʾҽ��ϴ�");
					}
				}

				// �ֹ�������ŭ ����� �����ϱ�
				decrementStock(con, orders.getOrderLineList());

				// �ֹ��� �������̺� �߰�
				int re2 = Payment(con, orders, totalAmountByGrade(con, orders));
				if (re2 == 0) {
					con.rollback();
					throw new SQLException("�ֹ� ���� - ������Ͽ� �ֹ������ �߰������ʾҽ��ϴ�");
				}

				con.commit();

			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}

		return result;
	}

	/** �ֹ��ð������̺��� ��� �߰� */
	private int Payment(Connection con, Orders orders, double price) throws SQLException {
		PreparedStatement ps = null;
		int result;
		String sql = "insert into pay values(PAY_NO_SEQ.nextval,?,SYSDATE,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, orders.getUserId());
			ps.setString(2, orders.getAddress());
			ps.setDouble(3, Double.parseDouble(String.format("%.1f", price)));
			result = ps.executeUpdate();

		} finally {
			DbUtil.close(null, ps, null);
		}
		return result;

	}

	/** ��޺� �Ѿ� ���ϱ� */
	private double totalAmountByGrade(Connection con, Orders order) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		UserDto ud = null;
		double result = 0;
		String sql = "SELECT*FROM USERLIST WHERE USER_ID=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, order.getUserId());
			rs = ps.executeQuery();

			if (rs.next()) {
				ud = new UserDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));

				if (ud.getGrade().equals("silver")) {
					result = 0.95 * getTotalAmount(order);
				} else if (ud.getGrade().equals("gold")) {
					result = 0.9 * getTotalAmount(order);
				} else {
					result = getTotalAmount(order);
				}
			}

		} finally {
			DbUtil.close(null, ps, rs);
		}
		return result;

	}

	/** �Ѿ� */
	public double getTotalAmount(Orders order) throws SQLException {
		List<OrderLine> orderLineList = order.getOrderLineList();
		double total = 0;
		for (OrderLine line : orderLineList) {
			BookDto books = booksDao.booksSelectBybooksId(line.getBooksId());
			if (books == null)
				throw new SQLException("��ǰ��ȣ �����Դϴ�.... �ֹ� ����..");
			total += line.getQty() * books.getBooksPrice();
		}
		return total;
	}

	/** �ֹ��� */
	public int[] orderLineInsert(Connection con, Orders order) throws SQLException {

		PreparedStatement ps = null;
		String sql = "insert into order_line(order_line_id,order_id, books_id,unit_price, qty, amount)"
				+ "  values(ORDER_LINE_ID_SEQ.nextval ,ORDER_ID_SEQ.currval , ?,?,?,? )";
		int result[] = null;
		try {
			ps = con.prepareStatement(sql);
			for (OrderLine orderline : order.getOrderLineList()) {
				BookDto bookDto = booksDao.booksSelectBybooksId(orderline.getBooksId());

				ps.setString(1, orderline.getBooksId());
				ps.setInt(2, bookDto.getBooksPrice());// ����
				ps.setInt(3, orderline.getQty());// �ѱ��Ű���
				ps.setInt(4, bookDto.getBooksPrice() * orderline.getQty());// �ѱ��űݾ�
				ps.addBatch(); // �ϰ�ó���� �۾��� �߰�
				ps.clearParameters();

			}
			result = ps.executeBatch();// �ϰ�ó��

		} finally {
			DbUtil.close(null, ps, null);
		}

		return result;

	}

	/** ����� ���� */
	public void managementStock(Connection con, List<OrderLine> orderLineList) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select stock from books where books_id =?";
		BookDto bookDto = null;
		try {
			ps = con.prepareStatement(sql);
			for (OrderLine orderline : orderLineList) {
				ps.setString(1, orderline.getBooksId());

				rs = ps.executeQuery();
				if (rs.next()) {
					bookDto = new BookDto(rs.getInt(1));
					if (bookDto.getStock() < 0) {
						con.rollback();
						throw new SQLException("�ֹ� ���� - ����� �����մϴ�");
					}
				}

			}

		} finally {
			DbUtil.close(null, ps, null);
		}

	}

	/** ����� ���� */
	private int[] decrementStock(Connection con, List<OrderLine> orderLineList) throws SQLException {
		PreparedStatement ps = null;
		String sql = "update books set stock = stock-? where books_id=?";
		int result[] = null;
		try {
			ps = con.prepareStatement(sql);
			for (OrderLine orderline : orderLineList) {
				ps.setInt(1, orderline.getQty());
				ps.setString(2, orderline.getBooksId());

				ps.addBatch();
				ps.clearParameters();
			}

			result = ps.executeBatch();

			// ����� üũ
			managementStock(con, orderLineList);

		} finally {
			DbUtil.close(null, ps, null);
		}

		return result;

	}

	/** �ֹ����� */
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from orders where user_id=? order by order_date desc");
			ps.setString(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Orders orders = new Orders(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5));

				List<OrderLine> orderLineList = selectOrderLine(orders.getOrderId());
				orders.setOrderLineList(orderLineList);

				list.add(orders);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;

	}

	/** �ֹ���ȣ�� �ش��ϴ� �ֹ��� �������� */
	public List<OrderLine> selectOrderLine(int orderId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderLine> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from order_line where  order_id=?");
			ps.setInt(1, orderId);
			rs = ps.executeQuery();

			while (rs.next()) {
				OrderLine orderLine = new OrderLine(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getInt(6));
				list.add(orderLine);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;

	}

	/** �������ݰ˻� */
	@Override
	public List<Pay> ordersPriceByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from pay where USER_ID=?";
		List<Pay> list = new ArrayList<Pay>();

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Pay(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}

		} finally {
			DbUtil.close(null, ps, null);
		}
		return list;
	}

	/** �ֹ���� */
	@Override
	public int cancleOrders(String userId, int orderNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete orders where order_id=?";
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(sql);
			ps.setInt(1, orderNo);
			result = ps.executeUpdate();

			if (result == 0) {
				con.rollback();
				throw new SQLException("�ֹ���� ���� - �ֹ���ȣ ����");

			} else {
				// ������ϻ���
				canclePay(con, userId);
				// �ֹ��󼼻��� �� �ֹ����̺������� on delete cascade�� ���ξ���

				con.commit();
			}

		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/** �ֹ���ҽ� �������� ���� */
	private int canclePay(Connection con, String userId) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete pay where user_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			result = ps.executeUpdate();

			if (result == 0) {
				con.rollback();
				throw new SQLException("�ֹ���� ���� - ������Ͽ��� ���� �Ұ�");
			}

		} finally {
			DbUtil.close(null, ps, null);
		}

		return result;
	}

}
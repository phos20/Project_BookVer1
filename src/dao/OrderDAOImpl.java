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

	/** 주문하기 */
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
				throw new SQLException("주문 실패");
			} else {
				int re[] = orderLineInsert(con, orders); // 주문상세 등록하기
				for (int i : re) {
					if (i != Statement.SUCCESS_NO_INFO) {
						con.rollback();
						throw new SQLException("주문 실패 - 결제내역에 주문목록이 추가되지않았습니다");
					}
				}

				// 주문수량만큼 재고량 감소하기
				decrementStock(con, orders.getOrderLineList());

				// 주문시 결제테이블 추가
				int re2 = Payment(con, orders, totalAmountByGrade(con, orders));
				if (re2 == 0) {
					con.rollback();
					throw new SQLException("주문 실패 - 결제목록에 주문목록이 추가되지않았습니다");
				}

				con.commit();

			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}

		return result;
	}

	/** 주문시결제테이블에 목록 추가 */
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

	/** 등급별 총액 구하기 */
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

	/** 총액 */
	public double getTotalAmount(Orders order) throws SQLException {
		List<OrderLine> orderLineList = order.getOrderLineList();
		double total = 0;
		for (OrderLine line : orderLineList) {
			BookDto books = booksDao.booksSelectBybooksId(line.getBooksId());
			if (books == null)
				throw new SQLException("상품번호 오류입니다.... 주문 실패..");
			total += line.getQty() * books.getBooksPrice();
		}
		return total;
	}

	/** 주문상세 */
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
				ps.setInt(2, bookDto.getBooksPrice());// 가격
				ps.setInt(3, orderline.getQty());// 총구매개수
				ps.setInt(4, bookDto.getBooksPrice() * orderline.getQty());// 총구매금액
				ps.addBatch(); // 일괄처리할 작업에 추가
				ps.clearParameters();

			}
			result = ps.executeBatch();// 일괄처리

		} finally {
			DbUtil.close(null, ps, null);
		}

		return result;

	}

	/** 재고량 관리 */
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
						throw new SQLException("주문 실패 - 재고가 부족합니다");
					}
				}

			}

		} finally {
			DbUtil.close(null, ps, null);
		}

	}

	/** 재고량 감소 */
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

			// 재고량 체크
			managementStock(con, orderLineList);

		} finally {
			DbUtil.close(null, ps, null);
		}

		return result;

	}

	/** 주문내역 */
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

	/** 주문번호에 해당하는 주문상세 가져오기 */
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

	/** 결제가격검색 */
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

	/** 주문취소 */
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
				throw new SQLException("주문취소 오류 - 주문번호 오류");

			} else {
				// 결제목록삭제
				canclePay(con, userId);
				// 주문상세삭제 는 주문테이블삭제시 on delete cascade라서 따로안함

				con.commit();
			}

		} finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/** 주문취소시 결제내역 삭제 */
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
				throw new SQLException("주문취소 오류 - 결제목록에서 삭제 불가");
			}

		} finally {
			DbUtil.close(null, ps, null);
		}

		return result;
	}

}

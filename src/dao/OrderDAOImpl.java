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
			ps.setInt(3, totalAmountByGrade(con, orders));

			result = ps.executeUpdate();
			if (result == 0) {
				con.rollback();
				throw new SQLException("주문 실패");
			} else {
				int re[] = orderLineInsert(con, orders); // 주문상세 등록하기
				for (int i : re) {
					if (i != Statement.SUCCESS_NO_INFO) {
						con.rollback();
						throw new SQLException("주문 실패");
					}
				}

				// 주문수량만큼 재고량 감소하기
				decrementStock(con, orders.getOrderLineList());
				con.commit();
				//주문시 결제테이블 추가
				Payment(con,orders,totalAmountByGrade(con,orders));
			}

		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}

		return result;
	}
	
	/**주문시결제테이블에 목록 추가*/
	private int Payment(Connection con, Orders orders, int price)throws SQLException {
		PreparedStatement ps = null;
		int result;
		String sql = "insert into pay values(PAY_NO_SEQ.nextval,?,SYSDATE,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, orders.getUserId());
			ps.setString(2, orders.getAddress());
			ps.setInt(3, price);
			result= ps.executeUpdate();
			if(result ==0) throw new SQLException("결제목록에 주문목록이 추가되지않았습니다");
			
		}finally {
			DbUtil.close(null, ps, null);
		}
		return result;
		
		
	}

	// ps.setInt(3)
	private int totalAmountByGrade(Connection con, Orders order) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		UserDto ud = null;
		int result = 0;
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
					result = getsilverTotalAmount(order);
				} else if (ud.getGrade().equals("gold")) {
					result = getgoldTotalAmount(order);
				} else {
					result = getTotalAmount(order);
				}
			}

		} finally {
			DbUtil.close(null, ps, rs);
		}
		return result;

	}

	/** TOTAL AMOUNT */
	public int getTotalAmount(Orders order) throws SQLException {
		List<OrderLine> orderLineList = order.getOrderLineList();
		int total = 0;
		for (OrderLine line : orderLineList) {
			BookDto books = booksDao.booksSelectBybooksId(line.getBooksId());
			if (books == null)
				throw new SQLException("상품번호 오류입니다.... 주문 실패..");
			total += line.getQty() * books.getBooksPrice();
		}
		return total;
	}

	/** SILVER TOTAL AMOUNT */
	private int getsilverTotalAmount(Orders order) throws SQLException {
		List<OrderLine> orderLineList = order.getOrderLineList();
		int total = 0;
		for (OrderLine line : orderLineList) {
			BookDto books = booksDao.booksSelectBybooksId(line.getBooksId());
			if (books == null)
				throw new SQLException("상품번호 오류입니다.... 주문 실패..");
			total += 0.95 * (line.getQty() * books.getBooksPrice());
		}

		return total;
	}

	/** GOLD TOTAL AMOUNT */
	private int getgoldTotalAmount(Orders order) throws SQLException {
		List<OrderLine> orderLineList = order.getOrderLineList();
		int total = 0;
		for (OrderLine line : orderLineList) {
			BookDto books = booksDao.booksSelectBybooksId(line.getBooksId());
			if (books == null)
				throw new SQLException("상품번호 오류입니다.... 주문 실패..");
			total += 0.90 * (line.getQty() * books.getBooksPrice());
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
			System.out.println("주문완료");

		} finally {
			DbUtil.close(null, ps, null);
		}

		return result;

	}

	/** 재고관리 */
	public int[] decrementStock(Connection con, List<OrderLine> orderLineList) throws SQLException {
		PreparedStatement ps = null;
		String sql = "update books set stock = stock-? where books_id=?";
		int result[] = null;
		try {
			ps = con.prepareStatement(sql);
			for (OrderLine orderline : orderLineList) {
				ps.setInt(1, orderline.getQty());
				ps.setString(2, orderline.getBooksId());

				ps.addBatch(); // 일괄처리할 작업에 추가
				ps.clearParameters();
			}

			result = ps.executeBatch();// 일괄처리

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
			ps = con.prepareStatement("select * from orders where user_id=?");
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

	

}
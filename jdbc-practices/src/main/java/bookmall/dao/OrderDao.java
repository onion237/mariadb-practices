package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;
import util.ConnectionProvider;

public class OrderDao {
	private ConnectionProvider connectionProvider;

	public OrderDao(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public boolean insert(OrderVo order) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String selectSeq = "select seq from order_seq where order_date = ?";
		ResultSet rs = null;

		if (order == null || order.getOrderBookList() == null || order.getOrderBookList().size() == 0)
			return false;

		try {
			conn = connectionProvider.getConnection();
			conn.setAutoCommit(false);

			// 채번테이블 조회
			if (!updateOrderSeq(conn, order)) {
				System.out.println("채번테이블에서 해당 날짜에 해당하는 seq 새로 생성");
				String insertSeq = "insert into order_seq(order_date, seq) values(?,?)";

				pstmt = conn.prepareStatement(insertSeq);
				pstmt.setDate(1, order.getSqlDate());
				pstmt.setLong(2, 0L);
				pstmt.executeUpdate();

				updateOrderSeq(conn, order);
			}

			pstmt = conn.prepareStatement(selectSeq);
			pstmt.setDate(1, order.getSqlDate());
			rs = pstmt.executeQuery();

			if (!rs.next())
				throw new SQLException("seq획득 중 오류");
			String seq = "000000000000" + String.valueOf(rs.getLong("seq"));
			String orderNo = order.getOrderDate().toString().replace("-", "").substring(0, 8)
					+ seq.substring(seq.length() - 8);

			// 주문번호(order_no) 셋팅
			order.setOrderNo(orderNo);

			// payamount 계산/셋팅
			int sum = 0;
			for (OrderBookVo ob : order.getOrderBookList()) {
				sum += ob.getPrice() * ob.getQty();
			}
			order.setPayAmount(sum);

			// order insert 및 pk 획득
			String insertOrder = "insert into `order`(order_no, member_no, pay_amount, ship_addr, order_date) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, order.getOrderNo());
			pstmt.setLong(2, order.getMemberNo());
			pstmt.setInt(3, order.getPayAmount());
			pstmt.setString(4, order.getShipAddr());
			pstmt.setTimestamp(5, Timestamp.valueOf(order.getOrderDate()));

			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (!rs.next())
				throw new SQLException("order record 삽입 중 에러");

			Long orderPK = rs.getLong(1);
			String insertOrderBook = "insert into order_book(order_no, book_no, qty, price) values (?,?,?,?)";
			for (int i = 1; i < order.getOrderBookList().size(); i++) {
				insertOrderBook += ", (?,?,?,?)";
			}
			// orderBook레코드들 insert
			pstmt = conn.prepareStatement(insertOrderBook);
			int idx = 1;
			for (OrderBookVo ob : order.getOrderBookList()) {
				pstmt.setLong(idx++, orderPK);
				pstmt.setLong(idx++, ob.getBookNo());
				pstmt.setInt(idx++, ob.getQty());
				pstmt.setInt(idx++, ob.getPrice());
			}
			result = pstmt.executeUpdate() > order.getOrderBookList().size() - 1;
			if (result)
				conn.commit();
			else
				throw new SQLException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return result;
	}

	private boolean updateOrderSeq(Connection conn, OrderVo order) throws SQLException {
		String updateSeq = "update order_seq set seq = seq + 1 where order_date = ?";

		PreparedStatement pstmt = conn.prepareStatement(updateSeq);
		pstmt.setDate(1, order.getSqlDate());

		return pstmt.executeUpdate() > 0;
	}

	public Map<Long, Integer> getCurrentBookPrice(List<OrderBookVo> orderBookList) {
		Map<Long, Integer> result = new HashMap<>();

		if (orderBookList == null || orderBookList.size() == 0)
			return result;

		String sql = "select no, price from book where no IN (?";

		for (int i = 1; i < orderBookList.size(); i++) {
			sql += ",?";
		}

		sql += ")";

		try (Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			int idx = 1;
			for (OrderBookVo orderBook : orderBookList) {
				pstmt.setLong(idx++, orderBook.getBookNo());
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					result.put(rs.getLong("no"), rs.getInt("price"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public List<OrderVo> findAll() {
		List<OrderVo> result = new ArrayList<OrderVo>();
		String selectOrder = "select no, order_no, member_no, pay_amount, ship_addr, date_format(order_date,'%Y-%m-%d %H:%i:%s') as d, status from `order` order by no desc";
		String selectOrderBook = "select order_no, book_no, qty, price from order_book where order_no = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet orderSet = null;
		ResultSet orderBookSet = null;
		
		try {
			conn = connectionProvider.getConnection();
			pstmt = conn.prepareStatement(selectOrder);
			orderSet = pstmt.executeQuery();
			OrderVo order = null;

			while (orderSet.next()) {
				order = new OrderVo();
				order.setNo(orderSet.getLong("no"));
				order.setOrderNo(orderSet.getString("order_no"));
				order.setMemberNo(orderSet.getLong("member_no"));
				order.setPayAmount(orderSet.getInt("pay_amount"));
				order.setShipAddr(orderSet.getString("ship_addr"));
				order.setFormattedOrderDate(orderSet.getString("d"));
				order.setStatus(orderSet.getString("status"));

				List<OrderBookVo> obList = new ArrayList<>();

				pstmt = conn.prepareStatement(selectOrderBook);
				pstmt.setLong(1, order.getNo());
				orderBookSet = pstmt.executeQuery();
				
				while (orderBookSet.next()) {
					OrderBookVo ob = new OrderBookVo();
					ob.setOrderNo(orderBookSet.getLong("order_no"));
					ob.setBookNo(orderBookSet.getLong("book_no"));
					ob.setPrice(orderBookSet.getInt("price"));
					ob.setQty(orderBookSet.getInt("qty"));
					
					obList.add(ob);
				}
				order.setOrderBookList(obList);
				result.add(order);
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}

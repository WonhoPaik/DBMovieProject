package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.BookingVO;
import model.CustomerVO;
import model.MovieVO;

public class BookingDAO {
	// 예매
	public void setBooking(BookingVO bvo) throws Exception {
		String sql = "INSERT INTO booking (b_no, m_no, c_no, b_seat, b_count, b_price, b_date) "
				+ "SELECT booking_seq.nextval, ?, ?, ?, ?, ?, sysdate " + "FROM customer " + "WHERE c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bvo.getM_no());
			pstmt.setInt(2, bvo.getC_no());
			pstmt.setString(3, bvo.getB_seat());
			pstmt.setInt(4, bvo.getB_count());
			pstmt.setString(5, bvo.getB_price());
			pstmt.setInt(6, bvo.getC_no());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("예매 완료.");
			} else {
				System.out.println("예매 실패!!!");
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	// 좌석 중복 체크
	public boolean getSeatOverlap(String seatOverlap) throws Exception {
		String sql = "select * from booking where b_seat = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, seatOverlap);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true; // 중복된 좌석이 있다.
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}

	// 좌석 구간 설정
	public boolean isValidSeat(String seat) {
		char row = seat.charAt(0);
		int col = Integer.parseInt(seat.substring(1));
		return row >= 'A' && row <= 'F' && col >= 1 && col <= 13;
	}

	// 예매 취소 (개인용)
	public void setBookingDrop(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from booking where b_no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("예매취소 완료.");
			} else {
				System.out.println("예매취소 실패!!!");
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	// 예매내역 삭제(관리자용
	public void setBookingDelete(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from booking where b_no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("예매내역 삭제 완료.");
			} else {
				System.out.println("예매내역 삭제 실패!!!");
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	// 예매내역 (개인용)
	public void getBookingList(int c_no) throws Exception {
		String sql = "select b.b_no, c.c_name, m.m_name, m.m_date, m.m_room, b.b_seat, b.b_count, b.b_price, b.b_date "
				+ "FROM booking b " + "JOIN movie m ON b.m_no = m.m_no " + "JOIN customer c ON b.c_no = c.c_no "
				+ "where c.c_no = ? " + "order by b_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookingVO bVo = null;
		MovieVO mVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			rs = pstmt.executeQuery();
			System.out.println(String.format("%-6s", "예매번호") + String.format("%-8s", "예매자이름")
					+ String.format("%-13s", "영화제목") + String.format("%-12s", "상영날짜") + String.format("%-10s", "상영관")
					+ String.format("%-7s", "자리") + String.format("%-6s", "수량") + String.format("%-8s", "가격") + "예매날짜");
			while (rs.next()) {
				System.out.println(String.format("%-8s", rs.getString("b_no"))
						+ String.format("%-9s", rs.getString("c_name")) + String.format("%-13s", rs.getString("m_name"))
						+ String.format("%-14s", rs.getString("m_date"))
						+ String.format("%-11s", rs.getString("m_room")) + String.format("%-8s", rs.getString("b_seat"))
						+ String.format("%-7s", rs.getString("b_count"))
						+ String.format("%-10s", rs.getString("b_price")) + rs.getString("b_date"));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
	}

	// 예매내역 전체(관리자용)
	public void getBookingTotalList() throws Exception {
		String sql = "SELECT b.b_no, c.c_name, m.m_name, m.m_date, m.m_room, b.b_seat, b.b_count, b.b_price, b.b_date "
				+ "FROM booking b JOIN movie m ON b.m_no = m.m_no "
				+ "JOIN customer c ON b.c_no = c.c_no ORDER BY b.b_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookingVO bVo = null;
		MovieVO mVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(String.format("%-6s", "예매번호") + String.format("%-8s", "예매자이름")
					+ String.format("%-13s", "영화제목") + String.format("%-12s", "상영날짜") + String.format("%-10s", "상영관")
					+ String.format("%-7s", "자리") + String.format("%-6s", "수량") + String.format("%-8s", "가격") + "예매날짜");
			while (rs.next()) {
				System.out.println(String.format("%-8s", rs.getString("b_no"))
						+ String.format("%-9s", rs.getString("c_name")) + String.format("%-13s", rs.getString("m_name"))
						+ String.format("%-14s", rs.getString("m_date"))
						+ String.format("%-11s", rs.getString("m_room")) + String.format("%-8s", rs.getString("b_seat"))
						+ String.format("%-7s", rs.getString("b_count"))
						+ String.format("%-10s", rs.getString("b_price")) + rs.getString("b_date"));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
	}

	// 포인트적립
	public void setPointUpdate(CustomerVO cvo, BookingVO bvo) throws Exception {
		String sql = "update customer set c_point = c_point + ? * 0.1 where c_no=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		final double POINT_PCT = 0.1;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bvo.getB_price()));
			pstmt.setInt(2, bvo.getC_no());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(Integer.parseInt(bvo.getB_price()) * POINT_PCT + " 포인트 적립 완료");
			} else {
				System.out.println("포인트 적립 실패!!!");
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	// 포인트 조회
	public void getCustomerPoint(int no) throws Exception {
		String sql = "select c_point from customer where c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cVo = new CustomerVO();
				cVo.setC_point(rs.getInt("c_point"));
				System.out.println("현재 누적 포인트 : " + cVo.getC_point());
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	// 포인트 사용
	public void usePoints(CustomerVO cvo, BookingVO bvo) {
		String sql = "select c_point from customer where c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final int minPointsToUse = 500; // 최소 사용 가능한 포인트
		int pointsToUse = 0;
		int currentPoints = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bvo.getC_no());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				currentPoints = rs.getInt("c_point");
			}
			Scanner input = new Scanner(System.in);
			System.out.print("포인트를 사용하시겠습니까? (y/n): ");
			String answer = input.nextLine();
			if (answer.equalsIgnoreCase("y")) {
				while (true) {
					System.out.print("사용할 포인트를 입력하세요 (500 이상): ");
					pointsToUse = input.nextInt();
					if (pointsToUse >= minPointsToUse && pointsToUse <= currentPoints
							&& pointsToUse <= Integer.parseInt(bvo.getB_price())) {
						break;
					} else {
						if (pointsToUse < minPointsToUse || pointsToUse > currentPoints) {
							System.out.println("잘못된 포인트 입력입니다. 다시 입력하세요.");
						} else {
							System.out.println("포인트가 예매 금액을 초과합니다. 다시 입력하세요.");
						}
					}
				}
				// 포인트 차감
				cvo.setC_point(currentPoints - pointsToUse);
				System.out.println(pointsToUse + "포인트를 사용했습니다.");

				// b_price - c_point
				int bPrice = Integer.parseInt(bvo.getB_price());
				bvo.setB_price(String.valueOf(bPrice - pointsToUse));
			} else {
				System.out.println("포인트 사용이 취소되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

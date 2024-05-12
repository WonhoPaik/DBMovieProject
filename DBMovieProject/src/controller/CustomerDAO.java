package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BookingVO;
import model.CustomerVO;

public class CustomerDAO {
	// 회원 등록
	public static void setCustomerReg(CustomerVO cvo) throws Exception {
		String sql = "insert into customer values (customer_seq.nextval, ?, ?, ?, ?, ?, ?, ?, 0)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getC_name());
			pstmt.setString(2, cvo.getC_bday());
			pstmt.setString(3, cvo.getC_email());
			pstmt.setString(4, cvo.getC_addr());
			pstmt.setString(5, cvo.getC_phone());
			pstmt.setString(6, cvo.getC_id());
			pstmt.setString(7, cvo.getC_pw());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(cvo.getC_name() + "님 회원 등록 완료.");
				System.out.println("회원 등록 성공!!!");
			} else {
				System.out.println("회원 등록 실패!!!");
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

	// 회원 정보 수정
	public void setCustomerUpdate(CustomerVO cvo) throws Exception {
		String sql = "update customer set c_email=?, c_addr=?, c_phone=? where c_pw=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getC_email());
			pstmt.setString(2, cvo.getC_addr());
			pstmt.setString(3, cvo.getC_phone());
			pstmt.setString(4, cvo.getC_pw());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("회원 정보 수정 성공!!!");
			} else {
				System.out.println("회원 정보 수정 실패!!!");
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

	// 회원 정보 수정 (관리자
	public void setAdminCustomerUpdate(CustomerVO cvo) throws Exception {
		String sql = "update customer set c_email=?, c_addr=?, c_phone=?,c_pw =?, c_id = ?,c_bday =? , c_name = ? where = c_no?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getC_email());
			pstmt.setString(2, cvo.getC_addr());
			pstmt.setString(3, cvo.getC_phone());
			pstmt.setString(4, cvo.getC_pw());
			pstmt.setString(5, cvo.getC_id());
			pstmt.setString(6, cvo.getC_bday());
			pstmt.setString(7, cvo.getC_name());
			pstmt.setInt(8, cvo.getC_no());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("회원 정보 수정 성공!!!");
			} else {
				System.out.println("회원 정보 수정 실패!!!");
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

	// 회원 삭제
	public boolean setCustomerDelete(String bday, String pw) throws Exception {
		String sql = " delete from customer where c_bday = ? and c_pw = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bday);
			pstmt.setString(2, pw);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("회원 탈퇴완료.");
				flag = true;
			} else {
				System.out.println("회원 탈퇴 실패!!!");
				flag = false;
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
		return flag;
	}

	// 회원 삭제(관리자)
	public boolean setAdminCustomerDelete(String no) throws Exception {
		String sql = " delete from customer where c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("회원 삭제완료.");
				flag = true;
			} else {
				System.out.println("회원 삭제 실패!!!");
				flag = false;
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
		return flag;
	}

	// 회원 아이디 중복 체크
	public boolean getCustomerIdOverlap(String idOverlap) throws Exception {
		String sql = "select * from customer where c_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean idOverlapResult = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idOverlap);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idOverlapResult = true; // 중복된 아이디가 있다.
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
		return idOverlapResult;
	}

	// 회원 로그인
	public boolean getCustomerLogin(String id, String pw) throws Exception {
		String sql = "select * from customer where c_id = ? and c_pw = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginSuccess = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginSuccess = true; // 로그인 성공
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
		return loginSuccess;
	}

	// 회원 비밀번호 확인
	public boolean getCustomerCheck(String pw) throws Exception {
		String sql = "select * from customer where c_pw = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginSuccess = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginSuccess = true; // 로그인 성공
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
		return loginSuccess;
	}

	// 회원 번호
	public String getCustomerNum(String id, String pw) throws Exception {
		String sql = "select c_no from customer where c_id = ? and c_pw = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String c_no = "";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				c_no = rs.getString("c_no");
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
		return c_no;
	}

	// 회원 번호 (예매용)
	public String getCustomerNumber(String pw) throws Exception {
		String sql = "select c_no from customer where c_pw = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String c_no = "";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				c_no = rs.getString("c_no");
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
		return c_no;
	}

	// 회원 정보
	public void getCustomer(String id, String pw) throws Exception {
		String sql = "select * from customer where c_id = ? and c_pw = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			System.out.println("회원번호\t성명\t생년월일\t\t이메일\t\t\t주소\t\t전화번호\t\t");
			while (rs.next()) {
				cVo = new CustomerVO();
				cVo.setC_no(rs.getInt("c_no"));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_bday(rs.getString("c_bday"));
				cVo.setC_email(rs.getString("c_email"));
				cVo.setC_addr(rs.getString("c_addr"));
				cVo.setC_phone(rs.getString("c_phone"));
				cVo.setC_id(rs.getString("c_id"));
				cVo.setC_pw(rs.getString("c_pw"));
				cVo.setC_point(rs.getInt("c_point"));
				System.out.println(cVo.getC_no() + "\t" + cVo.getC_name() + "\t" + cVo.getC_bday() + "\t"
						+ cVo.getC_email() + "\t\t" + cVo.getC_addr() + "\t\t" + cVo.getC_phone() + "\t\t");
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

	// 회원 정보 출력(관리자)
	public void getAdminCustomer(int no) throws Exception {
		String sql = "select * from customer where c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			System.out.println("회원번호\t성명\t생년월일\t\t이메일\t\t\t주소\t\t전화번호\t\t아이디\t\t비밀번호\t\t잔여포인트");
			while (rs.next()) {
				cVo = new CustomerVO();
				cVo.setC_no(rs.getInt("c_no"));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_bday(rs.getString("c_bday"));
				cVo.setC_email(rs.getString("c_email"));
				cVo.setC_addr(rs.getString("c_addr"));
				cVo.setC_phone(rs.getString("c_phone"));
				cVo.setC_id(rs.getString("c_id"));
				cVo.setC_pw(rs.getString("c_pw"));
				cVo.setC_point(rs.getInt("c_point"));
				System.out.println(cVo.getC_no() + "\t" + cVo.getC_name() + "\t" + cVo.getC_bday() + "\t"
						+ cVo.getC_email() + "\t\t" + cVo.getC_addr() + "\t\t" + cVo.getC_phone() + "\t" + cVo.getC_id()
						+ "\t\t" + cVo.getC_pw() + "\t\t" + cVo.getC_point());
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

	// 학생 전체 목록
	public void getCustomerTotalList() throws Exception {
		String sql = "select * from customer order by c_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("회원번호\t성명\t생년월일\t\t이메일\t\t\t주소\t\t전화번호\t\t아이디\t\t비밀번호\t\t잔여포인트");
			while (rs.next()) {
				cVo = new CustomerVO();
				cVo.setC_no(rs.getInt("c_no"));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_bday(rs.getString("c_bday"));
				cVo.setC_email(rs.getString("c_email"));
				cVo.setC_addr(rs.getString("c_addr"));
				cVo.setC_phone(rs.getString("c_phone"));
				cVo.setC_id(rs.getString("c_id"));
				cVo.setC_pw(rs.getString("c_pw"));
				cVo.setC_point(rs.getInt("c_point"));
				System.out.println(cVo.getC_no() + "\t" + cVo.getC_name() + "\t" + cVo.getC_bday() + "\t"
						+ cVo.getC_email() + "\t\t" + cVo.getC_addr() + "\t\t" + cVo.getC_phone() + "\t" + cVo.getC_id()
						+ "\t\t" + cVo.getC_pw() + "\t\t" + cVo.getC_point());
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
	// 포인트 조회
		public void getPoint(int no) throws Exception {
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
	

}

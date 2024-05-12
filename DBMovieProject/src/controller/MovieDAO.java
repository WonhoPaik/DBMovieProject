package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.MovieVO;

public class MovieDAO {
	// 영화 목록
	public void getMovieTotalList() throws Exception {
		String sql = "select * from movie order by m_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieVO mVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("영화번호\t영화제목\t감독\t장르\t개봉일\t\t상영관\t상영날짜\t\t영화줄거리");
			while (rs.next()) {
				mVo = new MovieVO();
				mVo.setM_no(rs.getInt("m_no"));
				mVo.setM_name(rs.getString("m_name"));
				mVo.setM_director(rs.getString("m_director"));
				mVo.setM_genre(rs.getString("m_genre"));
				mVo.setM_relDate(rs.getString("m_relDate"));
				mVo.setM_room(rs.getString("m_room"));
				mVo.setM_date(rs.getString("m_date"));
				mVo.setM_info(rs.getString("m_info"));
				System.out.println(mVo.getM_no() + "\t" + mVo.getM_name() + "\t" + mVo.getM_director() + "\t"
						+ mVo.getM_genre() + "\t" + mVo.getM_relDate() + "\t" + mVo.getM_room() + "\t" + mVo.getM_date()
						+ "\t" + mVo.getM_info());
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

	// 영화 등록
	public void setMovieReg(MovieVO mvo) throws Exception {
		String sql = "insert into Movie values (movie_seq.nextval, ?, ?, ?, ?, ?, ?, 0,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mvo.getM_name());
			pstmt.setString(2, mvo.getM_director());
			pstmt.setString(3, mvo.getM_genre());
			pstmt.setString(4, mvo.getM_relDate());
			pstmt.setString(5, mvo.getM_room());
			pstmt.setString(6, mvo.getM_date());
			pstmt.setString(7, mvo.getM_info());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(mvo.getM_name() + " 영화 등록 완료.");
			} else {
				System.out.println("영화 등록 실패!!!");
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
	// 영화 수정
		public boolean setMovieUpdate(MovieVO mvo) throws Exception {
			String sql = "update movie set m_name=?, m_director=?, m_genre=?, m_relDate=?, m_room=?, m_date=?, m_info=? where m_no=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			boolean movieUpdateSuccess = false;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mvo.getM_name());
				pstmt.setString(2, mvo.getM_director());
				pstmt.setString(3, mvo.getM_genre());
				pstmt.setString(4, mvo.getM_relDate());
				pstmt.setString(5, mvo.getM_room());
				pstmt.setString(6, mvo.getM_date());
				pstmt.setString(7, mvo.getM_info());
				pstmt.setInt(8, mvo.getM_no());
				int i = pstmt.executeUpdate();
				if (i == 1) {
					System.out.println(mvo.getM_name() + " 영화 수정 완료.");
				} else {
					System.out.println("영화 수정 실패!!!");
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
			return movieUpdateSuccess;
		}
		// 영화 번호 (예매용)
		public String getMovieNumber(String name) throws Exception {
			String sql = "select m_no from movie where m_name = ?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String m_no = "";
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					m_no = rs.getString("m_no");
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
			return m_no;
		}
		// 영화 삭제
		public void setMovieDelete(int no) throws Exception {
			StringBuffer sql = new StringBuffer();
			sql.append("delete from movie where m_no = ?");
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, no);
				int i = pstmt.executeUpdate();
				if (i == 1) {
					System.out.println("영화 삭제 완료.");
				} else {
					System.out.println("영화 삭제 실패!!!");
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
}

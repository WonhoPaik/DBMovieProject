package controller;

import java.util.Scanner;

import model.BookingVO;
import model.CustomerVO;
import model.MovieVO;

public class BookingManager {
	// 예매하기
	public void booking() throws Exception {
		final String ADULT = "10000";
		final String KID = "5000";
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		BookingDAO bd = new BookingDAO();
		BookingVO bvo = new BookingVO();
		MovieDAO md = new MovieDAO();
		MovieVO mvo = new MovieVO();
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = new CustomerVO();
		int c_no; // 고객번호
		int m_no; // 영화번호
		String c_pw; // 고객 비밀번호
		String m_name; // 영화제목
		String b_seat; // 자리
		int b_count; // 수량
		int c_point; // 고객포인트
		String mainMenu;// 메인메뉴
		boolean success = false;
		System.out.println("비밀번호 확인");
		do {
			System.out.print("비밀번호 : ");
			c_pw = input.nextLine();
			success = cdao.getCustomerCheck(c_pw);
			if (!success) {
				System.out.println("비밀번호가 틀렸습니다.");
				System.out.print("메인 메뉴로 이동(y/n) : ");
				mainMenu = input1.next();
				if (mainMenu.equals("y") || mainMenu.equals("Y")) {
					return;
				}
				System.out.println();
			}
		} while (!success);
		System.out.println();
		System.out.print("예매할 수량을 입력해주세요 : ");
		b_count = input.nextInt();
		System.out.println("영화 리스트");
		md.getMovieTotalList();
		input.nextLine();
		c_no = Integer.parseInt(cdao.getCustomerNumber(c_pw));
		for (int i = 0; i < b_count; i++) {
			System.out.println();
			System.out.println("10000원\t| 5000원");
			System.out.print("성인\t| 청소년 : ");
			String age = input.nextLine();
			// 입력받은 연령따라 가격 설정
			if (age.equals("성인")) {
				bvo.setB_price(ADULT);
			} else if (age.equals("청소년")) {
				bvo.setB_price(KID);
			} else {
				System.out.println("제대로 입력해주세요");
			}

			System.out.println();
			System.out.print("예매할 영화제목 : ");
			m_name = input.nextLine();
			m_no = Integer.parseInt(md.getMovieNumber(m_name));
			System.out.println("A(01-13) ~ F(01-13) 사이만 선택 가능합니다.");
			do {
				System.out.print("좌석 번호를 입력하세요 (예: A01, A02): ");
				b_seat = input.nextLine().toUpperCase();
				if (!bd.getSeatOverlap(b_seat)) { // 좌석 중복인지 확인
					if (bd.isValidSeat(b_seat)) { // 좌석 구간에 들어갔는지 확인
						bvo.setB_seat(b_seat); // 좌석 예약
						bvo.setB_count(1);
						break; 
					} else {
						System.out.println("잘못된 좌석입니다. 다시 선택하세요.");
					}
				} else {
					System.out.println(b_seat + " 번 좌석은 이미 예약되었습니다. 다른 좌석을 선택하세요.");
					i--; 
					continue;
				}
			} while (true);
			bvo.setC_no(c_no);
			bvo.setM_no(m_no);
			bd.getCustomerPoint(c_no);
			bd.usePoints(cvo, bvo);
	        bd.setPointUpdate(cvo, bvo);
	        bd.setBooking(bvo);
		}
		System.out.println();
		System.out.println("내 예매내역");
		bd.getBookingList(c_no);
		System.out.println();
	}

	// 예매내역 조회 (개인)
	public void bookingList() throws Exception {
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		BookingDAO bd = new BookingDAO();
		BookingVO bvo = new BookingVO();
		MovieDAO md = new MovieDAO();
		MovieVO mvo = new MovieVO();
		CustomerDAO cdao = new CustomerDAO();
		int c_no; // 고객번호
		String c_pw; // 고객 비밀번호
		String mainMenu;// 메인메뉴
		boolean success = false;
		System.out.println("비밀번호 확인");
		do {
			System.out.print("비밀번호 : ");
			c_pw = input.nextLine();
			success = cdao.getCustomerCheck(c_pw);
			if (!success) {
				System.out.println("비밀번호가 틀렸습니다.");
				System.out.print("메인 메뉴로 이동(y/n) : ");
				mainMenu = input1.next();
				if (mainMenu.equals("y") || mainMenu.equals("Y")) {
					return;
				}
				System.out.println();
			}
		} while (!success);
		System.out.println();
		c_no = Integer.parseInt(cdao.getCustomerNumber(c_pw));
		System.out.println();
		System.out.println("내 예매내역");
		bd.getBookingList(c_no);
		System.out.println();
	}

	// 예매내역 조회(관리자)
	public void bookingAdminList() throws Exception {
		BookingDAO bd = new BookingDAO();
		System.out.println();
		System.out.println("전체 예매 내역");
		bd.getBookingTotalList();
		System.out.println();
	}

	// 예매 취소 (개인)
	public void bookingDelete() throws Exception {
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		BookingDAO bd = new BookingDAO();
		CustomerDAO cdao = new CustomerDAO();
		int c_no; // 고객번호
		int b_no; // 예매번호
		String c_pw; // 비밀번호
		String mainMenu; // 메인 메뉴
		boolean success = false;
		System.out.println("예매취소를 위해 비밀번호를 입력해주세요");
		do {
			System.out.print("비밀번호 : ");
			c_pw = input.nextLine();
			success = cdao.getCustomerCheck(c_pw);
			if (!success) {
				System.out.println("비밀번호가 틀림 다시입력");
				System.out.print("메인 메뉴로 이동(y/n) : ");
				mainMenu = input1.next();
				if (mainMenu.equals("y") || mainMenu.equals("Y")) {
					return;
				}
				System.out.println();
			}
		} while (!success);
		c_no = Integer.parseInt(cdao.getCustomerNumber(c_pw));
		System.out.println();
		System.out.println("내 예매내역");
		bd.getBookingList(c_no);
		System.out.println();
		System.out.println("취소할 예매번호 입력");
		System.out.print("예매번호 : ");
		b_no = input.nextInt();
		bd.setBookingDrop(b_no);
		System.out.println();
		System.out.println("예매취소 후 리스트");
		bd.getBookingList(c_no);
		System.out.println();
	}

	// 예매 취소 (관리자)
	public void bookingDeleteAdmin() throws Exception {
		Scanner input = new Scanner(System.in);
		BookingDAO bd = new BookingDAO();
		int b_no; // 예매번호
		System.out.println();
		System.out.println("전체 예매내역");
		bd.getBookingTotalList();
		System.out.println();
		System.out.println("삭제할 예매번호 입력");
		System.out.print("예매번호 : ");
		b_no = input.nextInt();
		bd.setBookingDelete(b_no);
		System.out.println();
		System.out.println("예매내역 삭제 후 리스트");
		bd.getBookingTotalList();
		System.out.println();
	}

}

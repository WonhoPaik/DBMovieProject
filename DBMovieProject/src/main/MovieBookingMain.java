package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import controller.BookingDAO;
import controller.BookingManager;
import controller.CustomerManager;
import controller.DBUtil;
import controller.MovieManager;
import view.ADMIN_ACCOUNT_MENU;
import view.ADMIN_BOOKING_MENU;
import view.ADMIN_MENU_CHOICE;
import view.ADMIN_MOVIE_MENU;
import view.CUSTOMER_ACCOUNT_MENU;
import view.CUSTOMER_BOOKING_MENU;
import view.CUSTOMER_MENU_CHOICE;
import view.MAINMENU_CHOICE;
import view.MenuViewer;

public class MovieBookingMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		mainMenu();
	}

	public static void mainMenu() {
		int selectNum;
		while (true) {
			try {
				MenuViewer.mainMenuView();
				selectNum = MenuViewer.choice.nextInt();
				MenuViewer.choice.nextLine();
				switch (selectNum) {
				case MAINMENU_CHOICE.REGISTER:
					registerMenu();
					break;
				case MAINMENU_CHOICE.LOGIN:
					loginMenu();
					break;
				case MAINMENU_CHOICE.ADMIN:
					adminMenu();
					break;
				case MAINMENU_CHOICE.EXIT:
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");

				return;
			}
		}
	}

	// 회원가입 메뉴
	public static void registerMenu() {
		try {
			CustomerManager.customerReg();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 로그인 메뉴
	public static void loginMenu() {
		System.out.println("아이디와 비밀번호를 입력하세요");

		System.out.print("아이디 : ");
		String c_id = sc.nextLine();

		System.out.print("패스워드 : ");
		String c_pd = sc.nextLine();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select c_name from customer where c_id = ? and c_pw = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_id);
			pstmt.setString(2, c_pd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("c_name");
				System.out.println(name + "님 환영합니다.");
				int selectNum;
				while (true) {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, c_id);
					pstmt.setString(2, c_pd);
					rs = pstmt.executeQuery();
					if (!rs.next())
						return;
					try {
						MenuViewer.customerMenuView();
						selectNum = MenuViewer.choice.nextInt();
						MenuViewer.choice.nextLine();
						switch (selectNum) {
						// 예매하기
						case CUSTOMER_MENU_CHOICE.BOOKING:
							bookingMenu();
							break;
						// 예매내역 조회/ 취소
						case CUSTOMER_MENU_CHOICE.BOOKING_LIST:
							bookingListMenu();
							break;
						// 회원정보 수정/ 탈퇴
						case CUSTOMER_MENU_CHOICE.ACCOUNT:
							accountMenu();
							break;
						case CUSTOMER_MENU_CHOICE.EXIT:
							System.out.println("프로그램을 종료합니다.");
							return;
						default:
							System.out.println("해당 메뉴 번호만 입력하세요.");
						}
					} catch (Exception e) {
						System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");

						return;
					}

				} // end of while
			} else {
				System.out.println("로그인에 실패했습니다.");
				System.out.println("종료합니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("rs, pstmt, con close error");
				e.printStackTrace();
			}
		}

	}

	// 예매하기
	public static void bookingMenu() {
		BookingManager BM = new BookingManager();
		try {
			BM.booking();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 예매내역 조회/ 취소 
	public static void bookingListMenu() {
		int choice;
		BookingManager BM = new BookingManager();
		MenuViewer.bookingMenuView();
		choice = MenuViewer.choice.nextInt();
		MenuViewer.choice.nextLine();
		try {
			switch (choice) {
			case CUSTOMER_BOOKING_MENU.BOOKINGLIST:
				System.out.println("");
				BM.bookingList();
				break;
			case CUSTOMER_BOOKING_MENU.CANCEL:
				System.out.println("");
				BM.bookingDelete();
				break;
			case CUSTOMER_BOOKING_MENU.MENU:
				return;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원정보 수정/탈퇴/포인트조회
	public static void accountMenu() {
		int choice;
		CustomerManager CM = new CustomerManager();
		MenuViewer.accountMenuView();
		choice = MenuViewer.choice.nextInt();
		MenuViewer.choice.nextLine();
		try {
			switch (choice) {
			case CUSTOMER_ACCOUNT_MENU.MODIFY:
				System.out.println("");
				CM.customerUpdate();
				break;
			case CUSTOMER_ACCOUNT_MENU.DROP:
				System.out.println("");
				CM.customerDrop();
				break;
			case CUSTOMER_ACCOUNT_MENU.POINT:
				System.out.println("");
				CM.getCustomerPoint();
				break;
			case CUSTOMER_ACCOUNT_MENU.MENU:
				return;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 관리자 메뉴
	public static void adminMenu() {
		System.out.println("관리자 정보를 입력하세요");
		System.out.print("아이디 : ");
		String adminId = sc.nextLine();

		System.out.print("패스워드 : ");
		String adminPW = sc.nextLine();

		// 어드민정보 파일불러오기

		String filePath = "C:/Users/user/eclipse-workspace/dbhomework/src/kh/student/admin.properties";
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(filePath));
			String _adminid = properties.getProperty("id");
			String _adminpw = properties.getProperty("pw");

			if (adminId.equals(_adminid) && adminPW.equals(_adminpw)) {
				System.out.println("로그인 성공");
				int selectNum;
				while (true) {
					try {
						MenuViewer.adminMenuView();
						selectNum = MenuViewer.choice.nextInt();
						MenuViewer.choice.nextLine();
						switch (selectNum) {
						case ADMIN_MENU_CHOICE.MOVIE:
							adminMovieMenu();
							break;
						case ADMIN_MENU_CHOICE.ACCOUNT:
							adminAccountMenu();
							break;
						case ADMIN_MENU_CHOICE.BOOKING:
							adminBookingMenu();
							break;
						case ADMIN_MENU_CHOICE.EXIT:
							System.out.println("프로그램을 종료합니다.");
							return;
						default:
							System.out.println("해당 메뉴 번호만 입력하세요.");
						}
					} catch (Exception e) {
						System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");

						return;
					}

				} // end of while
			} else {
				System.out.println("관리자 로그인 실패");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 예매관리(관리자)
	public static void adminBookingMenu() {
		int choice;
		BookingManager BM = new BookingManager();
		MenuViewer.adminBookingMenuView();
		choice = MenuViewer.choice.nextInt();
		MenuViewer.choice.nextLine();
		try {
			switch (choice) {
			case ADMIN_BOOKING_MENU.LIST:
				System.out.println("");
				BM.bookingAdminList();
				break;
			case ADMIN_BOOKING_MENU.DELETE:
				System.out.println("");
				BM.bookingDeleteAdmin();;
				break;
			case ADMIN_BOOKING_MENU.MENU:
				return;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원관리
	public static void adminAccountMenu() {
		int choice;
		CustomerManager cM = new CustomerManager();
		MenuViewer.adminAccountMenuView();
		choice = MenuViewer.choice.nextInt();
		MenuViewer.choice.nextLine();
		try {
			switch (choice) {
			case ADMIN_ACCOUNT_MENU.LIST:
				System.out.println("");
				cM.customerTotalList();
				break;
			case ADMIN_ACCOUNT_MENU.INSERT:
				System.out.println("");
				registerMenu();
				break;
			case ADMIN_ACCOUNT_MENU.MODIFY:
				System.out.println("");
				cM.customerAdminUpdate();
				break;
			case ADMIN_ACCOUNT_MENU.DELETE:
				System.out.println("");
				cM.customerDelete();
				break;
			case ADMIN_ACCOUNT_MENU.MENU:
				return;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 영화관리
	public static void adminMovieMenu() {
		int choice;
		MovieManager mM = new MovieManager();
		MenuViewer.adminMovieMenuView();
		choice = MenuViewer.choice.nextInt();
		MenuViewer.choice.nextLine();
		try {
			switch (choice) {
			case ADMIN_MOVIE_MENU.LIST:
				System.out.println("");
				mM.movieList();
				break;
			case ADMIN_MOVIE_MENU.INSERT:
				System.out.println("");
				mM.movieReg();
				break;
			case ADMIN_MOVIE_MENU.MODIFY:
				System.out.println("");
				mM.movieUpdate();
				break;
			case ADMIN_MOVIE_MENU.DELETE:
				System.out.println("");
				mM.movieDelete();
				break;
			case ADMIN_MOVIE_MENU.MENU:
				return;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

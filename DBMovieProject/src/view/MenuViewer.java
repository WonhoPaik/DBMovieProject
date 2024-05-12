package view;

import java.util.Scanner;

public class MenuViewer {
	public static Scanner choice = new Scanner(System.in);

	// 메인메뉴
	public static void mainMenuView() {
		System.out.println();
		System.out.println("메뉴를 선택해주세요");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 관리자모드");
		System.out.println("4. 프로그램 종료");
		System.out.print("번호 선택 : ");
	}
	// 회원메뉴
	public static void customerMenuView() {
		System.out.println();
		System.out.println("영화 예매");
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 영화 예매하기");
		System.out.println("2. 예매내역 확인/취소");
		System.out.println("3. 회원정보 수정/탈퇴/포인트조회");
		System.out.println("4. 초기화면으로");
		System.out.print("번호 선택 : ");
	}

	// 예매내역
	public static void bookingMenuView() {
		System.out.println();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 예매내역 확인");
		System.out.println("2. 예매취소");
		System.out.println("3. 메인메뉴");
		System.out.print("번호 선택 : ");
	}

	// 회원정보
	public static void accountMenuView() {
		System.out.println();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 회원정보 수정");
		System.out.println("2. 회원탈퇴 (현재 예매중인경우 탈퇴불가능)");
		System.out.println("3. 포인트 조회");
		System.out.println("4. 메인메뉴");
		System.out.print("번호 선택 : ");
	}

	// 관리자메뉴
	public static void adminMenuView() {
		System.out.println();
		System.out.println("영화 관리자모드");
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 영화 정보 목록/입력/수정/삭제");
		System.out.println("2. 회원 정보 목록/입력/수정/삭제");
		System.out.println("3. 예매 정보 목록/삭제");
		System.out.println("4. 초기화면으로");
		System.out.print("번호 선택 : ");
	}

	// 영화 정보 메뉴
	public static void adminMovieMenuView() {
		System.out.println();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 영화 목록");
		System.out.println("2. 신규 영화 등록");
		System.out.println("3. 영화 정보 수정");
		System.out.println("4. 영화 삭제(현재 예매내역이 있는경우 삭제불가능)");
		System.out.println("5. 메인 메뉴");
		System.out.print("번호 선택 : ");
	}
	// 회원 정보 메뉴
	public static void adminAccountMenuView() {
		System.out.println();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 회원 목록");
		System.out.println("2. 신규 회원 등록");
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원 삭제(현재 예매내역이 있는경우 삭제불가능)");
		System.out.println("5. 메인 메뉴");
		System.out.print("번호 선택 : ");
	}
	// 예매 정보 메뉴
	public static void adminBookingMenuView() {
		System.out.println();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 예매 목록");
		System.out.println("2. 예매내역 삭제");
		System.out.println("3. 메인 메뉴");
		System.out.print("번호 선택 : ");
	}

}

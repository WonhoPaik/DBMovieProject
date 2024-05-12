package controller;

import java.util.Scanner;

import model.CustomerVO;

public class CustomerManager {
	// 회원가입
	public static void customerReg() throws Exception {
		Scanner input = new Scanner(System.in);
		CustomerDAO cd = new CustomerDAO();
		CustomerVO cvo = new CustomerVO();
		String c_name; // 이름
		String c_id; // 아이디
		String c_pw; // 비밀번호
		String c_bday; // 생년월일
		String c_phone; // 핸드폰번호
		String c_addr; // 주소
		String c_email; // 이메일
		boolean id_check; // 아이디 체크
		System.out.println("회원 정보 입력");
		System.out.print("성명 : ");
		c_name = input.nextLine();
		do {
			System.out.print("아이디(6자 이상 12자 이내) : ");
			c_id = input.nextLine();
			id_check = cd.getCustomerIdOverlap(c_id);
			if (id_check) {
				System.out.println("중복된 아이디입니다. 다시 입력하세요");
			}
		} while (id_check);
		System.out.print("비밀번호(12자 이내) : ");
		c_pw = input.nextLine();
		System.out.print("생년월일(8자리) : ");
		c_bday = input.nextLine();
		System.out.print("전화번호 : ");
		c_phone = input.nextLine();
		System.out.print("도로명 주소 : ");
		c_addr = input.nextLine();
		System.out.print("이메일 : ");
		c_email = input.nextLine();
		cvo.setC_name(c_name);
		cvo.setC_id(c_id);
		cvo.setC_pw(c_pw);
		cvo.setC_bday(c_bday);
		cvo.setC_phone(c_phone);
		cvo.setC_addr(c_addr);
		cvo.setC_email(c_email);
		cd.setCustomerReg(cvo);
		System.out.println();
		System.out.println("등록 회원 정보");
		cd.getCustomer(cvo.getC_id(), cvo.getC_pw());
		System.out.println();
	}

	// 회원 정보 수정
	public void customerUpdate() throws Exception {
		Scanner input = new Scanner(System.in);
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = new CustomerVO();
		String c_no; // 회원번호
		String c_id; // 아이디
		String c_pw; // 입력 비밀번호
		String c_pw2; // 수정 비밀번호
		String c_phone; // 수정 전화번호
		String c_addr; // 수정 주소
		String c_email; // 수정 이메일
		boolean success = false;
		System.out.println("회원 정보 수정");
		do {
			System.out.print("아이디 : ");
			c_id = input.nextLine();
			System.out.print("비밀번호 : ");
			c_pw = input.nextLine();
			success = cdao.getCustomerLogin(c_id, c_pw);
			if (!success) {
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
			}
		} while (!success);
		c_no = cdao.getCustomerNum(c_id, c_pw);
		System.out.println();
		System.out.println("회원 번호 : " + c_no);
		System.out.print("비밀번호(20자 이내) : ");
		c_pw2 = input.nextLine();
		System.out.print("전화번호 : ");
		c_phone = input.nextLine();
		System.out.print("도로명 주소 : ");
		c_addr = input.nextLine();
		System.out.print("이메일 : ");
		c_email = input.nextLine();
		cvo.setC_pw(c_pw2);
		cvo.setC_phone(c_phone);
		cvo.setC_addr(c_addr);
		cvo.setC_email(c_email);
		cdao.setCustomerUpdate(cvo);
		System.out.println();
		System.out.println("회원정보 수정완료");
		cdao.getCustomer(c_id, cvo.getC_pw());
		System.out.println();
	}

	// 회원 정보 수정 (관리자)
	public void customerAdminUpdate() throws Exception {
		Scanner input = new Scanner(System.in);
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = new CustomerVO();
		int c_no; // 회원번호
		String c_name; // 회원이름
		String c_bday; // 회원 생년월일
		String c_id; // 회원 아이디
		String c_pw; // 수정 비밀번호
		String c_phone; // 수정 전화번호
		String c_addr; // 수정 주소
		String c_email; // 수정 이메일
		System.out.println("회원 정보 리스트");
		cdao.getCustomerTotalList();
		System.out.println();
		System.out.print("수정을 원하는 회원번호 : ");
		c_no = input.nextInt();
		input.nextLine();
		System.out.print("이름 : ");
		c_name = input.nextLine();
		System.out.print("생년월일 : ");
		c_bday = input.nextLine();
		System.out.print("아이디 : ");
		c_id = input.nextLine();
		System.out.print("비밀번호(20자 이내) : ");
		c_pw = input.nextLine();
		System.out.print("전화번호 : ");
		c_phone = input.nextLine();
		System.out.print("도로명 주소 : ");
		c_addr = input.nextLine();
		System.out.print("이메일 : ");
		c_email = input.nextLine();
		cvo.setC_no(c_no);
		cvo.setC_name(c_name);
		cvo.setC_bday(c_bday);
		cvo.setC_id(c_id);
		cvo.setC_pw(c_pw);
		cvo.setC_phone(c_phone);
		cvo.setC_addr(c_addr);
		cvo.setC_email(c_email);
		cdao.setCustomerUpdate(cvo);
		System.out.println();
		System.out.println("회원정보 수정완료");
		cdao.getAdminCustomer(c_no);
		System.out.println();
	}

	// 회원탈퇴
	public void customerDrop() {
		Scanner input = new Scanner(System.in);
		CustomerDAO cdao = new CustomerDAO();
		String c_pw; // 비밀번호
		String c_bday; // 생년월일
		boolean success = false;
		System.out.println("회원 정보 수정");
		try {
			do {
				System.out.print("생년월일 : ");
				c_bday = input.nextLine();
				System.out.print("비밀번호 : ");
				c_pw = input.nextLine();
				success = cdao.setCustomerDelete(c_bday, c_pw);
				if (!success) {
					System.out.println("정보를 정확하게 입력해주세요");
				}
			} while (!success);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 포인트 조회
	public void getCustomerPoint() throws Exception {
		Scanner input = new Scanner(System.in);
		CustomerDAO cdao = new CustomerDAO();
		String c_pw; // 비밀번호
		int c_no; // 생년월일
		System.out.println("포인트 조회");
		System.out.print("비밀번호 : ");
		c_pw = input.nextLine();
		c_no = Integer.parseInt(cdao.getCustomerNumber(c_pw));
		cdao.getPoint(c_no);
	}

	// 회원삭제
	public void customerDelete() throws Exception {
		Scanner input = new Scanner(System.in);
		CustomerDAO cdao = new CustomerDAO();
		String c_no; // 일련번호
		boolean success = false;
		System.out.println("회원 삭제");
		cdao.getCustomerTotalList();
		try {
			do {
				System.out.print("삭제할 회원번호 : ");
				c_no = input.nextLine();
				success = cdao.setAdminCustomerDelete(c_no);
				if (!success) {
					System.out.println("정보를 정확하게 입력해주세요");
				}
			} while (!success);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 전체 목록
	public void customerTotalList() throws Exception {
		CustomerDAO cdao = new CustomerDAO();
		System.out.println("회원 정보 전체 목록");
		cdao.getCustomerTotalList();

	}

}

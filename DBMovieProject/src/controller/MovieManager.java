package controller;

import java.util.Scanner;

import model.MovieVO;

public class MovieManager {

	// 영화 목록
	public void movieList() throws Exception {
		MovieDAO md = new MovieDAO();
		System.out.println("영화 전체 리스트");
		md.getMovieTotalList();
		System.out.println();
	}

	// 영화 등록
	public void movieReg() throws Exception {
		Scanner input = new Scanner(System.in);
		MovieDAO md = new MovieDAO();
		MovieVO mvo = new MovieVO();
		String m_name; // 영화제목
		String m_director; // 감독
		String m_genre; // 장르
		String m_relDate; // 개봉일
		String m_room; // 상영관
		String m_date; // 상영날짜
		String m_info; // 영화요약
		System.out.println("영화 전체 리스트");
		md.getMovieTotalList();
		System.out.println();
		System.out.println("영화 정보 입력");
		System.out.print("영화제목 : ");
		m_name = input.nextLine();
		System.out.print("감독 : ");
		m_director = input.nextLine();
		System.out.print("장르 : ");
		m_genre = input.nextLine();
		System.out.print("개봉일 : ");
		m_relDate = input.nextLine();
		System.out.print("상영관 : ");
		m_room = input.nextLine();
		System.out.print("상영날짜 : ");
		m_date = input.nextLine();
		System.out.print("영화요약 : ");
		m_info = input.nextLine();
		mvo.setM_name(m_name);
		mvo.setM_director(m_director);
		mvo.setM_genre(m_genre);
		mvo.setM_relDate(m_relDate);
		mvo.setM_room(m_room);
		mvo.setM_date(m_date);
		mvo.setM_info(m_info);
		md.setMovieReg(mvo);
		System.out.println();
		System.out.println("영화 전체 리스트");
		md.getMovieTotalList();
		System.out.println();
	}

	// 영화 업데이트
	public void movieUpdate() throws Exception {
		Scanner input = new Scanner(System.in);
		MovieDAO md = new MovieDAO();
		MovieVO mvo = new MovieVO();
		int m_no; // 수정할 영화 일련번호
		String m_name; // 수정할 영화제목
		String m_director; // 수정할 감독
		String m_genre; // 수정할 장르
		String m_relDate; // 수정할 개봉일
		String m_room; // 수정할 상영관
		String m_date; // 수정할 상영날짜
		String m_info; // 수정할 영화요약
		System.out.println("영화 전체 리스트(예매중인 영화 변경 불가)");
		md.getMovieTotalList();
		System.out.println();
		System.out.println("수정할 영화 일련번호 입력");
		System.out.print("영화 일련번호 : ");
		m_no = input.nextInt();
		input.nextLine();
		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.print("영화제목 : ");
		m_name = input.nextLine();
		System.out.print("감독 : ");
		m_director = input.nextLine();
		System.out.print("장르 : ");
		m_genre = input.nextLine();
		System.out.print("개봉일 : ");
		m_relDate = input.nextLine();
		System.out.print("상영관 : ");
		m_room = input.nextLine();
		System.out.print("상영날짜 : ");
		m_date = input.nextLine();
		System.out.print("영화요약 : ");
		m_info = input.nextLine();
		mvo.setM_no(m_no);
		mvo.setM_name(m_name);
		mvo.setM_director(m_director);
		mvo.setM_genre(m_genre);
		mvo.setM_relDate(m_relDate);
		mvo.setM_room(m_room);
		mvo.setM_date(m_date);
		mvo.setM_info(m_info);
		md.setMovieUpdate(mvo);
		System.out.println();
		System.out.println("영화 전체 리스트");
		md.getMovieTotalList();
		System.out.println();
	}

	// 영화 삭제
	public void movieDelete() throws Exception {
		Scanner input = new Scanner(System.in);
		MovieDAO md = new MovieDAO();
		int m_no; // 삭제할 영화 일련 번호
		System.out.println("영화 전체 리스트(상영중인 영화 삭제 불가)");
		md.getMovieTotalList();
		System.out.println();
		System.out.println("삭제할 영화 일련번호 입력");
		System.out.print("일련번호 : ");
		m_no = input.nextInt();
		md.setMovieDelete(m_no);
		System.out.println();
		System.out.println("영화 전체 리스트");
		md.getMovieTotalList();
		System.out.println();
	}

}

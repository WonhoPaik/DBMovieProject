package model;

public class MovieVO {
	private int m_no; //영화 일련번호
	private String m_name; //영화제목
	private String m_director; //감독
	private String m_genre; //장르
	private String m_relDate; //개봉일
	private String m_room; //상영관
	private String m_date; //상영일
	private String m_seat; //좌석
	private String m_info; //영화줄거리요약
	public MovieVO() {
		super();
	}
	public MovieVO(int m_no, String m_name, String m_director, String m_genre, String m_relDate, String m_room,
			String m_date, String m_seat, String m_info) {
		super();
		this.m_no = m_no;
		this.m_name = m_name;
		this.m_director = m_director;
		this.m_genre = m_genre;
		this.m_relDate = m_relDate;
		this.m_room = m_room;
		this.m_date = m_date;
		this.m_seat = m_seat;
		this.m_info = m_info;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_director() {
		return m_director;
	}
	public void setM_director(String m_director) {
		this.m_director = m_director;
	}
	public String getM_genre() {
		return m_genre;
	}
	public void setM_genre(String m_genre) {
		this.m_genre = m_genre;
	}
	public String getM_relDate() {
		return m_relDate;
	}
	public void setM_relDate(String m_relDate) {
		this.m_relDate = m_relDate;
	}
	public String getM_room() {
		return m_room;
	}
	public void setM_room(String m_room) {
		this.m_room = m_room;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
	public String getM_seat() {
		return m_seat;
	}
	public void setM_seat(String m_seat) {
		this.m_seat = m_seat;
	}
	public String getM_info() {
		return m_info;
	}
	public void setM_info(String m_info) {
		this.m_info = m_info;
	}
	@Override
	public String toString() {
		return "[" + m_no + ",\t" + m_name + ",\t" + m_director + ",\t" + m_genre
				+ ",\t" + m_relDate + ",\t" + m_room + ",\t" + m_date + ",\t" + m_seat
				+ ",\t" + m_info + "]";
	}
	
}
